# jvm性能分析demo

## 添加JVM启动参数
1.本地IDEA启动

Run/Debug Configuration -> VM Options
```
-XX:MetaspaceSize=64m -XX:MaxMetaspaceSize=64m -Xms128m -Xmx128m -Xmn64m -Xss384k -XX:MaxDirectMemorySize=32m
-XX:+UseConcMarkSweepGC -XX:+UseParNewGC -XX:+PrintGCDetails -XX:+PrintGCTimeStamps -Xloggc:./logs/gc-%t.log 
-XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=./logs/
```

2.命令行启动
```
$ java -jar {xxx} jvm-analysis-demo-1.0.0.jar
```

## 查看JVM启动参数
1.`jcmd {pid} VM.flags`

2.`jinfo -flags {pid}` 或 `jmap -heap {pid}`

3.`jps -v` 或 `ps -ef | grep java`

## Dump堆栈
1. `-XX:+HeapDumpOnOutOfMemoryError`当堆内存OOM时自动dump堆内存
2. 通过JMX的MBean生成当前的堆栈信息（可用于JVM监控）
3. `jmap -dump:live,format=b,file=heap.hprof {pid}` 或 `jcmd {pid} GC.heap_dump {file-path}` 例如，jcmd 2523 GC.heap_dump ~/Desktop/heap.hprof
4. `jstack {pid} > {file-path}` 或 `jcmd {pid} Thread.print > {file-path}` 例如，jstack 2523 > ~/Desktop/threaddump.txt

## 分析dump文件
- 堆dump文件 -> MAT, VisualVM, IDEA Profiler
- 栈dump文件 -> https://gceasy.io/

## JVM参数含义
- -XX:MetaspaceSize=128m （元空间默认大小） 
- -XX:MaxMetaspaceSize=128m （元空间最大大小）
- -Xms1024m （堆默认大小）
- -Xmx1024m （堆最大大小）
- -Xmn256m （年轻代大小，建议不要太大，太大可能会是YGC时间过长，影响吞吐量）
- -XX:MaxDirectMemorySize=64m （堆外内存大小）
- -Xss256k （栈最大深度大小）
- -XX:+UseConcMarkSweepGC （指定使用的垃圾收集器，这里使用CMS收集器）
- -XX:+UseParNewGC （年轻代收集器）
- -XX:+PrintGCDetails （打印详细的GC日志）
- -XX:+PrintGCTimeStamps （打印gc时间戳）
- -Xloggc:/appl/gc-%t.log （定义gc日志目录，建议加时间而不是-XX:+UseGCLogFileRotation -XX:NumberOfGCLogFiles=100）
- -XX:+HeapDumpOnOutOfMemoryError （当JVM发生OOM时，自动生成DUMP文件）
- -XX:HeapDumpPath=./logs （生成DUMP文件的路径）

## 相关资料
https://blog.gceasy.io/2020/03/18/7-jvm-arguments-of-highly-effective-applications/