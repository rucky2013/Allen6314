package com.allenway.infrustructure.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

/**
 * Created by wuhuachuan on 16/5/3.
 */
@ToString
@Data
@AllArgsConstructor
public class OAuthParamEntity {

    private String oauthTokenApiURL;
    private String clientId;
    private String clientSecret;
    private String grantType;
    private String provisionKey;
    private String scope;

    public OAuthParamEntity(){}
    public OAuthParamEntity(Builder builder){
        this.oauthTokenApiURL = builder.oauthTokenApiURL;
        this.clientId = builder.clientId;
        this.clientSecret = builder.clientSecret;
        this.grantType = builder.grantType;
        this.provisionKey = builder.provisionKey;
        this.scope = builder.scope;
    }

    public static class Builder{
        private String oauthTokenApiURL;
        private String clientId;
        private String clientSecret;
        private String grantType;
        private String provisionKey;
        private String scope;

        public Builder oauthTokenApiURL(String oauthTokenApiURL){
            this.oauthTokenApiURL = oauthTokenApiURL;
            return this;
        }
        public Builder clientId(String clientId){
            this.clientId = clientId;
            return this;
        }
        public Builder clientSecret(String clientSecret){
            this.clientSecret = clientSecret;
            return this;
        }
        public Builder grantType(String grantType){
            this.grantType = grantType;
            return this;
        }
        public Builder provisionKey(String provisionKey){
            this.provisionKey = provisionKey;
            return this;
        }
        public Builder scope(String scope){
            this.scope = scope;
            return this;
        }

        public OAuthParamEntity build(){
            return  new OAuthParamEntity(this);
        }
    }

}
