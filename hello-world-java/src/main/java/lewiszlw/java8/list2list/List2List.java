package lewiszlw.java8.list2list;

import lewiszlw.java8.list2map.CommonObject;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Desc:
 *
 * @author zhanglinwei02
 * @date 2019/1/9
 */
public class List2List {
    public static void main(String[] args) {
        List<CommonObject> list = new ArrayList<>();
        list.add(new CommonObject(1, "lewis", ""));
        list.add(new CommonObject(2, "tom", ""));

        List<Integer> collect = list.stream().map(CommonObject::getId).collect(Collectors.toList());

        collect.stream().forEach(System.out::println);
    }
}
