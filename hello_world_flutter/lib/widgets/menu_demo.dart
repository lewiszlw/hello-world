import 'package:flutter/material.dart';

/// PopupMenuButton控件即弹出菜单控件，点击控件会出现菜单
class MenusDemo extends StatefulWidget {
  @override
  State createState() => new _MenusDemoState();
}

class _MenusDemoState extends State<MenusDemo> {
  String _bodyStr = "显示菜单的点击";
  @override
  Widget build(BuildContext context) {
    return new Scaffold(
      appBar: new AppBar(
        title: new Text("PopupMenuButton"),
        actions: <Widget>[
          new PopupMenuButton(
            itemBuilder: (BuildContext context) => <PopupMenuItem<String>>[
              new PopupMenuItem(child: new Text("选项一"), value: "选项一的值",),
              new PopupMenuItem(child: new Text("选项二"), value: "选项二的值",),
            ],
            onSelected: (String value) {
              setState(() {
                _bodyStr = value;
              });
            },
          )
        ],
      ),
      body: new Center(child: new Text(_bodyStr),),
    );
  }
}

void main() {
  runApp(new MaterialApp(
    title: "demo",
    home: new MenusDemo(),
  ));
}