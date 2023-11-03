package org.microframework.java.bean;

import org.apache.commons.collections4.multimap.AbstractListValuedMap;
import org.apache.commons.collections4.multimap.ArrayListValuedHashMap;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author Shaoyu Liu
 * @date 2023/11/3
 */
public class MultiValueMapTest {
    public static void main(String[] args) {
//        AbstractListValuedMap<String, String> map = new ArrayListValuedHashMap<>();
//        map.put("A", "1");
//        map.put("A", "2");
//        List<String> list = map.get("A");
//        System.out.println(list);
//
//
//        MultiValueMap<String, String> multiValueMap = new LinkedMultiValueMap<>();
//        multiValueMap.put("A", Arrays.asList("AV1", "AV2", "AV3"));
//        multiValueMap.put("B", Arrays.asList("BV1", "BV2", "BV3"));
//        multiValueMap.put("C", Arrays.asList("CV1", "CV2", "CV3"));
//        System.out.println(multiValueMap.get("A"));


        List<MultiValueMap<String, String>> attendanceData = new ArrayList<>();

        MultiValueMap<String, String> attendanceRecord = new LinkedMultiValueMap<>();
        attendanceRecord.put("姓名",Arrays.asList("张三"));
        attendanceRecord.put("1", Arrays.asList("9:00","12:15", "18:00"));
        attendanceRecord.put("2", Arrays.asList("9:00", "18:00","早退0小时"));
        attendanceRecord.put("3", Arrays.asList("9:00", "18:00"));
        attendanceData.add(attendanceRecord);


        MultiValueMap<String, String> attendanceRecord2 = new LinkedMultiValueMap<>();
        attendanceRecord2.put("姓名",Arrays.asList("李四"));
        attendanceRecord2.put("1", Arrays.asList("9:00", "18:00"));
        attendanceRecord2.put("2", Arrays.asList("9:00", "18:00","早退0小时"));
        attendanceRecord2.put("3", Arrays.asList("9:00", "18:00"));
        attendanceData.add(attendanceRecord2);

        attendanceData.forEach(System.out::println);

        // {姓名=[张三], 1=[9:00, 12:15, 18:00], 2=[9:00, 18:00, 早退0小时], 3=[9:00, 18:00]}
        //{姓名=[李四], 1=[9:00, 18:00], 2=[9:00, 18:00, 早退0小时], 3=[9:00, 18:00]}
    }
}
