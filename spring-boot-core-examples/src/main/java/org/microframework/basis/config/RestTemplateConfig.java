package org.microframework.basis.config;

import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;
import org.apache.http.Header;
import org.apache.http.HeaderElement;
import org.apache.http.HeaderElementIterator;
import org.apache.http.HttpHost;
import org.apache.http.client.HttpClient;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.ConnectionKeepAliveStrategy;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicHeaderElementIterator;
import org.apache.http.protocol.HTTP;
import org.apache.http.ssl.SSLContextBuilder;
import org.microframework.basis.properties.HttpClientPoolProperties;
import org.microframework.java.util.ObjectUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.client.OkHttp3ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import java.nio.charset.Charset;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

/**
 * @author Shaoyu Liu
 * @date 2023/12/4
 */
@Configuration
public class RestTemplateConfig {

    @Value("${spring.http.connect-timeout:100}")
    private Integer connectTimeout;

    @Value("${spring.http.read-timeout:100}")
    private Integer readTimeout;

    @Value("${spring.http.write-timeout:100}")
    private Integer writeTimeout;

    /**
     * 连接池最大连接数
     */
    @Value("${spring.http.max-idle-connections:100}")
    private Integer maxIdleConnections;

    /**
     * 连接池长连接保持时间
     */
    @Value("${spring.http.keep-alive-duration:5}")
    private Long keepAliveDuration;

    @Autowired
    private HttpClientPoolProperties httpProperties;

    private static final Logger log = LoggerFactory.getLogger(RestTemplateConfig.class);
    /**
     @Autowired
     @Qualifier("restTemplateDefault") private RestTemplate restTemplateDefault;

     @Autowired
     @Qualifier("okHttpClientTemplate") private RestTemplate okHttpClientTemplate;

     @Autowired
     @Qualifier("httpClientTemplate") private RestTemplate httpClientTemplate;
     */

    /**
     * 一、配置RestTemplate HttpURLConnection实现方式（默认实现方式）
     */
    @Bean(name = "restTemplateDefault")
    public RestTemplate restTemplateDefault() {
        ClientHttpRequestFactory clientHttpRequestFactory = simpleClientHttpRequestFactory();
        return new RestTemplate(clientHttpRequestFactory);
    }

    /**
     * 二、配置RestTemplate okhttp实现方式
     */
    @Bean(name = "okHttpClientTemplate")
    public RestTemplate okHttpClientTemplate() {
        ClientHttpRequestFactory clientHttpRequestFactory = okHttp3ClientHttpRequestFactory();
        return new RestTemplate(clientHttpRequestFactory);
    }

    /**
     * 三、配置RestTemplate httpclient实现方式
     */
    @Bean(name = "httpClientTemplate")
    public RestTemplate httpClientTemplate() {
        ClientHttpRequestFactory clientHttpRequestFactory = httpComponentsClientHttpRequestFactory();
        return createRestTemplate(clientHttpRequestFactory);
    }

    /**
     * 配置RestTemplate的实现方式
     *
     * @param factory
     * @return
     */
    @Bean
    public RestTemplate restTemplateOkHttp(ClientHttpRequestFactory factory) {
        ClientHttpRequestFactory clientHttpRequestFactory = okHttp3ClientHttpRequestFactory();
        RestTemplate restTemplate = new RestTemplate(clientHttpRequestFactory);
        // 可以添加消息转换
        //restTemplate.setMessageConverters(...);
        // 可以增加拦截器
        //restTemplate.setInterceptors(...);
        return restTemplate;
    }


    /**
     * 1.RestTemplate 默认请求 SimpleClientHttpRequestFactory
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

    /**
     * 2.RestTemplate 配置 OkHttp3ClientHttpRequestFactory
     *
     * @return
     * @see SimpleClientHttpRequestFactory
     * @see java.net.HttpURLConnection
     * @see HttpComponentsClientHttpRequestFactory
     */
    public ClientHttpRequestFactory okHttp3ClientHttpRequestFactory() {
        return new OkHttp3ClientHttpRequestFactory(okHttpConfigClient());
    }

