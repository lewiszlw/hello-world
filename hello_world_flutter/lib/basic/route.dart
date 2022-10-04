import 'package:flutter/material.dart';

/// flutter 路由管理
/// 
/// Error: Navigator operation requested with a context that does not include a Navigator.
/// 如果在Widget中需要使用Navigator导航，则必须将该Widget必须作为MaterialApp的子Widget，
/// 并且context（实际上是Element）也必须是MaterialApp对应的context的子context。

class TipRoute extends StatelessWidget {
  TipRoute({Key? key, required this.text}) : super(key: key);

  final String text;

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: Text("提示")),
      body: Padding(
        padding: EdgeInsets.all(20), // 向上下左右各添加18像素补白
        child: Center(
            child: Column(
          children: [
            Text(text),
            ElevatedButton(
                onPressed: () => {Navigator.pop(context, "我是返回值")},
                child: Text("返回上一级"))
          ],
        )),
      ),
    );
  }
}

class DetailRoute extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    var args = ModalRoute.of(context)?.settings.arguments.toString();
    if (args == null) {
      args = "null";
    }
    return Scaffold(
      appBar: AppBar(title: Text("详情")),
      body: Padding(
        padding: EdgeInsets.all(20), // 向上下左右各添加18像素补白
        child: Center(
            child: Column(
          children: [
            Text(args),
            ElevatedButton(
                onPressed: () => {Navigator.pop(context, "我是详情页返回值")},
                child: Text("返回上一级"))
          ],
        )),
      ),
    );
  }
}

class HomePage extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: Text("首页")),
      body: Center(
          child: Column(
        children: [
          ElevatedButton(
            child: Text("打开提示页"),
            onPressed: () async {
              var result = await Navigator.push(context,
                  MaterialPageRoute(builder: (context) {
                return TipRoute(
                  text: "从首页过来",
                );
              }));
              print("路由返回值: $result");
            },
          ),
          ElevatedButton(
            child: Text("打开详情页"),
            onPressed: () async {
              var result = await Navigator.pushNamed(context, "detail_page", arguments: "命名路由参数");
              print("路由返回值: $result");
            },
          ),
        ],
      )),
    );
  }
}

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: HomePage(),

      // 命名路由
      // initialRoute: "/", //名为"/"的路由作为应用的home(首页)
      routes: {
        // "/": (context) => HomePage(),
        "detail_page": (context) => DetailRoute(),
      },
    );
  }
}

void main() => runApp(MyApp());
