package org.microframework.java.generate;

import org.springframework.util.AlternativeJdkIdGenerator;
import org.springframework.util.JdkIdGenerator;
import org.springframework.util.SimpleIdGenerator;

/**
 * UUID (Universally Unique Identifier) 全局唯一标识
 */
public class IdGenerate {
    public static void main(String[] args) {
        // 自增id
        SimpleIdGenerator simpleIdGenerator = new SimpleIdGenerator();
        System.out.println(simpleIdGenerator.generateId());
        System.out.println(simpleIdGenerator.generateId());

        // uuid (32位)
        JdkIdGenerator jdkIdGenerator = new JdkIdGenerator();
        System.out.println(jdkIdGenerator.generateId());

        // uuid (32位) 变动UUID最高/最低有效位,性能远远高于JdkIdGenerator 使用SecureRandom替换Random
        AlternativeJdkIdGenerator alternativeJdkIdGenerator = new AlternativeJdkIdGenerator();
        System.out.println(alternativeJdkIdGenerator.generateId());



        // 计算耗时 SimpleIdGenerator
        long simpleIdGeneratorStartTime = System.currentTimeMillis() ;
        for (int i = 0; i < 100000; i++) {
            simpleIdGenerator.generateId();
        }
        long simpleIdGeneratorCostTime = System.currentTimeMillis() - simpleIdGeneratorStartTime;
        System.out.println("simpleIdGeneratorStartTime:" + simpleIdGeneratorCostTime);


        // 计算耗时 JdkIdGenerator
        long jdkIdGeneratorStartTime = System.currentTimeMillis() ;
        for (int i = 0; i < 100000; i++) {
            jdkIdGenerator.generateId();
        }
        long jdkIdGeneratorCostTime = System.currentTimeMillis() - jdkIdGeneratorStartTime;
        System.out.println("jdkIdGeneratorStartTime:" + jdkIdGeneratorCostTime);


        // 计算耗时 AlternativeJdkIdGenerator
        long alternativeJdkIdGeneratorStartTime = System.currentTimeMillis() ;
        for (int i = 0; i < 100000; i++) {
            alternativeJdkIdGenerator.generateId();
        }
        long alternativeJdkIdGeneratorCostTime = System.currentTimeMillis() - alternativeJdkIdGeneratorStartTime;
        System.out.println("AlternativeJdkIdGenerator:" + alternativeJdkIdGeneratorCostTime);
    }

}
