package lewiszlw.serializable;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectInputStream.GetField;
import java.io.Serializable;
import java.util.List;

/**
 * Desc:
 * ------------------------------------
 * Author:zhanglinwei
 * Date:2018/9/26
 * Time:19:13
 */
public class ToObj implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;

    private List<Integer> list;

    public Integer age;

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

    private void readObject(ObjectInputStream ois) {
        try {
            GetField getField = ois.readFields();
            String enPassword =(String) getField.get("password", "");
            if(enPassword == "encryption") {
                password = "pass";
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
