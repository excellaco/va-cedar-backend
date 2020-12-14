package com.excella.reactor.config;

import com.excella.reactor.domain.entities.User;
import java.security.KeyPair;
import java.util.Collections;
import java.util.Map;
import javax.sql.DataSource;
import lombok.Getter;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

/** Authorization configuration */
@Configuration
@EnableAuthorizationServer
@EnableConfigurationProperties(SecurityProperties.class)
@Getter
public class AuthorizationConfig extends AuthorizationServerConfigurerAdapter {

  private final DataSource dataSource;
  private final PasswordEncoder passwordEncoder;
  private final AuthenticationManager authenticationManager;
  private final SecurityProperties securityProperties;
  private JwtAccessTokenConverter jwtAccessTokenConverter;
  private TokenStore tokenStore;

  /**
   * @param dataSource a SQL datasource
   * @param passwordEncoder a password encoder
   * @param authenticationManager a authentication manager to process the request
   * @param securityProperties security properties for OAuth and JWT
   */
  public AuthorizationConfig(
      final DataSource dataSource,
      final PasswordEncoder passwordEncoder,
      final AuthenticationManager authenticationManager,
      final SecurityProperties securityProperties) {
    this.dataSource = dataSource;
    this.passwordEncoder = passwordEncoder;
    this.authenticationManager = authenticationManager;
    this.securityProperties = securityProperties;
  }

  /**
   * Creates a JWT Token Store given a JWT Access Token Converter.
   *
   * @return TokenStore
   */
  @Bean(name = "tokenStore")
  public TokenStore getTokenStore() {
    if (tokenStore == null) {
      tokenStore = new JwtTokenStore(getJwtAccessTokenConverter());
    }
    return tokenStore;
  }

  /**
   * Sets up the token service that authorizes and authenticates with JWT, users stored in the DB,
   * and the Authentication Manager.
   *
   * @param tokenStore tokenStore
   * @param clientDetailsService clientDetailsService
   * @return Default token service
   */
  @Bean
  public DefaultTokenServices tokenServices(
      final TokenStore tokenStore, final ClientDetailsService clientDetailsService) {
    final DefaultTokenServices tokenServices = new DefaultTokenServices();
    tokenServices.setSupportRefreshToken(true);
    tokenServices.setTokenStore(tokenStore);
    tokenServices.setClientDetailsService(clientDetailsService);
    tokenServices.setAuthenticationManager(this.authenticationManager);
    return tokenServices;
  }

  /**
   * Creates a JWT access token converter with the JKS file in the classpath.
   *
   * @return JwtAccessTokenConcerter
   */
  @Bean(name = "jwtAccessTokenConverter")
  public JwtAccessTokenConverter getJwtAccessTokenConverter() {
    if (jwtAccessTokenConverter != null) {
      return jwtAccessTokenConverter;
    }
    jwtAccessTokenConverter =
        new JwtAccessTokenConverter() {
          @Override
          public OAuth2AccessToken enhance(
              final OAuth2AccessToken accessToken, final OAuth2Authentication authentication) {
            if (authentication.getOAuth2Request().getGrantType().equalsIgnoreCase("password")
                && authentication.getPrincipal() instanceof User) {
              ((DefaultOAuth2AccessToken) accessToken)
                  .setAdditionalInformation(
                      Map.of("email", ((User) authentication.getPrincipal()).getEmail()));
            }

            final OAuth2AccessToken enhancedAccessToken =
                super.enhance(accessToken, authentication);
            ((DefaultOAuth2AccessToken) enhancedAccessToken)
                .setAdditionalInformation(Collections.emptyMap());
            return enhancedAccessToken;
          }
        };
    final SecurityProperties.JwtProperties jwtProperties = securityProperties.getJwt();
    final KeyPair keyPair = keyPair(jwtProperties, keyStoreKeyFactory(jwtProperties));

    jwtAccessTokenConverter.setKeyPair(keyPair);
    return jwtAccessTokenConverter;
  }

  /**
   * Stores OAuth client details in database.
   *
   * @param clients clients
   * @throws Exception exception
   */
  @Override
  public void configure(final ClientDetailsServiceConfigurer clients) throws Exception {
    clients.jdbc(this.dataSource);
  }

  @Override
  public void configure(final AuthorizationServerEndpointsConfigurer endpoints) {
    endpoints
        .authenticationManager(this.authenticationManager)
        .accessTokenConverter(getJwtAccessTokenConverter())
        .tokenStore(getTokenStore());
  }

  /**
   * Configures Authentication, setting password encoder and permissions on a token.
   *
   * @see WebSecurityConfiguration#passwordEncoder()
   * @param oauthServer oauthServer
   */
  @Override
  public void configure(final AuthorizationServerSecurityConfigurer oauthServer) {
    oauthServer
        .passwordEncoder(this.passwordEncoder)
        .tokenKeyAccess("permitAll()")
        .checkTokenAccess("isAuthenticated()");
  }

  /**
   * Get KeyPair object from a JKS file.
   *
   * @param jwtProperties jwtProperties
   * @param keyStoreKeyFactory keyStoreKeyFactory
   * @return KeyPair object.
   */
  private KeyPair keyPair(
      final SecurityProperties.JwtProperties jwtProperties,
      final KeyStoreKeyFactory keyStoreKeyFactory) {
    return keyStoreKeyFactory.getKeyPair(
        jwtProperties.getKeyPairAlias(), jwtProperties.getKeyPairPassword().toCharArray());
  }

  /**
   * Creates a keyStoreKeyFactory with JWT Properties. Gets the KeyPair value from the JKS file
   * using the credentials in the JWTProperties in the application.yml file.
   *
   * @param jwtProperties jwtProperties
   * @return KeyStoreKeyFactory
   */
  private KeyStoreKeyFactory keyStoreKeyFactory(
      final SecurityProperties.JwtProperties jwtProperties) {
    return new KeyStoreKeyFactory(
        jwtProperties.getKeyStore(), jwtProperties.getKeyStorePassword().toCharArray());
  }
}
