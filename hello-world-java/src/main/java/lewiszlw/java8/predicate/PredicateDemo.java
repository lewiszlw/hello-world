package lewiszlw.java8.predicate;

import lewiszlw.java8.listsort.Obj;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

/**
 * Desc:
 *
 * @author zhanglinwei02
 * @date 2019-05-21
 */
public class PredicateDemo {

    public static void main(String[] args) {
        List<Obj> objList = new ArrayList<>();
        objList.add(Obj.builder().code(1).desc("a").build());
        objList.add(Obj.builder().code(2).desc("b").build());
        objList.add(Obj.builder().code(3).desc("c").build());

        Obj b = find(objList, obj -> Objects.equals(obj.getDesc(), "b"));
        System.out.println(b.toString());
    }

    public static <T> T find(List<T> list, Predicate<T> predicate) {
        if (CollectionUtils.isEmpty(list)) {
            return null;
        }
        for (T t: list) {
            if (predicate.test(t)) {
                return t;
            }
        }
        return null;
    }
}
