package com.peppermint.usermanagementsystem.logging.config;

import com.peppermint.usermanagementsystem.logging.model.ApiIOLogger;
import com.peppermint.usermanagementsystem.logging.repository.ApiIOLoggerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

@Slf4j
@Component
@RequiredArgsConstructor
public class ApiIOLoggerFilter implements Filter {
    private final ApiIOLoggerRepository apiIOLoggerRepository;

    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        if (request instanceof HttpServletRequest && response instanceof HttpServletResponse) {
            ContentCachingRequestWrapper requestWrapper = new ContentCachingRequestWrapper((HttpServletRequest) request);
            ContentCachingResponseWrapper responseWrapper = new ContentCachingResponseWrapper((HttpServletResponse) response);
            try {
                chain.doFilter(requestWrapper, responseWrapper);
            } finally {
                ApiIOLogger apiIOLogger = new ApiIOLogger();
                performRequestAudit(requestWrapper, apiIOLogger);
                performResponseAudit(responseWrapper, apiIOLogger);
                apiIOLoggerRepository.save(apiIOLogger);
            }
        } else {
            chain.doFilter(request, response);
        }
    }

    private void performRequestAudit(ContentCachingRequestWrapper requestWrapper, ApiIOLogger apiIOLogger) {
        String requestBody = getPayLoadFromByteArray(requestWrapper.getContentAsByteArray(), requestWrapper.getCharacterEncoding());
        if (requestWrapper != null && requestWrapper.getContentAsByteArray() != null && requestWrapper.getContentAsByteArray().length > 0) {
            log.debug("\n Headers:: {} \n Request Body:: {}", new ServletServerHttpRequest((HttpServletRequest) requestWrapper.getRequest()).getHeaders(), requestBody);
        }
        apiIOLogger.setApiUrl(requestWrapper.getRequestURI() + "?" + requestWrapper.getQueryString());
        apiIOLogger.setRequestMethod(requestWrapper.getMethod());
        apiIOLogger.setRemoteIp(requestWrapper.getRemoteAddr());
        apiIOLogger.setRequestBody(requestBody);
    }

    private void performResponseAudit(ContentCachingResponseWrapper responseWrapper, ApiIOLogger apiIOLogger)
            throws IOException {
        if (responseWrapper != null && responseWrapper.getContentAsByteArray() != null
                && responseWrapper.getContentAsByteArray().length > 0) {
            String responseBody = getPayLoadFromByteArray(responseWrapper.getContentAsByteArray(),
                    responseWrapper.getCharacterEncoding());
            log.debug("Response Body:: {}", responseBody);
            apiIOLogger.setResponseBody(responseBody);
            apiIOLogger.setResponseCode(responseWrapper.getStatus());
        } else {
            performErrorResponseAudit(responseWrapper, apiIOLogger);
        }
        responseWrapper.copyBodyToResponse();
    }

    private void performErrorResponseAudit(ContentCachingResponseWrapper responseWrapper, ApiIOLogger apiIOLogger) {
        log.warn("HTTP Error Status Code::" + responseWrapper.getStatus());
        apiIOLogger.setResponseCode(responseWrapper.getStatus());
        apiIOLogger.setResponseBody(getPayLoadFromByteArray(responseWrapper.getContentAsByteArray(),
                responseWrapper.getCharacterEncoding()));
    }

    private String getPayLoadFromByteArray(byte[] requestBuffer, String charEncoding) {
        String payLoad = "";
        try {
            payLoad = new String(requestBuffer, charEncoding);
        } catch (UnsupportedEncodingException unex) {
            payLoad = "Unsupported-Encoding";
        }
        return payLoad;
    }

}
