package org.microframework.java.cache;//package com.liushaoyu.springcloud.util;
//
//import com.google.common.collect.Maps;
//import org.springframework.util.CollectionUtils;
//import org.springframework.util.ObjectUtils;
//
//import java.io.Serializable;
//import java.util.Arrays;
//import java.util.Collections;
//import java.util.List;
//import java.util.Map;
//import java.util.concurrent.ThreadLocalRandom;
//
///**
// * @author Shaoyu Liu
// * @date 2021/11/29 15:05
// **/
//public class CacheRedis {
//
//    private static final String redisKeyPrefix = "employee_change_record_translation_field:";
//
//    /**
//     * 根据id获取用户信息
//     *
//     * @param id     缓存对象id
//     * @param fields 缓存对象字段集合，例如：new String[]{"name", "username", "jobNum"}
//     * @return 字段对应的值
//     */
//    public List<String> getEmployeeById(Serializable id, String... fields) {
//        List<String> waitSearchFields = Arrays.asList(fields);
//        if (ObjectUtils.isEmpty(id) || CollectionUtils.isEmpty(waitSearchFields)) {
//            return Collections.emptyList();
//        }
//        String key = StringUtils.join(redisKeyPrefix + "EmployeeInfo:", id);
//        List<String> cacheFields = redisClient.hmget(key, waitSearchFields);
//        if (!CollectionUtils.isEmpty(cacheFields)) {
//            return cacheFields;
//        } else {
//            Employee employee = employeeService.selectById(id);
//            if (!ObjectUtils.isEmpty(employee)) {
//                cacheEmployeeFields(key, employee);
//                return redisClient.hmget(key, waitSearchFields);
//            } else {
//                // TODO put key = null 防止击穿
//                cacheEmployeeFields(key, null);
//            }
//        }
//        return Collections.emptyList();
//    }
//
//    private void cacheEmployeeInfo() {
//
//        List<Employee> employees = employeeService.selectList(new EntityWrapper<Employee>().eq("is_del", 0));
//
//        for (Employee employee : employees) {
//            String key = StringUtils.join(redisKeyPrefix, "EmployeeInfo:", employee.getId());
//            if (redisClient.exists(key)) {
//                continue;
//            }
//            cacheEmployeeFields(key, employee);
//        }
//    }
//
//    private void cacheEmployeeFields(String key, Employee employee) {
//        // 缓存2小时，并随机附加0-20分钟时间防止缓存同时失效
//        int additionalTime = ThreadLocalRandom.current().nextInt(1200);
//        int expireTime = 7200 + additionalTime;
//        Map<String, String> employeeInfoMap = Maps.newHashMap();
//        if (!ObjectUtils.isEmpty(employee)) {
//            employeeInfoMap.put("name", null);
//            employeeInfoMap.put("username", null);
//            employeeInfoMap.put("jobNum", null);
//            redisClient.hmset(key, null);
//        } else {
//            employeeInfoMap.put("name", employee.getName());
//            employeeInfoMap.put("username", employee.getUsername());
//            employeeInfoMap.put("jobNum", employee.getJobNum());
//            redisClient.hmset(key, employeeInfoMap);
//        }
//        redisClient.expire(key, expireTime);
//    }
//}
