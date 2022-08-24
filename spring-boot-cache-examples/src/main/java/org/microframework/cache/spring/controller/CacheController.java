package org.microframework.cache.spring.controller;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Shaoyu Liu
 * @date 2022-08-24
 */
@RestController
@RequestMapping("cache")
public class CacheController {
    /**
     * 通过 @Cacheable 为方法启用spring cache
     * @return
     */
    @Cacheable("CacheController-list")
    @GetMapping("list")
    public List<Object> list() {
        return new ArrayList<>();
    }
}
