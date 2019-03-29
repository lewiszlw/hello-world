import 'package:flutter/material.dart';

class ContainerDemo extends StatelessWidget {

  @override
  Widget build(BuildContext context) {
    return new Center(
      child: new Container(
        width: 300.0,
        height: 400.0,
        decoration: new BoxDecoration(
          color: Colors.lightBlueAccent[100],
          border: new Border.all(color: const Color(0xff6d9eeb), width: 8.0)
        ),
        child: new Text("容器演示"),
      ),
    );
  }
}

void main() {
  runApp(new MaterialApp(
    title: "container demo",
    home: new ContainerDemo(),
  ));
}