package lewiszlw.java8.list2map;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Desc:
 * ------------------------------------
 * Author:zhanglinwei
 * Date:2018/9/27
 * Time:11:42
 */
public class List2Map {

    public static void main(String[] args) {
        List<CommonObject> list = new ArrayList<>();
        list.add(new CommonObject(1, "lew", ""));
        list.add(new CommonObject(2, "tom", ""));
        // java.lang.IllegalStateException: Duplicate key tom
        // list.add(new CommonObject(2, "john", ""));

        Map<Integer, String> map = list.stream().collect(Collectors.toMap(CommonObject::getId, CommonObject::getName));
        for(Map.Entry<Integer, String> each: map.entrySet()) {
            System.out.println("key=" + each.getKey() + " val=" + each.getValue());
        }
    }
}
