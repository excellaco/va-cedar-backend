package com.excella.reactor.config;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/** */
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
@Slf4j
public class CorsFilter implements Filter {

  @Override
  public void init(final FilterConfig filterConfig) throws ServletException {
    // Called by the web container to indicate to a filter that it is being placed into service.
  }

  @Override
  public void doFilter(
      final ServletRequest req, final ServletResponse resp, final FilterChain chain)
      throws IOException, ServletException {
    final HttpServletResponse response = (HttpServletResponse) resp;
    final HttpServletRequest request = (HttpServletRequest) req;
    response.setHeader("Access-Control-Allow-Origin", "*");
    response.setHeader("Access-Control-Allow-Methods", "POST, GET, HEAD, PUT, OPTIONS, DELETE");
    response.setHeader("Access-Control-Max-Age", "3600");
    response.setHeader(
        "Access-Control-Allow-Headers",
        "x-requested-with, authorization, Content-Type, Authorization, credential, X-XSRF-TOKEN");

    if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
      response.setStatus(HttpServletResponse.SC_OK);
    } else {
      chain.doFilter(req, resp);
    }
  }

  @Override
  public void destroy() {
    // Called by the web container to indicate to a filter that it is being taken out of service.
  }
}
