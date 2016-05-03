package com.allenway.infrustructure.config;

import com.allenway.infrustructure.entity.OAuthParamEntity;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by wuhuachuan on 16/3/15.
 */

@Slf4j
@Data
@Configuration
@ConditionalOnProperty(name="infrastructure.oauth2.enable",havingValue="true")
public class OAuthConfiguration {

    @Value("${infrastructure.oauth2.oauthTokenApiURL}")
    private String oauthTokenApiURL;

    @Value("${infrastructure.oauth2.clientId}")
    private String clientId;

    @Value("${infrastructure.oauth2.clientSecret}")
    private String clientSecret;

    @Value("${infrastructure.oauth2.grantType}")
    private String grantType;

    @Value("${infrastructure.oauth2.provisionKey}")
    private String provisionKey;

    @Value("${infrastructure.oauth2.scope}")
    private String scope;

    @Bean
    public OAuthParamEntity createOAuthParamEntity(){
        log.info("create OAuthParamEntity ... oauthTokenApiURL = {},clientId = {},clientSecret = {},grantType = {},provisionKey = {},scope = {}",
                                                                                                    oauthTokenApiURL,
                                                                                                    clientId,
                                                                                                    clientSecret,
                                                                                                    grantType,
                                                                                                    provisionKey,
                                                                                                    scope);
        return new OAuthParamEntity.Builder()
                .oauthTokenApiURL(oauthTokenApiURL)
                .clientId(clientId)
                .clientSecret(clientSecret)
                .grantType(grantType)
                .provisionKey(provisionKey)
                .scope(scope)
                .build();
    }
}
