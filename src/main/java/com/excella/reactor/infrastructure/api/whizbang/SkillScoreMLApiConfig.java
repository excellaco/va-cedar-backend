package com.excella.reactor.infrastructure.api.whizbang;

import lombok.*;
import org.eclipse.jetty.client.HttpClient;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.reactive.JettyClientHttpConnector;
import org.springframework.http.client.reactive.JettyResourceFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * API configuration for whizbang skill score ML service. Uses spring profiles to bring in
 * configuration settings, see resources folder
 */
@Component
@NoArgsConstructor
@ConfigurationProperties("whizbang")
class SkillScoreMLApiConfig {

  private String baseUrl;
  private long connectTimeout = 5000;
  private long addressResolutionTimeout = 5000;
  private long idleTimeout = 15_000;

  /**
   * Create with baseUrl
   *
   * @param baseUrl the baseURL of the API
   */
  SkillScoreMLApiConfig(final String baseUrl) {
    this.baseUrl = baseUrl;
  }

  @Bean
  static JettyResourceFactory resourceFactory() {
    return new JettyResourceFactory();
  }

  @Bean
  WebClient webClient() {
    final var httpClient = new HttpClient();
    httpClient.setConnectTimeout(this.connectTimeout);
    httpClient.setAddressResolutionTimeout(this.addressResolutionTimeout);
    httpClient.setIdleTimeout(this.idleTimeout);

    final var connector = new JettyClientHttpConnector(httpClient, resourceFactory());

    return WebClient.builder().clientConnector(connector).baseUrl(this.baseUrl).build();
  }
}
