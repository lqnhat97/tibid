package org.tibid.utils;

import com.google.gson.Gson;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.logging.Logger;

public class HttpUtils {


    private HttpUtils() {
    }

    public static HttpResponse postJson(List<NameValuePair> nvps, String url, String content, int timeout)
            throws IOException {

        HttpResponse response;

        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(timeout).setConnectTimeout(timeout)
                .setConnectionRequestTimeout(timeout).setStaleConnectionCheckEnabled(true).build();

        try (CloseableHttpClient httpclient = HttpClients.custom().setDefaultRequestConfig(requestConfig).build()) {

            HttpPost httppost = new HttpPost(url);
            nvps.forEach(item -> httppost.addHeader(item.getName(), item.getValue()));
            httppost.setEntity(new StringEntity(content, StandardCharsets.UTF_8.name()));
            response = httpclient.execute(httppost);
//            HttpEntity entity = response.getEntity();


//            if (entity != null) {
//                try (InputStream inStream = entity.getContent()) {
//                    String result = IOUtils.toString(inStream, StandardCharsets.UTF_8);
//                    zp2EIBAResponse.responseBody = result;
//                }
//            }
        }
        return response;
    }

}
