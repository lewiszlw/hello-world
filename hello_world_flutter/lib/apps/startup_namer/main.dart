import 'package:flutter/material.dart';
import "package:english_words/english_words.dart";
import 'basic_widgets.dart';

//void main() => runApp(new MyApp());
void main() => runApp(new MaterialApp(title: "My app", home: new MyScaffold()));

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return new MaterialApp(
      title: 'Startup Name Generator',
      home: new RandomWords(),
//      theme: new ThemeData(primaryColor: Colors.white),
      theme: new ThemeData.dark(),
    );
  }
}

class RandomWords extends StatefulWidget {
  @override
  State createState() => new RandomWordsState();
}

class RandomWordsState extends State<RandomWords> {
  final _suggestions = <WordPair>[];
  final _saved = new Set<WordPair>();
  final TextStyle _biggerFont = new TextStyle(fontSize: 18.0);

  Widget _buildSuggestions() {
    return new ListView.builder(
      itemBuilder: (context, i) {
        // 在每行中间加一像素分隔线
        if (i.isOdd) return new Divider();
        // For example: 1, 2, 3, 4, 5 becomes 0, 1, 1, 2, 2.
        final index = i ~/ 2;
        if (index >= _suggestions.length) {
          _suggestions.addAll(generateWordPairs().take(10));
        }
        return _buildRow(_suggestions[index]);
        },
      padding: const EdgeInsets.all(16.0),
    );
  }
  
  Widget _buildRow(WordPair pair) {
    final _alreadySaved = _saved.contains(pair);
    return new ListTile(
      title: new Text(pair.asPascalCase, style: _biggerFont,),
      trailing: new Icon(
        _alreadySaved? Icons.favorite: Icons.favorite_border,
        color: _alreadySaved? Colors.red: null,
      ),
      onTap: () {
        // 在 Flutter 的响应式风格框架中，调用 setState() ，将为 State 对象触发
        // build() 方法的调用，从而实现对UI的更新。
        setState(() {
          if (_alreadySaved) {
            _saved.remove(pair);
          } else {
            _saved.add(pair);
          }
        });
      },
    );
  }

  void _pushSaved() {
    Navigator.of(context).push(
      new MaterialPageRoute(
        builder: (context) {
          final titles = _saved.map(
              (pair) {
                return new ListTile(
                  title: new Text(pair.asPascalCase, style: _biggerFont,),
                );
              }
          );
          final divided = ListTile.divideTiles(tiles: titles, context: context)
              .toList();
          return new Scaffold(
            appBar: new AppBar(title: new Text("Saved Suggestions"),),
            body: new ListView(children: divided,),
          );
        }
      )
    );
  }

  @override
  Widget build(BuildContext context) {
    return new Scaffold(
      appBar: new AppBar(
        title: new Text("Startup name generator"),
        actions: <Widget>[new IconButton(icon: new Icon(Icons.list), onPressed: _pushSaved)],
      ),
      body: _buildSuggestions(),
    );
  }
}
