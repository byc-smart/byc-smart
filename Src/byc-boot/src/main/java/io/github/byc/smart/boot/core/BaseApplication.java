package io.github.byc.smart.boot.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author : bamboo(kan.zhang-cn@hotmail.com)
 */
@EnableAutoConfiguration
@EnableTransactionManagement
// todo 缺少bean名称生成器
@ComponentScan(basePackages = {"$sys.basePackage"})
public class BaseApplication {
    public static void runApp(String[] args,Class<?> clazz){
        SpringApplication run = new SpringApplication(new Class[]{clazz});
        run.run(args);
    }

    public static void main(String[] args) {
        runApp(args,BaseApplication.class);
    }
}
