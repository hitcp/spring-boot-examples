package org.microframework.webflux.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * @author Shaoyu Liu
 * @date 2022-12-13
 */
public class HttpUtil {

    public static final Logger LOGGER = LoggerFactory.getLogger(HttpUtil.class);

    private static final String DEFAULT_ENC = StandardCharsets.UTF_8.name();

//    @Autowired
//    private static RestTemplate restTemplate;

    public static void main(String[] args) {
//        // GET
//        String getUrl = "http://jsonplaceholder.typicode.com/posts/5";
//        System.out.println(get(getUrl, new HashMap<>(), new HashMap<>(), String.class));
//
//        Map s = HttpUtil.get(getUrl, new HashMap<>(), new HashMap<>(), Map.class);
//        System.out.println(s);
//        restTemplate.exchange(getUrl, HttpMethod.GET, null, String.class);
//
//        // POST
//        // 请求地址
//        String postUrl = "http://jsonplaceholder.typicode.com/posts";
//
//        // 请求头设置
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//
//        //提交参数设置
//        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
//        map.add("userId", "222");
//        map.add("title", "abc");
//        map.add("body", "航歌");
//
//        // 组装请求体
//        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);
//
//        // 发送post请求，并输出结果
//        restTemplate.exchange(postUrl, HttpMethod.POST, request, String.class);
//
//        Map s = HttpUtil.post(getUrl, new HashMap<>(), new HashMap<>(), Map.class);
    }

    // TODO header验证 请求接口异常验证
    public static <R> R get(String url, Map<String, String> header, Map<String, Object> body, Class<? extends R> resultType) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        if (header != null && !header.isEmpty()) {
            header.forEach((s, o) -> httpHeaders.put(s, Collections.singletonList(o)));
        }

        Map<String, Object> uriVariables = new HashMap<>();
        if (body != null && !body.isEmpty()) {
            body.forEach((s, o) -> uriVariables.put(s, Collections.singletonList(o)));
        }

        ResponseEntity<? extends R> exchange = new RestTemplate().exchange(url, HttpMethod.GET, new HttpEntity<>(httpHeaders), resultType, uriVariables);
        if (HttpStatus.OK.value() == exchange.getStatusCodeValue()) {
            return exchange.getBody();
        } else {
            LOGGER.error("request url:{},request status:{},response detail:{}", url, exchange.getStatusCodeValue(), exchange);
            return null;
        }
//        return new RestTemplate().getForObject(url, resultType, uriVariables);
    }

    // TODO header验证 请求接口异常验证
    public static <R> R post(String url, Map<String, String> header, Map<String, String> body, Class<? extends R> resultType) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        if (header != null && !header.isEmpty()) {
            header.forEach((s, o) -> httpHeaders.put(s, Collections.singletonList(o)));
        }

        MultiValueMap<String, String> httpBody = new LinkedMultiValueMap<>();
        if (body != null && !body.isEmpty()) {
            body.forEach((s, o) -> httpBody.put(s, Collections.singletonList(o)));
        }

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(httpBody, httpHeaders);

        return new RestTemplate().postForObject(url, request, resultType, new HashMap<>());
    }

    private <R> void execute(String url, HttpMethod method, HttpEntity<?> requestEntity, Class<R> responseType, Map<String, Object> uriVariables) throws URISyntaxException {
        URI uri = buildUri(url, uriVariables);
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("HTTP method: {}, uri: {}, header:{}, body: {}", method, uri, requestEntity.getHeaders(), requestEntity.getBody());
        }
    }


    /**
     * build URI By url and query.
     *
     * @param url          url
     * @param uriVariables query param
     * @return {@link URI}
     */
    public URI buildUri(String url, Map<String, Object> uriVariables) throws URISyntaxException {
        if (uriVariables != null && !uriVariables.isEmpty()) {
            url = url + "?" + toQueryUrl(uriVariables);
        }
        return new URI(url);
    }

    /**
     * Print query as a http url param string. Like K=V&K=V.
     *
     * @return http url param string
     */
    public String toQueryUrl(Map<String, Object> uriVariables) {
        StringBuilder urlBuilder = new StringBuilder();
        Set<Map.Entry<String, Object>> entrySet = uriVariables.entrySet();
        int i = entrySet.size();
        for (Map.Entry<String, Object> entry : entrySet) {
            try {
                if (null != entry.getValue()) {
                    urlBuilder.append(entry.getKey()).append('=')
                            .append(URLEncoder.encode(String.valueOf(entry.getValue()), DEFAULT_ENC));
                    if (i > 1) {
                        urlBuilder.append('&');
                    }
                }
                i--;
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException(e);
            }
        }

        return urlBuilder.toString();
    }

}
