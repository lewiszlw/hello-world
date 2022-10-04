import 'package:flutter/gestures.dart';
import 'package:flutter/material.dart';

/// 展示各类文本

class HomePage extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
        appBar: AppBar(title: Text("各类文本")),
        body: Column(  // Column默认居中
          children: [
            Text(
              "右对齐文本",
              textAlign: TextAlign.right,
            ),
            Text(
              "省略文本" * 100,
              maxLines: 1,
              overflow: TextOverflow.ellipsis,
            ),
            Text(
              "放大文本",
              textScaleFactor: 1.5,
            ),
            Text(
              "设置文本样式",
              style: TextStyle(
                  color: Colors.blue,
                  fontSize: 18.0,
                  height: 1.2,
                  fontFamily: "Courier",
                  background: Paint()..color = Colors.yellow,
                  decoration: TextDecoration.underline,
                  decorationStyle: TextDecorationStyle.dashed),
            ),
            Text.rich(TextSpan(children: [
              TextSpan(text: "链接文本"),
              TextSpan(
                  text: "https://flutterchina.club",
                  style: TextStyle(color: Colors.blue),
                  recognizer: TapGestureRecognizer()..onTap),
            ])),
          ],
        ));
  }
}

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: HomePage(),
    );
  }
}

void main() => runApp(MyApp());
