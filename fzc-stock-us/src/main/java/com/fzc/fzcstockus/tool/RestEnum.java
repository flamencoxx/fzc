package com.fzc.fzcstockus.tool;



import com.fzc.fzcstockus.Interceptor.HttpLoggingInterceptor;
import lombok.Getter;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 11615
 */
@Getter
public enum RestEnum {
    /**
     * RestTemplate 单例
     */
    SINGLE_INSTANCE;
    private RestTemplate restTemplate;
    RestEnum() {
        // 设置超时
        restTemplate = new RestTemplateBuilder()
                .setConnectTimeout(Duration.ofMillis(5000))
                .setReadTimeout(Duration.ofMillis(5000))
                .build();

        // 设置日志拦截
        ClientHttpRequestInterceptor ri = new HttpLoggingInterceptor();
        List<ClientHttpRequestInterceptor> ris = new ArrayList<>();
        ris.add(ri);
        restTemplate.setInterceptors(ris);
        restTemplate.setRequestFactory(new BufferingClientHttpRequestFactory(new SimpleClientHttpRequestFactory()));
    }

}
