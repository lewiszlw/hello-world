package lewiszlw.java8.groupby;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lewiszlw.java8.list2map.CommonObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Desc:
 *
 * @author zhanglinwei02
 * @date 2019-05-09
 */
public class GroupByDemo {

    public static void main(String[] args) throws JsonProcessingException {
        List<CommonObject> list = new ArrayList<>();
        list.add(new CommonObject(1, "a", new Date().toString()));
        list.add(new CommonObject(2, "a", new Date().toString()));
        list.add(new CommonObject(2, "c", new Date().toString()));
        list.add(new CommonObject(3, "b", new Date().toString()));

        Map<Integer, List<CommonObject>> collect1 = list.stream().collect(Collectors.groupingBy(CommonObject::getId));
        System.out.println(new ObjectMapper().writeValueAsString(collect1));
        Map<String, List<CommonObject>> collect2 = list.stream().collect(Collectors.groupingBy(CommonObject::getName));
        System.out.println(new ObjectMapper().writeValueAsString(collect2));
    }
}
