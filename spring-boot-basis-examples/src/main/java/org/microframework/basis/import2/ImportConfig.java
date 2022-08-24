package org.microframework.basis.import2;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @Import 使用案例
 * 作用：注解的作用是导入一个配置Configuration类，1.如果一个类不在启动类所在包下；2.包名不以com.**开头，
 * @Import(UserConfig.class)
 * @CompentScan("com.**")
 * 执行顺序：ImportByRegistrar》ImportBySelector》ImportByClass
 *
 * @author Shaoyu Liu
 * @date 2022-08-23
 */
@Import({ImportByClass.class, ImportByRegistrar.class, ImportBySelector.class})
@Configuration
public class ImportConfig {


}