    public OkHttpClient okHttpConfigClient() {
        return new OkHttpClient().newBuilder()
                // 连接池配置
                .connectionPool(pool())
                // 设置连接超时时间
                .connectTimeout(connectTimeout, TimeUnit.SECONDS)
                // 设置读取超时时间
                .readTimeout(readTimeout, TimeUnit.SECONDS)
                // 设置写入超时时间
                .writeTimeout(writeTimeout, TimeUnit.SECONDS)
                .hostnameVerifier((hostname, session) -> true)
                // 是否自动重试连接失败的请求
                .retryOnConnectionFailure(false)
                // 设置代理
//              .proxy(new Proxy(Proxy.Type.HTTP, new InetSocketAddress("127.0.0.1", 8888)))
                // 拦截器
//                .addInterceptor()
                .build();
    }

    public ConnectionPool pool() {
        return new ConnectionPool(maxIdleConnections, keepAliveDuration, TimeUnit.SECONDS);
    }


    /**
     * 创建HTTP客户端工厂
     */
    @Bean(name = "httpComponentsClientHttpRequestFactory")
    public ClientHttpRequestFactory httpComponentsClientHttpRequestFactory() {
        /**
         *  maxTotalConnection 和 maxConnectionPerRoute 必须要配
         */
        if (httpProperties.getMaxTotalConnect() <= 0) {
            throw new IllegalArgumentException("invalid maxTotalConnection: " + httpProperties.getMaxTotalConnect());
        }
        if (httpProperties.getMaxConnectPerRoute() <= 0) {
            throw new IllegalArgumentException("invalid maxConnectionPerRoute: " + httpProperties.getMaxConnectPerRoute());
        }
        HttpComponentsClientHttpRequestFactory clientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory(httpClient());
        // 连接超时
        clientHttpRequestFactory.setConnectTimeout(httpProperties.getConnectTimeout());
        // 数据读取超时时间，即SocketTimeout
        clientHttpRequestFactory.setReadTimeout(httpProperties.getReadTimeout());
        // 从连接池获取请求连接的超时时间，不宜过长，必须设置，比如连接不够用时，时间过长将是灾难性的
        clientHttpRequestFactory.setConnectionRequestTimeout(httpProperties.getConnectionRequestTimeout());
        return clientHttpRequestFactory;
    }


    /**
     * 初始化支持异步的RestTemplate,并加入spring的Bean工厂，由spring统一管理,如果你用不到异步，则无须创建该对象
     * 这个类过时了
     * @return
     */
/*  @Bean(name = "asyncRestTemplate")
  @ConditionalOnMissingBean(AsyncRestTemplate.class)
  public AsyncRestTemplate asyncRestTemplate(RestTemplate restTemplate) {
    final Netty4ClientHttpRequestFactory factory = new Netty4ClientHttpRequestFactory();
    factory.setConnectTimeout(this.connectionTimeout);
    factory.setReadTimeout(this.readTimeout);
    return new AsyncRestTemplate(factory, restTemplate);
  }*/

