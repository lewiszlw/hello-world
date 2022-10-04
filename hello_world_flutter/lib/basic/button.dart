import 'package:flutter/material.dart';

/// 展示各类按钮


class HomePage extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: Text("各类按钮")),
      body: Center(
          child: Column(
        children: [
          ElevatedButton(onPressed: () => {}, child: Text("漂浮按钮")),
          TextButton(onPressed: () => {}, child: Text("文本按钮")),
          OutlinedButton(onPressed: () => {}, child: Text("带边框按钮")),
          IconButton(onPressed: () => {}, icon: Icon(Icons.thumb_up)),

          ElevatedButton.icon(onPressed: () => {}, label: Text("带图标漂浮按钮"), icon: Icon(Icons.send)),
          TextButton.icon(onPressed: () => {}, label: Text("带图标文本按钮"), icon: Icon(Icons.add),),
          OutlinedButton.icon(onPressed: () => {}, label: Text("带图标带边框按钮"), icon: Icon(Icons.info),),
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
    );
  }
}

void main() => runApp(MyApp());
