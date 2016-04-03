package com.allenway.utils.validparam;

import org.springframework.util.StringUtils;

/**
 * Created by wuhuachuan on 16/3/9.
 */
public class ValidUtils {

    /**
     * 检测 id 是否合理
     * @param id
     * @return
     */
    public static boolean validIdParam(String id) {
        if(StringUtils.isEmpty(id)){
            return false;
            //防止 sql 攻击
        } else if(id.contains("\"") || id.contains("\'")){
            return false;
        } else {
            return true;
        }
    }
}
