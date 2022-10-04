import "package:flutter/cupertino.dart";

class Homepage extends StatefulWidget {
  @override
  State<StatefulWidget> createState() => _HomepageState();
}

class _HomepageState extends State<Homepage> {
  @override
  Widget build(BuildContext context) {
    return CupertinoPageScaffold(
      navigationBar: CupertinoNavigationBar(middle: Text("time picker"),),
      child: CupertinoTimerPicker(mode: CupertinoTimerPickerMode.hm, onTimerDurationChanged: (value) => {
        print("time: $value")
      },));
  }
}

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return CupertinoApp(home: Homepage(),);
  }
}

void main() => runApp(MyApp());