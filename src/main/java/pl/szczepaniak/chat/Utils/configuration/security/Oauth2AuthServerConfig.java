package pl.szczepaniak.chat.Utils.configuration.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import pl.szczepaniak.chat.Utils.AppConfig;

import javax.sql.DataSource;

@Configuration
@EnableAuthorizationServer
public class Oauth2AuthServerConfig extends AuthorizationServerConfigurerAdapter{

    private AuthenticationManager authenticationManager;
    private DataSource dataSource;

    @Autowired
    public Oauth2AuthServerConfig(AuthenticationManager authenticationManager,
                                  @Qualifier("dataSource") DataSource dataSource) {
        this.dataSource = dataSource;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public void configure(final AuthorizationServerEndpointsConfigurer endpoints) {
        endpoints.tokenStore(tokenStore())
                .authenticationManager(authenticationManager);
    }

    @Override
    public void configure(final ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                .withClient(AppConfig.CLIENT_NAME_SECURITY)
                .secret(AppConfig.SERVER_PASSWORD_SECURITY)
                .scopes("read", "write")
                .authorizedGrantTypes("password","authorization_code", "refresh_token")
                .accessTokenValiditySeconds(AppConfig.ACCESS_TOKEN_VALIDITY_SECONDS)
                .refreshTokenValiditySeconds(AppConfig.REFRESH_TOKEN_VALIDITY_SECONDS);
    }

    @Bean public TokenStore tokenStore() { return new JdbcTokenStore(dataSource); }

}
