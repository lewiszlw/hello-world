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

## 搭建ELK环境
https://www.elastic.co/guide/en/elastic-stack/current/installing-elastic-stack.html

1.Elasticsearch
- https://www.elastic.co/guide/en/elasticsearch/reference/7.16/docker.html
- 执行 `curl -X GET "localhost:9200/_cat/nodes?v=true&pretty"` 可查看 es nodes 是否正常运行中。

2.Kibana
- https://www.elastic.co/guide/en/kibana/7.16/docker.html
- 访问 http://localhost:5601/ 可查看 kibana 页面。

3.Filebeat
- https://www.elastic.co/guide/en/beats/filebeat/7.16/filebeat-installation-configuration.html
- https://www.elastic.co/guide/en/beats/filebeat/7.16/filebeat-input-filestream.html
