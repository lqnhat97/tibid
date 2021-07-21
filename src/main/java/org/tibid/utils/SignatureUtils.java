package org.tibid.utils;

import org.apache.commons.codec.binary.Hex;
import org.tibid.entity.BidOrderEnity;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class SignatureUtils {

    private SignatureUtils() {
    }

    public static String sign(String secretKey, String data) {
        try {

            Mac hmacSHA256 = Mac.getInstance("HmacSHA256");
            SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getBytes(StandardCharsets.UTF_8), "HmacSHA256");
            hmacSHA256.init(secretKeySpec);

            return new String(Hex.encodeHex(hmacSHA256.doFinal(data.getBytes(StandardCharsets.UTF_8))));

        } catch (NoSuchAlgorithmException | InvalidKeyException e) {
            e.printStackTrace();
        }

        return null;
    }
}
