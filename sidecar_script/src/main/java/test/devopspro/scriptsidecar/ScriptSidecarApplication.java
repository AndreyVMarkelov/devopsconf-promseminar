package test.devopspro.scriptsidecar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.sidecar.EnableSidecar;
import org.springframework.context.annotation.Bean;

import test.devopspro.scriptsidecar.healthcheck.ScriptHealthCheck;

@SpringBootApplication
@EnableSidecar
public class ScriptSidecarApplication {
    public static void main(String[] args) {
        SpringApplication.run(ScriptSidecarApplication.class, args);
    }

    @Bean
    public ScriptHealthCheck redisHealthCheck() {
        return new ScriptHealthCheck();
    }
}
