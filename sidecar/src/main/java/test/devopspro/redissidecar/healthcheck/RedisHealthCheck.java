package test.devopspro.redissidecar.healthcheck;

import java.io.IOException;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;

import io.micrometer.core.instrument.util.IOUtils;

public class RedisHealthCheck implements HealthIndicator {

    @Override
    public Health health() {
        try {
            Process process = Runtime.getRuntime().exec("redis-cli ping");
            String output = IOUtils.toString(process.getInputStream());
            if (output.indexOf("PONG") != -1) {
                return Health.up().build();
            } else {
                return Health.down().withDetail("reason", output).build();
            }
        } catch (IOException e) {
            return Health.down().withException(e).build();
        }
    }

}
