package org.tibid.service.tiki;

import com.google.gson.Gson;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.tibid.dto.BidTicketDto;
import org.tibid.entity.BidOrderEntity;
import org.tibid.entity.BidTicketEntity;
import org.tibid.entity.tiki.Item;
import org.tibid.entity.tiki.Order;
import org.tibid.entity.tiki.request.BaseRequest;
import org.tibid.entity.tiki.request.CompleteRequest;
import org.tibid.entity.tiki.response.BaseResponse;
import org.tibid.mapper.BidOrderMapper;
import org.tibid.mapper.BidTicketMapper;
import org.tibid.repository.BidOrderRepo;
import org.tibid.repository.BidTicketRepo;
import org.tibid.utils.HttpUtils;
import org.tibid.utils.SignatureUtils;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
public class TikiIntegrateServiceImpl implements TikiIntegrateService {

    @Value(value = "${tiki.client-id}")
    private String clientId;

    @Value(value = "${tiki.base-url}")
    private String baseUrl;

    @Value(value = "${tiki.create-order-path}")
    private String createOrderPath;

    @Value(value = "${tiki.exchange-auth-token}")
    private String exchangeAuthToken;

    @Value(value = "${tiki.client-secret}")
    private String clientSecret;

    @Value(value = "${tiki.order-complete}")
    private String orderCompletePath;

    public static final String HEADER_CONTENT_TYPE = "application/json";

    public static final String HEADER_CLIENT_ID = "X-Tiniapp-Client-Id";

    public static final String HEADER_SIGNATURE = "X-Tiniapp-Signature";

    public static final String HEADER_TIMESTAMP = "X-Tiniapp-Timestamp";

    private final BidTicketRepo bidTicketRepo;

    private final BidOrderRepo bidOrderRepo;

    private final BidTicketMapper bidTicketMapper;

    private final BidOrderMapper bidOrderMapper;

    private final Gson gson = new Gson();

    public TikiIntegrateServiceImpl(BidTicketRepo bidTicketRepo, BidOrderRepo bidOrderRepo, BidTicketMapper bidTicketMapper, BidOrderMapper bidOrderMapper) {
        this.bidTicketRepo = bidTicketRepo;
        this.bidOrderRepo = bidOrderRepo;
        this.bidTicketMapper = bidTicketMapper;
        this.bidOrderMapper = bidOrderMapper;
    }

