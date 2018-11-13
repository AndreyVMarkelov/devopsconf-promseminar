package test.devopspro.redissidecar.monitoring;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.micrometer.core.instrument.util.IOUtils;
import io.prometheus.client.Collector;
import io.prometheus.client.CollectorRegistry;
import io.prometheus.client.Gauge;

@Component
public class RedisMetrics extends Collector {
    private static final Gauge usedMemory = Gauge.build("redis_used_memory", "How much memory used").create();
    private static final Gauge keysCount = Gauge.build("redis_keys_count", "How many keys").create();

    @Autowired
    private CollectorRegistry collectorRegistry;

    @PostConstruct
    public void init() {
        collectorRegistry.register(this);
    }

    @Override
    public List<MetricFamilySamples> collect() {
        try {
            Process process = Runtime.getRuntime().exec("redis-cli info");
            String output = IOUtils.toString(process.getInputStream());

            String[] lines = output.split("\\r?\\n");
            for (String line : lines) {
                if (line.startsWith("used_memory:")) {
                    usedMemory.set(Double.parseDouble(line.substring("used_memory:".length())));
                }
                if (line.startsWith("db0:keys=")) {
                    keysCount.set(Double.parseDouble(line.substring("db0:keys=".length(), line.indexOf(","))));
                }
            }
        } catch (Exception ex) {
            // log or something
        }

        List<MetricFamilySamples> result = new ArrayList<>();
        result.addAll(usedMemory.collect());
        result.addAll(keysCount.collect());
        return result;
    }

}
