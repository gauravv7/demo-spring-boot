package com.example.demo.filter;

import com.example.demo.models.RateLimitData;
import com.example.demo.utils.RateLimitUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class RateLimitingFilter extends OncePerRequestFilter {

    // Map to store request counts per IP address
    private final Map<String, RateLimitData> requestCountsPerIpAddress = new ConcurrentHashMap<>();

    // Maximum requests allowed per minute
    private static final int MAX_REQUESTS_PER_MINUTE = 2;

    private static final int MIN_IN_SECONDS_TO_RATE_LIMIT = 60;

    private List<String> urlPatterns;

    public RateLimitingFilter() {
        // Initialize with some example URLs or patterns
        urlPatterns = new ArrayList<>();
        urlPatterns.add("/myapp/v1/home/rate-limited");
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;

        String requestUri = request.getRequestURI();

        // Check if the request URI matches any URL pattern in the list
        boolean isUrlMatched = urlPatterns.stream()
                .anyMatch(urlPattern -> requestUri.equals(urlPattern));

        if(isUrlMatched) {
            String clientIpAddress = httpServletRequest.getRemoteAddr();

            // Initialize request count for the client IP address
            requestCountsPerIpAddress.putIfAbsent(clientIpAddress, RateLimitUtils.makeRateLimitData());
            RateLimitData requestCount = requestCountsPerIpAddress.get(clientIpAddress);

            // Increment the request count
            int requests = requestCount.getRequestCounter().incrementAndGet();

            // Check if the request limit has been exceeded
            boolean ifPastTime = RateLimitUtils.checkIfPastTime(requestCount.getLastOccuringTimeStamp(), MIN_IN_SECONDS_TO_RATE_LIMIT);
            if (requests > (MAX_REQUESTS_PER_MINUTE+1) && !ifPastTime) {
                httpServletResponse.setStatus(HttpServletResponse.SC_SERVICE_UNAVAILABLE);
                httpServletResponse.getWriter().write("Too many requests. Please try again later.");
                return;
            } else {
                requestCount.setLastOccuringTimeStamp(LocalDateTime.now());
                if(ifPastTime) {
                    requestCount.setRequestCounter(new AtomicInteger(0));
                }
            }
        }
        // Allow the request to proceed
        filterChain.doFilter(request, response);
    }
}