    /**
     * 配置httpClient
     *
     * @return
     */
    @Bean
    public HttpClient httpClient() {
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
        try {
            //设置信任ssl访问
            SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, (arg0, arg1) -> true).build();

            httpClientBuilder.setSSLContext(sslContext);
            HostnameVerifier hostnameVerifier = NoopHostnameVerifier.INSTANCE;
            SSLConnectionSocketFactory sslConnectionSocketFactory = new SSLConnectionSocketFactory(sslContext, hostnameVerifier);
            Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
                    // 注册http和https请求
                    .register("http", PlainConnectionSocketFactory.getSocketFactory())
                    .register("https", sslConnectionSocketFactory).build();

            //使用Httpclient连接池的方式配置(推荐)，同时支持netty，okHttp以及其他http框架
            PoolingHttpClientConnectionManager poolingHttpClientConnectionManager = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
            // 最大连接数
            poolingHttpClientConnectionManager.setMaxTotal(httpProperties.getMaxTotalConnect());
            // 同路由并发数
            poolingHttpClientConnectionManager.setDefaultMaxPerRoute(httpProperties.getMaxConnectPerRoute());
            //配置连接池
            httpClientBuilder.setConnectionManager(poolingHttpClientConnectionManager);
            // 重试次数
            httpClientBuilder.setRetryHandler(new DefaultHttpRequestRetryHandler(httpProperties.getRetryTimes(), true));

            //设置默认请求头
            List<Header> headers = getDefaultHeaders();
            httpClientBuilder.setDefaultHeaders(headers);
            //设置长连接保持策略
            httpClientBuilder.setKeepAliveStrategy(connectionKeepAliveStrategy());
            return httpClientBuilder.build();
        } catch (KeyManagementException | NoSuchAlgorithmException | KeyStoreException e) {
            log.error("初始化HTTP连接池出错", e);
        }
        return null;
    }


    /**
     * 配置长连接保持策略
     *
     * @return
     */
    public ConnectionKeepAliveStrategy connectionKeepAliveStrategy() {
        return (response, context) -> {
            // Honor 'keep-alive' header
            HeaderElementIterator it = new BasicHeaderElementIterator(
                    response.headerIterator(HTTP.CONN_KEEP_ALIVE));
            while (it.hasNext()) {
                HeaderElement he = it.nextElement();
                log.info("HeaderElement:{}", ObjectUtil.convertJson(he));
                String param = he.getName();
                String value = he.getValue();
                if (value != null && "timeout".equalsIgnoreCase(param)) {
                    try {
                        return Long.parseLong(value) * 1000;
                    } catch (NumberFormatException ignore) {
                        log.error("解析长连接过期时间异常", ignore);
                    }
                }
            }
            HttpHost target = (HttpHost) context.getAttribute(
                    HttpClientContext.HTTP_TARGET_HOST);
            //如果请求目标地址,单独配置了长连接保持时间,使用该配置
            Optional<Map.Entry<String, Integer>> any = Optional.ofNullable(httpProperties.getKeepAliveTargetHost()).orElseGet(HashMap::new)
                    .entrySet().stream().filter(
                            e -> e.getKey().equalsIgnoreCase(target.getHostName())).findAny();
            //否则使用默认长连接保持时间
            return any.map(en -> en.getValue() * 1000L).orElse(httpProperties.getKeepAliveTime() * 1000L);
        };
    }


    /**
     * 设置请求头
     *
     * @return
     */
    private List<Header> getDefaultHeaders() {
        List<Header> headers = new ArrayList<>();
        headers.add(new BasicHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/31.0.1650.16 Safari/537.36"));
        headers.add(new BasicHeader("Accept-Encoding", "gzip,deflate"));
        headers.add(new BasicHeader("Accept-Language", "zh-CN"));
        headers.add(new BasicHeader("Connection", "Keep-Alive"));
        return headers;
    }


    private RestTemplate createRestTemplate(ClientHttpRequestFactory factory) {
        RestTemplate restTemplate = new RestTemplate(factory);

        //我们采用RestTemplate内部的MessageConverter
        //重新设置StringHttpMessageConverter字符集，解决中文乱码问题
        modifyDefaultCharset(restTemplate);

        //设置错误处理器
        restTemplate.setErrorHandler(new DefaultResponseErrorHandler());

        return restTemplate;
    }

    /**
     * 修改默认的字符集类型为utf-8
     *
     * @param restTemplate
     */
    private void modifyDefaultCharset(RestTemplate restTemplate) {
        List<HttpMessageConverter<?>> converterList = restTemplate.getMessageConverters();
        HttpMessageConverter<?> converterTarget = null;
        for (HttpMessageConverter<?> item : converterList) {
            if (StringHttpMessageConverter.class == item.getClass()) {
                converterTarget = item;
                break;
            }
        }
        if (null != converterTarget) {
            converterList.remove(converterTarget);
        }
        Charset defaultCharset = Charset.forName(httpProperties.getCharset());
        converterList.add(1, new StringHttpMessageConverter(defaultCharset));
    }


}
