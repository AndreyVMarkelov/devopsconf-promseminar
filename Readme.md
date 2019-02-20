Demo project for Devopspro 2018

### 1. Build all images
```
mvn clean package docker:build
```

### 2. Run sidecar inside container
```
docker-compose -f run_fatimage/docker-compose.yml up
```

#### 2.1 Go to service discovery: http://localhost:8761/
#### 2.2 Go to health check: http://localhost:8082/actuator/health
#### 2.3 Go to metrics: http://localhost:8082/actuator/prometheus

### 3. Run sidecar in separate container
```
docker-compose -f run_thinimage/docker-compose.yml up
```

#### 3.1 Go to service discovery: http://localhost:8761/
#### 3.2 Go to health check: http://localhost:8083/actuator/health
