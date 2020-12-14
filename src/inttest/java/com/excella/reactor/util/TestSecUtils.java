package com.excella.reactor.util;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import com.excella.reactor.config.SecurityProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.http.MediaType;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.stereotype.Component;
import org.springframework.test.web.servlet.MockMvc;

@Component
@EnableConfigurationProperties(SecurityProperties.class)
public class TestSecUtils {

  @Autowired private ObjectMapper mapper;
  @Autowired private SecurityProperties securityProperties;

  public String getAuth(MockMvc mockMvc) {
    try {
      String result =
          mockMvc
              .perform(
                  post("/oauth/token")
                      .with(
                          SecurityMockMvcRequestPostProcessors.httpBasic(
                              securityProperties.getOauth2().getClient().getClientId(),
                              securityProperties.getOauth2().getClient().getClientSecret()))
                      .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                      .content(
                          buildUrlEncodedFormEntity(
                              Map.of(
                                  "grant_type", "password",
                                  "username", "user",
                                  "password", "pass"))))
              .andReturn()
              .getResponse()
              .getContentAsString();
      return mapper.readValue(result, OAuth2AccessToken.class).getValue();
    } catch (Exception e) {
      throw new RuntimeException();
    }
  }

  private String buildUrlEncodedFormEntity(Map<String, String> values)
      throws UnsupportedEncodingException {
    StringBuilder result = new StringBuilder();
    for (Map.Entry<String, String> value : values.entrySet()) {
      result.append(
          String.format(
              "&%s=%s",
              URLEncoder.encode(value.getKey(), StandardCharsets.UTF_8.name()),
              URLEncoder.encode(value.getValue(), StandardCharsets.UTF_8.name())));
    }
    return result.toString();
  }
}
