package lewiszlw.java8.listfilter;

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
public class ListFilter {
    public static void main(String[] args) {
        List<CommonObject> list = new ArrayList<>();
        list.add(new CommonObject(1, "lewis", "true"));
        list.add(new CommonObject(2, "tom", "false"));

        List<CommonObject> collect = list.stream().filter(commonObject -> {
            return commonObject.getData() == "true";
        }).collect(Collectors.toList());

        collect.stream().forEach(System.out::println);
    }
}
