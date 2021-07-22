package org.tibid.utils;

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
            Logger.getLogger(SignatureUtils.class.getName()).info(data);
            Logger.getLogger(SignatureUtils.class.getName()).info(secretKey);
            Mac hmacSHA256 = Mac.getInstance("HmacSHA256");
            SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getBytes(StandardCharsets.UTF_8), "HmacSHA256");
            hmacSHA256.init(secretKeySpec);
            byte[] encodedData = Base64Utils.encode(data.getBytes(StandardCharsets.UTF_8));

            return Hex.encodeHexString(hmacSHA256.doFinal(encodedData));

        } catch (NoSuchAlgorithmException | InvalidKeyException e) {
            e.printStackTrace();
        }

        return null;
    }
}
