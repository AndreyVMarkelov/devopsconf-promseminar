package test.devopspro.redissidecar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.sidecar.EnableSidecar;
import org.springframework.context.annotation.Bean;

import test.devopspro.redissidecar.healthcheck.RedisHealthCheck;

@SpringBootApplication
@EnableSidecar
public class SidecarApplication {
    public static void main(String[] args) {
        SpringApplication.run(SidecarApplication.class, args);
    }

    @Bean
    public RedisHealthCheck redisHealthCheck() {
        return new RedisHealthCheck();
    }
}
