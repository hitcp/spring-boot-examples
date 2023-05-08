package cn.hitcp.mapper;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Shaoyu Liu
 * @date 2023/5/8
 */
@MapperScan(basePackages = "cn.hitcp.mapper")
@Configuration
public class MybatisPlusConfig {
    @Bean
    public CustomizedSqlInjector customizedSqlInjector() {
        return new CustomizedSqlInjector();
    }
}
