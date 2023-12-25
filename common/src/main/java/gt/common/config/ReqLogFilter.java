package gt.common.config;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * we want this filter to run after ServerHttpObservationFilter if span/trace id should be printed on log
 */
@Slf4j
public class ReqLogFilter implements Filter {

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        if (log.isTraceEnabled()) {
            Map<String, String> req = new HashMap<>();

            req.put("req.remoteHost", request.getRemoteHost());

            if (request instanceof HttpServletRequest) {
                HttpServletRequest httpServletRequest = (HttpServletRequest) request;
                req.put("req.requestURI", httpServletRequest.getRequestURI());
                StringBuffer requestURL = httpServletRequest.getRequestURL();
                if (requestURL != null) {
                    req.put("req.requestURL", requestURL.toString());
                }
                req.put("req.method", httpServletRequest.getMethod());
                req.put("req.req.queryString", httpServletRequest.getQueryString());
                req.put("req.userAgent", httpServletRequest.getHeader("User-Agent"));
                req.put("req.xForwardedFor", httpServletRequest.getHeader("X-Forwarded-For"));
            }

            log.trace("Received request {} ", req);
        }

        chain.doFilter(request, response);

    }

}