    @Override
    public Order createOrder(List<BidTicketDto> bidTicketDtoList, String customerId) {
        BaseResponse baseResponse = new BaseResponse();
        try {
            BaseRequest baseRequest = new BaseRequest();
            baseRequest.setCustomerId(customerId);
            List<Item> itemList = new ArrayList<>();
            for (BidTicketDto bidTicketDto : bidTicketDtoList) {
                BidTicketEntity bidTicketEntity = bidTicketMapper.toEntity(bidTicketDto);

                Optional<BidOrderEntity> bidOrderEntityOptional = bidOrderRepo.findById(bidTicketEntity.getBidOrderId());
                BidOrderEntity bidOrderEntity = new BidOrderEntity();
                if (bidOrderEntityOptional.isPresent()) {
                    bidOrderEntity = bidOrderEntityOptional.get();
                }


                Item item = new Item();
                item.setName(bidOrderEntity.getProductName());
                item.setSku(String.valueOf(bidOrderEntity.getProductId()));
                item.setQuantity(bidOrderEntity.getBidQuantity());
                item.setPrice((int) bidTicketEntity.getPrice());
                itemList.add(item);
            }
            baseRequest.setItems(itemList);
            String timestamp = Long.toString(System.currentTimeMillis());
            String payload = timestamp + "." + clientId + "." + gson.toJson(baseRequest);
            String signature = SignatureUtils.sign(clientSecret, payload);
            List<NameValuePair> headers = new ArrayList<>();
            headers.add(new BasicNameValuePair(HttpHeaders.CONTENT_TYPE, HEADER_CONTENT_TYPE));
            headers.add(new BasicNameValuePair(HEADER_CLIENT_ID, clientId));
            headers.add(new BasicNameValuePair(HEADER_SIGNATURE, signature));
            headers.add(new BasicNameValuePair(HEADER_TIMESTAMP, timestamp));
            String url = baseUrl + createOrderPath;

            Logger.getLogger(this.getClass().getName()).info(url);
            Logger.getLogger(this.getClass().getName()).info(gson.toJson(baseRequest));
            Logger.getLogger(this.getClass().getName()).info(gson.toJson(headers));

            HttpResponse response = HttpUtils.postJson(headers, url, gson.toJson(baseRequest), 120000);
            HttpEntity respEntity = response.getEntity();
            if (respEntity != null) {
                try (InputStream inStream = respEntity.getContent()) {
                    String result = new BufferedReader(
                            new InputStreamReader(inStream, StandardCharsets.UTF_8))
                            .lines()
                            .collect(Collectors.joining("\n"));
                    Logger.getLogger(this.getClass().getName()).info(gson.toJson(result));
                    baseResponse = gson.fromJson(result, BaseResponse.class);
                    if (baseResponse.getError() != null) {
                        return null;
                    }
                }
            }


            return baseResponse.getData().getOrder();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public String getAuthToken(String authCode) {
        BaseResponse baseResponse = new BaseResponse();
        try {
            BaseRequest baseRequest = new BaseRequest();
            baseRequest.setCode(authCode);
            String timestamp = Long.toString(System.currentTimeMillis());
            String payload = timestamp + "." + clientId + "." + gson.toJson(baseRequest);
            String signature = SignatureUtils.sign(clientSecret, payload);
            Logger.getLogger(TikiIntegrateServiceImpl.class.getName()).info("signature: " + signature);

            List<NameValuePair> headers = new ArrayList<>();
            headers.add(new BasicNameValuePair(HttpHeaders.CONTENT_TYPE, HEADER_CONTENT_TYPE));
            headers.add(new BasicNameValuePair(HEADER_CLIENT_ID, clientId));
            headers.add(new BasicNameValuePair(HEADER_SIGNATURE, signature));
            headers.add(new BasicNameValuePair(HEADER_TIMESTAMP, timestamp));
            String url = baseUrl + exchangeAuthToken;

            Logger.getLogger(this.getClass().getName()).info(url);
            Logger.getLogger(this.getClass().getName()).info(gson.toJson(baseRequest));
            Logger.getLogger(this.getClass().getName()).info(gson.toJson(headers));

            HttpResponse response = HttpUtils.postJson(headers, url, gson.toJson(baseRequest), 120000);
            HttpEntity respEntity = response.getEntity();
            if (respEntity != null) {
                try (InputStream inStream = respEntity.getContent()) {
                    String result = new BufferedReader(
                            new InputStreamReader(inStream, StandardCharsets.UTF_8)).readLine();
                    Logger.getLogger(this.getClass().getName()).info(gson.toJson(result));
                    baseResponse = gson.fromJson(result, BaseResponse.class);
                    if (baseResponse.getError() != null) {
                        return null;
                    }
                }
            }
            return baseResponse.getData().getCustomer().getId();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Order completeOrder(String orderId) {
        BaseResponse baseResponse = new BaseResponse();
        try {
            CompleteRequest baseRequest = new CompleteRequest();
            baseRequest.setOrderId(orderId);
            baseRequest.setComment("Giao h??ng th??nh c??ng");
            String timestamp = Long.toString(System.currentTimeMillis());
            String payload = timestamp + "." + clientId + "." + gson.toJson(baseRequest);
            String signature = SignatureUtils.sign(clientSecret, payload);

            List<NameValuePair> headers = new ArrayList<>();
            headers.add(new BasicNameValuePair(HttpHeaders.CONTENT_TYPE, HEADER_CONTENT_TYPE));
            headers.add(new BasicNameValuePair(HEADER_CLIENT_ID, clientId));
            headers.add(new BasicNameValuePair(HEADER_SIGNATURE, signature));
            headers.add(new BasicNameValuePair(HEADER_TIMESTAMP, timestamp));
            String url = baseUrl + exchangeAuthToken;

            Logger.getLogger(this.getClass().getName()).info(gson.toJson(baseRequest));

            HttpResponse response = HttpUtils.postJson(headers, url, gson.toJson(baseRequest), 120000);
            HttpEntity respEntity = response.getEntity();
            if (respEntity != null) {
                try (InputStream inStream = respEntity.getContent()) {
                    String result = new BufferedReader(
                            new InputStreamReader(inStream, StandardCharsets.UTF_8)).readLine();
                    Logger.getLogger(this.getClass().getName()).info(gson.toJson(result));
                    baseResponse = gson.fromJson(result, BaseResponse.class);
                    if (baseResponse.getError() != null) {
                        return null;
                    }
                }
            }
            return baseResponse.getData().getOrder();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Order queryTikiOrder(long orderId) {
        BaseResponse baseResponse = new BaseResponse();
        try {
            String path = String.format("%s?order_id=%s", createOrderPath, orderId);
            String timestamp = Long.toString(System.currentTimeMillis());
            String url = baseUrl + path;
            String payload = timestamp + "." + clientId + "." + path;
            String signature = SignatureUtils.sign(clientSecret, payload);

            List<NameValuePair> headers = new ArrayList<>();
            headers.add(new BasicNameValuePair(HttpHeaders.CONTENT_TYPE, HEADER_CONTENT_TYPE));
            headers.add(new BasicNameValuePair(HEADER_CLIENT_ID, clientId));
            headers.add(new BasicNameValuePair(HEADER_SIGNATURE, signature));
            headers.add(new BasicNameValuePair(HEADER_TIMESTAMP, timestamp));

            Logger.getLogger(this.getClass().getName()).info(path);

            HttpResponse response = null;

            response = HttpUtils.getJson(headers, url, 120000);

            HttpEntity respEntity = response.getEntity();
            if (respEntity != null) {
                try (InputStream inStream = respEntity.getContent()) {
                    String result = new BufferedReader(
                            new InputStreamReader(inStream, StandardCharsets.UTF_8)).readLine();
                    Logger.getLogger(this.getClass().getName()).info(gson.toJson(result));
                    baseResponse = gson.fromJson(result, BaseResponse.class);
                    if (baseResponse.getError() != null) {
                        return null;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return baseResponse.getData().getOrder();
    }

}
