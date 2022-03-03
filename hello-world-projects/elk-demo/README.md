# ELK Demo
ELK Stack/ Elastic Stack 介绍：
- https://www.elastic.co/cn/what-is/elk-stack
- https://www.elastic.co/cn/elastic-stack/

# 服务日志配置
1. 在application.properties使用一些默认配置
2. 创建日志配置文件，当classpath下如下文件时，springboot会自动加载来覆盖默认配置
   - logback-spring.xml（推荐）
   - logback.xml
   - logback-spring.groovy
   - logback.groovy

# 搭建ELK环境
执行`docker compose up -d`命令启动es、kibana和logstash容器。

https://www.elastic.co/guide/en/elastic-stack/current/installing-elastic-stack.html

1.Elasticsearch
- https://www.elastic.co/guide/en/elasticsearch/reference/7.16/docker.html
- 执行 `curl -X GET "localhost:9200/_cat/nodes?v=true&pretty"` 可查看 es nodes 是否正常运行中。
- http://localhost:9200/_cat/indices?v 可查看索引列表

2.Kibana
- https://www.elastic.co/guide/en/kibana/7.16/docker.html
- 访问 http://localhost:5601/ 可查看 kibana 页面。
- 需要在kibana中 Index Patterns 创建 index pattern，才能在 Discovery 中看到日志。

3.Logstash
- https://www.elastic.co/guide/en/logstash/7.16/docker-config.html
- https://www.elastic.co/guide/en/logstash/current/plugins-outputs-elasticsearch.html
- https://www.elastic.co/guide/en/logstash/current/plugins-filters-date.html
- https://www.elastic.co/guide/en/logstash/current/plugins-filters-grok.html
- https://www.elastic.co/guide/en/logstash/current/plugins-filters-prune.html
- `docker logs logstash`查看logstash运行日志
- http://grokdebug.herokuapp.com/ 可debug grok表达式
- https://stackoverflow.com/questions/28826995/grok-pattern-with-this-log-line

# 常见问题
**1.docker 挂载文件夹不更新**

docker挂载日志文件夹不及时更新甚至不更新，导致容器读取不到最新的log，必须在容器内执行 `cat elk-demo.log` 后，容器才会读到最新文件内容。

尝试 `chmod 666 logs` 、`chmod -R 777 logs` 等均不行。这个问题暂未解决。

**2.date filter 时区未设置，导致log似乎"消失"**

由于时区未设置，导致UTC+8时间转成UTC时间，然后在kibana显示时，由被转成UTC+8，导致时间增加了8小时，xxx时间-now之间查询不到该日志，因为该日志时间在now之后。
