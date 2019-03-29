package lewiszlw.serializable;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectOutputStream.PutField;
import java.io.Serializable;
import java.util.List;

/**
 * Desc:
 * ------------------------------------
 * Author:zhanglinwei
 * Date:2018/9/26
 * Time:19:08
 */
public class FromObj implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;

    private List<Integer> list;

    transient public int age;

    public static int staticVar = 5;

    public String password;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Integer> getList() {
        return list;
    }

    public void setList(List<Integer> list) {
        this.list = list;
    }

    private void writeObject(ObjectOutputStream oos) {
        try {
            PutField putField = oos.putFields();
            password = "encryption";
            putField.put("password", password);
            oos.writeFields();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void readObject(ObjectInputStream ois) {
        try {
            ObjectInputStream.GetField getField = ois.readFields();
            String enPassword =(String) getField.get("password", "");
            if(enPassword.equals("encryption")) {
                password = "pass";
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
