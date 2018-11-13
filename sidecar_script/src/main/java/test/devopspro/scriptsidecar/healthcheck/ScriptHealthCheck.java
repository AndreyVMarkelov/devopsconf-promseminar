package test.devopspro.scriptsidecar.healthcheck;

import java.io.File;

import javax.annotation.PostConstruct;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;

import groovy.lang.GroovyShell;
import groovy.lang.Script;

public class ScriptHealthCheck implements HealthIndicator {
    private static final GroovyShell shell = new GroovyShell(ScriptHealthCheck.class.getClassLoader());

    @Value("${script.health:}")
    private String scriptPath;

    private Script script = null;

    @PostConstruct
    public void init() {
        if (StringUtils.isNotBlank(scriptPath)) {
            try {
                script = shell.parse(new File(scriptPath));
            } catch (Exception e) {
                throw new BeanCreationException("Invalid health check script", e);
            }
        }
    }

    @Override
    public Health health() {
        if (script == null) {
            return Health.up().build();
        }

        try {
            Object result = script.run();
            if (result instanceof Boolean && (Boolean)result) {
                return Health.up().build();
            }
            return Health.down().build();
        } catch (Exception e) {
            return Health.down().withException(e).build();
        }
    }

}
