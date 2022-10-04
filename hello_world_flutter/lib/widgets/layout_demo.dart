import 'package:flutter/material.dart';

/// 垂直、水平布局
class LayoutDemo extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return new Scaffold(
      appBar: new AppBar(title: new Text("Layout demo"),),
      // Column: 垂直布局, Row: 水平布局
      body: new Column(children: <Widget>[
        new ElevatedButton(
          onPressed: () {print("点击红色按钮");},
          child: new Text("红色按钮"),
          // color: const Color(0xffcc0000),
        ),
        new Flexible(
          flex: 1,
          fit: FlexFit.tight,
          child: new ElevatedButton(
            onPressed: () {print("点击黄色按钮");},
            child: new Text("黄色按钮"),
            style: new ButtonStyle(backgroundColor: MaterialStateProperty.all<Color>(Colors.blue)),
            // color: const Color(0xfff1c232),
          ),
        ),
      ])
    );
  }
}


/// 层叠定位
class LayoutDemo2 extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return new Scaffold(
      appBar: new AppBar(title: new Text("层叠定位"),),
      body: new Stack(children: <Widget>[
        new Image.network("http://img2.cxtuku.com/00/13/12/s97783873391.jpg"),
        new Positioned(
          child: new Text("Whatever is worth doing is worth doing well",
            style: new TextStyle(fontSize: 20.0, fontFamily: "serif"),
          ),
          left: 35.0,
          right: 35.0,
          top: 45.0,
        )
      ],),
    );
  }
}

/// 滚动布局
class LayoutDemo3 extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return new Scaffold(
      appBar: new AppBar(title: new Text("滚动布局"),),
      body: new ListView(children: <Widget>[
        new Center(child: new Text("\nSonnet", style: new TextStyle(fontSize: 26.0, fontFamily: "serif"),),),
        new Center(child: new Text("William Shakespeare\n\n", style: new TextStyle(fontSize: 12.0, fontFamily: "serif"),),),
        new Center(child: new Text(
          '''
From fairest creatures we desire increase,
That thereby beauty's rose might never die,
But as the riper should by time decease,
His tender heir might bear his memory;
But thou, contracted to thine own bright eyes,
Feed'st thy light's flame with self-substantial fuel,
Making a famine where abundance lies,
Thyself thy foe, to thy sweet self too cruel.
Thout that are now the world's fresh ornament
And only herald to the gaudy spring,
Within thine own bud buriest thy content
And, tender churl, mak'st waste in niggarding.
Pity the world, or else this glutton be,
To eat the world's due, by the grave and thee.
WHEN forty winters shall besiege thy brow
And dig deep trenches in thy beauty's field,
Thy youth's proud livery, so gazed on now,
Will be a tottered weed of small worth held:
Then being asked where all thy beauty lies,
Where all the treasure of thy lusty days,
To say within thine own deep-sunken eyes
Were an all-eating shame and thriftless praise.
How much more prasie deserved thy beauty's use
If thou couldst answer, 'This fair child of mine
Shall sum my count and make my old excuse,'
Proving his beauty by succession thine.
This were to be new made when thou art old
And see thy blood warm when thou feel'st cold.
LOOK in thy glass, and tell the face thou viewest
Now is the time that face should form another,
Whose fresh repair if now thou renewest,
Thou dost beguile the world, unbless some mother.
For where is she so fair whose uneared womb
Disdains the tillage of thy husbandry?
Or who is he so fond will be the tomb
Of his self-love, to stop posterity?
Thou art thy mother's glass, and she in thee
Calls back the lovely April of her prime;
So thou through windows of thine age shalt see,
Despite of wrinkles, this thy golden time.
But if thou live rememb'red not to be,
Die single, and thine image dies with thee.
          ''',
          style: new TextStyle(fontSize: 14.0, fontFamily: "serif"),
        ),)
      ],),
    );
  }
}

/// 中心定位：Center


/// 对齐：Align
// bottomCenter    (0.5, 1.0)    底部中心
//bottomLeft    (0.0, 1.0)    左下角
//bottomRight    (1.0, 1.0)    右下角
//center    (0.5, 0.5)    水平垂直居中
//centerLeft    (0.0, 0.5)    左边缘中心
//centerRight    (1.0, 0.5)    右边缘中心
//topCenter    (0.5, 0.0)    顶部中心
//topLeft    (0.0, 0.0)    左上角
//topRight    (1.0, 0.0)    右上角
class LayoutDemo4 extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return new Scaffold(
      appBar: new AppBar(title: new Text("对齐Align"),),
      body: new Stack(children: <Widget>[
        new Align(
          alignment: new FractionalOffset(0.0, 0.0),
          child: new Image.network("http://up.qqjia.com/z/25/tu32710_10.jpg"),
        ),
        new Align(
          alignment: new FractionalOffset(1.0, 1.0),
          child: new Image.network("http://up.qqjia.com/z/25/tu32710_10.jpg"),
        ),
      ],),
    );
  }
}


/// SizedBox控件能强制子控件具有特定宽度、高度
class LayoutDemo5 extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return new Scaffold(
      appBar: new AppBar(title: new Text("SizedBox Demo"),),
      body: new SizedBox(
        width: 250.0,
        height: 250.0,
        child: new Container(
          decoration: new BoxDecoration(color: Colors.lightBlueAccent[100]),
        ),
      ),
    );
  }
}

/// AspectRatio控件能强制子小部件的宽度和高度具有给定的宽高比
class LayoutDemo6 extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return new Scaffold(
      appBar: new AppBar(title: new Text("AspectRatio Demo"),),
      body: new AspectRatio(
        aspectRatio: 3.0/2.0,
        child: new Container(decoration: new BoxDecoration(color: Colors.lightBlueAccent[100]),),
      ),
    );
  }
}


/// DecoratedBox控件会在子控件绘制之前或之后绘制一个装饰
class LayoutDemo7 extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return new Scaffold(
      appBar: new AppBar(title: new Text("DecoratedBox Demo"),),
      body: new DecoratedBox(
        decoration: new BoxDecoration(
          gradient: new LinearGradient(
            begin: const FractionalOffset(0.0, 0.0),
            end: const FractionalOffset(1.0, 1.0),
            colors: <Color>[const Color(0xffff2cc), const Color(0xffff6eb4)],
          ),
        ),
        child: new Container(width: 250.0, height: 250.0,),
      ),
    );
  }
}


/// Opacity控件能调整子控件的不透明度
class LayoutDemo8 extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return new Scaffold(
      appBar: new AppBar(title: new Text("Opacity Demo"),),
      body: new Center(child: new Opacity(
        opacity: 0.1,
        child: new Container(
          width: 250.0,
          height: 100.0,
          decoration: new BoxDecoration(color: const Color(0xff000000)),
        ),
      ),
      ),
    );
  }
}

void main() {
  runApp(new MaterialApp(
    title: "layout demo",
//    home: new LayoutDemo(),
//    home: new LayoutDemo2(),
//    home: new LayoutDemo3(),
//    home: new LayoutDemo4(),
//    home: new LayoutDemo5(),
//    home: new LayoutDemo6(),
//    home: new LayoutDemo7(),
    home: new LayoutDemo8(),
  ));
}