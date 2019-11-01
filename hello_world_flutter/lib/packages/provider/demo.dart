import 'package:flutter/material.dart';
import 'package:provider/provider.dart';
import 'counter_model.dart';

// reference: https://juejin.im/post/5d00a84fe51d455a2f22023f
void main() {
  final counter = CounterModel();
  final textSize = 48;

  runApp(Provider<int>.value(
    value: textSize,
    child: ChangeNotifierProvider.value(value: counter, child: MyApp()),
  ));
}

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      theme: ThemeData.dark(),
      home: FirstScreen(),
    );
  }
}

class FirstScreen extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    final _counter = Provider.of<CounterModel>(context);
    final textSize = Provider.of<int>(context).toDouble();

    return Scaffold(
      appBar: AppBar(
        title: Text("FirstPage"),
      ),
      body: Center(
        child: Text(
          'Value: ${_counter.value}',
          style: TextStyle(fontSize: textSize),
        ),
      ),
      floatingActionButton: FloatingActionButton(
        onPressed: () => Navigator.of(context)
            .push(MaterialPageRoute(builder: (context) => SecondPage())),
        child: Icon(Icons.navigate_next),
      ),
    );
  }
}

class SecondPage extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text("SecondPage"),
      ),
      body: Consumer2<CounterModel, int>(
        builder: (context, CounterModel counter, int textSize, _) => Center(
          child: Text(
            'value: ${counter.value}',
            style: TextStyle(fontSize: textSize.toDouble()),
          ),
        ),
      ),
      floatingActionButton: Consumer<CounterModel>(
        builder: (context, CounterModel counter, child) => FloatingActionButton(
          onPressed: counter.increment,
          child: child,
        ),
        child: Icon(Icons.add),
      ),
    );
  }
}
