package org.microframework.webflux.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

/**
 * @author Shaoyu Liu
 * @date 2022-12-13
 */
public class RestTemplateTest {

    @Autowired
    private static RestTemplate restTemplate;

    public static void main(String[] args) {
        // GET
        String getUrl = "http://jsonplaceholder.typicode.com/posts/5";
        restTemplate.exchange(getUrl, HttpMethod.GET,null, String.class);

        // POST
        // 请求地址
        String postUrl = "http://jsonplaceholder.typicode.com/posts";

        // 请求头设置
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        //提交参数设置
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("userId", "222");
        map.add("title", "abc");
        map.add("body", "航歌");

        // 组装请求体
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);

        // 发送post请求，并输出结果
        restTemplate.exchange(postUrl, HttpMethod.POST, request, String.class);
    }
}
