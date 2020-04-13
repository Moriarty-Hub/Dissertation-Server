package com.util;

import java.nio.charset.StandardCharsets;
import org.springframework.util.DigestUtils;

public class MD5 {

    public static String getMD5MessageDigest(String text) {
        return DigestUtils.md5DigestAsHex(text.getBytes(StandardCharsets.UTF_8)).toUpperCase();
    }

}
