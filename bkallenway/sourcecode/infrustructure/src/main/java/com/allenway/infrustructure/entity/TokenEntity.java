package com.allenway.infrustructure.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Created by wuhuachuan on 16/5/3.
 */

@ToString
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TokenEntity {
    private String access_token;
    private String expires_in;
    private String refresh_token;
    private String token_type;
}
