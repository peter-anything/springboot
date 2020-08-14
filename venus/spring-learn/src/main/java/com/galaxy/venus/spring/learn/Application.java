package com.galaxy.venus.spring.learn;

import com.galaxy.venus.spring.learn.factorybean.IPrint;
import com.galaxy.venus.spring.learn.factorybean.SpiFactoryBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

@SpringBootApplication
public class Application {
    public Application(IPrint printProxy) {
        printProxy.execute(10, "log print");
        printProxy.execute(0, "console print");
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public SpiFactoryBean printSpiProxy(ApplicationContext context) {
        return new SpiFactoryBean(context, IPrint.class);
    }

    @Primary
    @Bean
    public IPrint printSpiProxy(SpiFactoryBean spiFactoryBean) throws Exception {
        return (IPrint) spiFactoryBean.getObject();
    }
}
