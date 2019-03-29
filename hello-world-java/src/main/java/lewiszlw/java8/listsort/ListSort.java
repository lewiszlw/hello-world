package lewiszlw.java8.listsort;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Desc:
 *
 * @author zhanglinwei02
 * @date 2018/12/14
 */
public class ListSort {
    public static void main(String[] args) throws InterruptedException {
        List<Obj> list = new ArrayList<>();
        Obj obj1 = Obj.builder().date(new Date().toString()).desc("1").build();
        Thread.sleep(3000);
        Obj obj2 = Obj.builder().date(new Date().toString()).desc("2").build();
        Thread.sleep(3000);
        Obj obj3 = Obj.builder().date(new Date().toString()).desc("3").build();
        list.add(obj2);
        list.add(obj3);
        list.add(obj1);

        list = list.stream().sorted(Comparator.comparing(Obj::getDate)).collect(Collectors.toList());
        for (Obj obj : list) {
            System.out.println(obj.getDate());
        }
    }
}
