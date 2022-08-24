package org.microframework.java.generate;

//import com.baomidou.mybatisplus.core.incrementer.IdentifierGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.util.AlternativeJdkIdGenerator;
import org.springframework.util.JdkIdGenerator;
import org.springframework.util.SimpleIdGenerator;

public class IdGenerate {
    public static void main(String[] args) {
        SimpleIdGenerator simpleIdGenerator = new SimpleIdGenerator();
        System.out.println(simpleIdGenerator.generateId());

        JdkIdGenerator jdkIdGenerator = new JdkIdGenerator();
        System.out.println(jdkIdGenerator.generateId());

        AlternativeJdkIdGenerator alternativeJdkIdGenerator = new AlternativeJdkIdGenerator();
        System.out.println(alternativeJdkIdGenerator.generateId());
    }

//    @Bean
//    public IdentifierGenerator idGenerator() {
////        return new ImadcnIdentifierGenerator("");
//        return null;
//    }
}
