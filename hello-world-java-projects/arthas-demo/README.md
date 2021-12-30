# arthas-demo
https://arthas.aliyun.com/

## 启动和退出arthas
```
curl -O https://arthas.aliyun.com/arthas-boot.jar

# 低版本jdk attach 高版本jdk 会出现 java.io.IOException: Non-numeric value found - int expected
/Users/lewis/Library/Java/JavaVirtualMachines/openjdk-17/Contents/Home/bin/java -jar arthas-boot.jar 
```
选择目标程序进程编号，arthas会attach到目标进程。

如果只是退出当前的连接，可以用quit或者exit命令。Attach到目标进程上的arthas还会继续运行，端口会保持开放，下次连接时可以直接连接上。
如果想完全退出arthas，可以执行stop命令。

## arthas指令
### help
```
[arthas@41921]$ help
[arthas@41921]$ help dashboard
```

### dashboard

```
# 可查看进程、内存（堆、metaspace、堆外内存等）、运行时（os、jdk版本等）
[arthas@41921]$ dashboard
```

### thread
```
# 展示线程列表
[arthas@41921]$ thread

# 获取线程 ID 28 的信息
[arthas@41921]$ thread 28
```

### jad
```
# 反编译class
[arthas@41921]$ jad com.github.lewiszlw.arthasdemo.ApiController
```

### watch
```
# 查看函数返回值
[arthas@41921]$ watch com.github.lewiszlw.arthasdemo.ApiController hello returnObj

# 查看函数入参和返回值, -x表示遍历深度，可以调整来打印具体的参数和结果内容，默认值是1
[arthas@1151]$ watch com.github.lewiszlw.arthasdemo.ApiController queryNames "{params, returnObj}" -x 2
```

### trace
```
# 当前方法下第一层调用路径耗时
[arthas@1151]$ trace com.github.lewiszlw.arthasdemo.ApiController queryNames

# 动态trace。想要深入子函数queryNamesByAge，可以打开一个新终端2，使用telnet localhost 3658 或者 启动arthas并attach相同进程 连接上arthas，再trace 
[arthas@1151]$ trace com.github.lewiszlw.arthasdemo.service.AlphaService queryNamesByAge --listenerId 10
```