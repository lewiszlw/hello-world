import 'package:flutter/material.dart';

/// 无状态控件
class MyAppDemo extends StatelessWidget {

  final String data;

  const MyAppDemo({
    Key key,
    this.data,
  }) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return new Scaffold(
      appBar: new AppBar(
        title: new Text("Flutter应用"),
      ),
      body: new Center(
        child: new Text(data),
      ),
    );
  }
}



/// 有状态控件
class MyAppStateful extends StatefulWidget {
  final int increase;

  const MyAppStateful({
    Key key,
    this.increase
  }) : super(key: key);

  @override
  State createState() {
    return new _MyAppState();
  }
}

class _MyAppState extends State<MyAppStateful> {
  int _count = 0;

  void _increment() {
    setState(() {
      _count += widget.increase;
    });
  }

  @override
  Widget build(BuildContext context) {
    return new Scaffold(
      appBar: new AppBar(
        title: new Text("Flutter应用"),
      ),
      body: new Center(
        child: new Text("按钮点击 $_count 次"),
      ),
      floatingActionButton: new FloatingActionButton(
        onPressed: _increment,
        tooltip: "增加",
        child: new Icon(Icons.add),
      ),
    );
  }
}