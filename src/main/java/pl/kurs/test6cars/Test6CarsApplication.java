package pl.kurs.test6cars;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@EnableAspectJAutoProxy(proxyTargetClass=true)
@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class Test6CarsApplication {

    public static void main(String[] args) {
        SpringApplication.run(Test6CarsApplication.class, args);
    }

}
