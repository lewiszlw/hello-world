package lewiszlw.serializable;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Collections;

/**
 * Desc:
 * ------------------------------------
 * Author:zhanglinwei
 * Date:2018/9/26
 * Time:19:37
 */
public class JacksonTest {

    public static void main(String[] args) throws IOException {
        FromObj fromObj = new FromObj();
        fromObj.setName("fromObj");
        fromObj.setList(Collections.singletonList(2));
        fromObj.age = 10;
        fromObj.password = "123";

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(fromObj);
        System.out.println(json);

        ToObj toObj = mapper.readValue(json, ToObj.class);
        System.out.println(toObj.getName() + " " + toObj.getList() + " " + toObj.age + " " + toObj.password);
    }
}
