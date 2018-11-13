package test.devopspro.scriptsidecar.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import test.devopspro.scriptsidecar.healthcheck.ScriptHealthCheck;

@RestController
public class LocalStatusDelegatorController {
    @Autowired
    private ScriptHealthCheck redisHealthCheck;

    @RequestMapping("/delegating-status")
    public Health sidecarHealthStatus() {
        return redisHealthCheck.health();
    }
}
