package com.nju.mystore.configure;

import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.nju.mystore.exception.BlueWhaleException;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "alipay")
@Component
@Getter
@Setter
public class AlipayTools {

    private String serverUrl;

    private String appId;

    private String appPrivateKey;

    private String alipayPublicKey;

    private String notifyUrl;

    private String returnUrl;

    private static String format = "json";

    private static String charset = "utf-8";

    private static String signType = "RSA2";

    public String pay(String tradeName, String name, Double price) {
        AlipayClient alipayClient = new DefaultAlipayClient(serverUrl, appId, appPrivateKey, format, charset, alipayPublicKey, signType);
        AlipayTradePagePayRequest request = new AlipayTradePagePayRequest();
        request.setNotifyUrl(notifyUrl);
        request.setReturnUrl(returnUrl);
        JSONObject bizContent = new JSONObject();
        bizContent.put("out_trade_no", tradeName);
        bizContent.put("total_amount", price);
        bizContent.put("subject", name);
        bizContent.put("product_code", "FAST_INSTANT_TRADE_PAY");
        request.setBizContent(bizContent.toString());
        String form;
        try {
            form = alipayClient.pageExecute(request).getBody();
        } catch (Exception e) {
            throw BlueWhaleException.payError();
        }
        return form;
    }
}

