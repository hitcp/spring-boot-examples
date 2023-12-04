package org.microframework.basis.config;

import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.client.OkHttp3ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.TimeUnit;

/**
 * @author Shaoyu Liu
 * @date 2023/12/4
 */
@Configuration
public class RestTemplateConfig {
    @Value("${ok.http.connect-timeout:100}")
    private Integer connectTimeout;

    @Value("${ok.http.read-timeout:100}")
    private Integer readTimeout;

    @Value("${ok.http.write-timeout:100}")
    private Integer writeTimeout;

    @Value("${ok.http.max-idle-connections:100}")
    private Integer maxIdleConnections;

    @Value("${ok.http.keep-alive-duration:100}")
    private Long keepAliveDuration;


    @Bean
    public RestTemplate restTemplateDefault(ClientHttpRequestFactory factory) {
        return new RestTemplate(factory);
    }

    @Bean
    public RestTemplate restTemplateOkHttp(ClientHttpRequestFactory factory) {
        ClientHttpRequestFactory clientHttpRequestFactory = httpRequestFactory();
        RestTemplate restTemplate = new RestTemplate(clientHttpRequestFactory);
        // 可以添加消息转换
        //restTemplate.setMessageConverters(...);
        // 可以增加拦截器
        //restTemplate.setInterceptors(...);
        return restTemplate;
    }


    /**
     * RestTemplate 默认请求 SimpleClientHttpRequestFactory
     *
     * @return
     * @see SimpleClientHttpRequestFactory
     * @see java.net.HttpURLConnection
     * @see HttpComponentsClientHttpRequestFactory
     */
    @Bean
    public ClientHttpRequestFactory simpleClientHttpRequestFactory() {
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        factory.setReadTimeout(5000);
        factory.setConnectTimeout(15000);
        // 设置代理
        //factory.setProxy(null);
        return factory;
    }


    public ClientHttpRequestFactory httpRequestFactory() {
        return new OkHttp3ClientHttpRequestFactory(okHttpConfigClient());
    }

    public OkHttpClient okHttpConfigClient() {
        return new OkHttpClient().newBuilder()
                .connectionPool(pool())
                .connectTimeout(connectTimeout, TimeUnit.SECONDS)
                .readTimeout(readTimeout, TimeUnit.SECONDS)
                .writeTimeout(writeTimeout, TimeUnit.SECONDS)
                .hostnameVerifier((hostname, session) -> true)
                // 设置代理
//              .proxy(new Proxy(Proxy.Type.HTTP, new InetSocketAddress("127.0.0.1", 8888)))
                // 拦截器
//                .addInterceptor()
                .build();
    }

    public ConnectionPool pool() {
        return new ConnectionPool(maxIdleConnections, keepAliveDuration, TimeUnit.SECONDS);
    }
}
