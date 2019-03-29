package lewiszlw.serializable;

import java.io.*;
import java.util.Collections;

/**
 * Desc:
 * ------------------------------------
 * Author:zhanglinwei
 * Date:2018/9/26
 * Time:19:14
 */
public class SerializableTest {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        FromObj fromObj = new FromObj();
        fromObj.setName("fromObj");
        fromObj.setList(Collections.singletonList(2));
        fromObj.age = 22;
        fromObj.password = "123";

        // 序列化
        FileOutputStream fos = new FileOutputStream("/Users/lewis/tmp/fromObj.ser");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(fromObj);
        oos.flush();
        System.out.println(new File("/Users/lewis/tmp/fromObj.ser").length());
        // 测试序列化存储规则
        oos.writeObject(fromObj);
        oos.flush();
        System.out.println(new File("/Users/lewis/tmp/fromObj.ser").length());
        oos.close();
        fos.close();

        // 序列化后修改静态变量值
        FromObj.staticVar = 10;

        // 反序列化
        FileInputStream fis = new FileInputStream("/Users/lewis/tmp/fromObj.ser");
        ObjectInputStream ois = new ObjectInputStream(fis);
        FromObj fromObj1 = (FromObj) ois.readObject();
        System.out.println(fromObj1.getName() + " " + fromObj1.getList() + " " + fromObj1.staticVar + " " + fromObj1.age + " " + fromObj1.password);
    }
}
