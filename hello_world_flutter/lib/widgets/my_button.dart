import 'package:flutter/material.dart';

class MyButton extends StatelessWidget {

  @override
  Widget build(BuildContext context) {
    return new GestureDetector(
      onTap: () {print("用户单击了");},
      onDoubleTap: () {print("用户双击了");},
      onLongPress: () {print("用户长按了");},
      child: new Container(
        height: 36.0,
        padding: const EdgeInsets.all(8.0),
        margin: const EdgeInsets.symmetric(horizontal: 8.0),
        decoration: new BoxDecoration(
          borderRadius: new BorderRadius.circular(5.0),
          color: Colors.lightGreen[100]
        ),
        child: new Center(child: new Text("点击监听"),),
      ),
    );
  }
}