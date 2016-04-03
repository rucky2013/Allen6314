package com.allenway.encryption;


import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;

import java.text.DateFormat;

/**
 * Created by wuhuachuan on 16/3/8.
 */
public class DESEncryptUtil {

    private DESEncryptUtil(){}

    private static final DateFormat df = DateFormat.getDateTimeInstance(DateFormat.DEFAULT, DateFormat.SHORT);

    private static String ADMINSALT = "97952b3e-ac0b-454c-b280-9832e0f7377d";

    /**
     * Salt is Base64(now + identity)
     *
     * Identity can be null, will generate a random alphanumeric instead.
     *
     * @param identity can be null
     * @return
     */
    public static String getSalt(String identity) {
        return Base64.encodeBase64String(blend(ADMINSALT.getBytes(), identity.getBytes()));
    }
    /**
     * Stored passphrase
     *
     * @param salt
     * @param userPassword
     * @return
     */
    public static String getPassphrase(String salt, String userPassword) {
        return DigestUtils.sha1Hex(blend(salt.getBytes(), userPassword.getBytes()));
    }

    public static boolean matchPassphrase(final String passphrase, final String salt, final String userPassword) {
        boolean result = passphrase.equalsIgnoreCase(getPassphrase(salt, userPassword));
        if (!result) {
        }
        return result;
    }
    private static byte[] blend(byte[] a, byte[] b) {
        byte[] result = new byte[a.length + b.length];
        int ai = 0;
        int bi = 0;
        for (int i = 0; i < result.length; i++) {
            if (ai == a.length || bi < ai && bi < b.length) {
                result[i] = b[bi++];
                continue;
            }
            if (bi == b.length || ai <= bi) {
                result[i] = a[ai++];
                continue;
            }
        }
        return result;
    }
}
