package com.pelime.newbanck.support;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

public class StaticCommon {
    public static String MAIL_REGEX="\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*";
    public static String PHONE_REGEX="^1[3|4|5|7|8|9][0-9]\\d{8}$";
    public static String SALT="eb";

    public static String WEB_SECURITY_SECRECT="webeb";
    public static String MD5Pwd(String username, String pwd) {
        // 加密算法MD5
        // salt盐 username + salt
        // 迭代次数
        String md5Pwd = new SimpleHash("MD5", pwd,
                ByteSource.Util.bytes(username + SALT), 2).toHex();
        return md5Pwd;
    }
}
