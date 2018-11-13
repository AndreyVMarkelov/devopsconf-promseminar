package test.devopspro.redissidecar.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import test.devopspro.redissidecar.healthcheck.RedisHealthCheck;

@RestController
public class LocalStatusDelegatorController {
    @Autowired
    private RedisHealthCheck redisHealthCheck;

    @RequestMapping("/delegating-status")
    public Health sidecarHealthStatus() {
        return redisHealthCheck.health();
    }
}
