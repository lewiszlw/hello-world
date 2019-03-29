import 'package:flutter/material.dart';

class Counter extends StatefulWidget {

  @override
  State createState() {
    return new _CounterState();
  }
}

class CounterDisplay extends StatelessWidget {
  CounterDisplay({this.count});
  final int count;
  @override
  Widget build(BuildContext context) {
    return new Center(child: new Text("点击了 $count 次"),);
  }
}

class CounterIncrementor extends StatelessWidget {
  CounterIncrementor({this.onPressed});
  final VoidCallback onPressed;
  @override
  Widget build(BuildContext context) {
    return new FloatingActionButton(
      onPressed: onPressed,
      child: new Icon(Icons.add),
      tooltip: "增加",
    );
  }
}

class _CounterState extends State<Counter> {
  var _count = 0;
  void _increment() {
    setState(() {
      _count++;
    });
  }
  @override
  Widget build(BuildContext context) {
    return new Scaffold(
      appBar: new AppBar(title: new Text("计数器"),),
      body: new CounterDisplay(count: _count,),
      floatingActionButton: new CounterIncrementor(onPressed: _increment,),
    );
  }
}

void main() {
  runApp(new MaterialApp(title: "计数器", home: new Counter(),));
}