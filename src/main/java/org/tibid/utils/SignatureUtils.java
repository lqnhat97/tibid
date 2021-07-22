package org.tibid.utils;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
import org.springframework.util.Base64Utils;
import org.tibid.entity.BidOrderEnity;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Logger;

public class SignatureUtils {

    private SignatureUtils() {
    }

    public static String sign(String secretKey, String data) {
        try {
            Mac hmacSHA256 = Mac.getInstance("HmacSHA256");
            SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getBytes(), "HmacSHA256");
            hmacSHA256.init(secretKeySpec);
            String encodedData = Base64.encodeBase64URLSafeString(data.getBytes());
            byte[] h =  hmacSHA256.doFinal(encodedData.getBytes());
            return Hex.encodeHexString(h);

        } catch (NoSuchAlgorithmException | InvalidKeyException e) {
            e.printStackTrace();
        }

        return null;
    }
}
