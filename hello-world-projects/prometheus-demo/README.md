# Prometheus demo
学习搭建 Prometheus 环境、代码埋点上报。

## 搭建环境
1. `docker compose up -d`启动prometheus和grafana容器
2. 访问 http://localhost:9090 查看prometheus官方UI
3. 访问Grafana http://localhost:3000 ，初始账号密码为 admin/admin
4. 配置 Prometheus 数据源，注意 Access 为 Browser
5. 引入 [JVM 模板](https://grafana.com/grafana/dashboards/4701)

## 启动本项目

`curl -v http://localhost:8088/actuator/prometheus` 可看到 metrics。

1. 访问 http://localhost:8088/hello 即可计数一次。 在 Prometheus 官方 UI 中，搜索 prometheus_demo_requests_method_count_xxx 即可看到指标的曲线图。
2. 访问 http://localhost:8088/online?gauge=100 上报当前指标值。在 Prometheus 官方 UI 中，搜索 prometheus_demo_online_gauge 即可看到指标的曲线图。

## 将指标添加到 grafana
创建 dashboard，并添加 panel，输入 PromQL query，如
```
app_online_gauge{application="prometheus-demo", instance="docker.for.mac.localhost:8088", job="prometheus-demo"}
app_requests_method_count_total{application="prometheus-demo", instance="docker.for.mac.localhost:8088", job="prometheus-demo", method="ApiController.hello"}
```

## 监控机器指标 Node Exporter

1.启动 node_exporter `sudo docker run --name node_exporter --network="host" --pid="host" -v "/:/host:ro" prom/node-exporter:v1.0.0 --path.rootfs="/host`（不支持 macOS）

2.添加 job 到 prometheus.yaml
```yaml
- job_name: node
  honor_timestamps: true
  scrape_interval: 15s
  scrape_timeout: 10s
  metrics_path: /metrics
  scheme: http
  follow_redirects: true
  static_configs:
  - targets:
    - 10.224.104.150:9100
```

3.引入 [Node Exporter 模板](https://grafana.com/grafana/dashboards/8919)，
