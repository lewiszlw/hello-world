package lewiszlw.guava.file;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Charsets;
import com.google.common.io.Files;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Desc:
 *
 * @author zhanglinwei02
 * @date 2019-04-28
 */
public class FilesDemo {

    public static void main(String[] args) throws IOException {
        Map<String, String> cacheMap = new HashMap<>();
        cacheMap.put("key1", "value1");
        cacheMap.put("key2", "value2");

        ObjectMapper objectMapper = new ObjectMapper();

        File file = new File("/Users/lewis/test/test");
        Files.write(objectMapper.writeValueAsBytes(cacheMap), file);

        List<String> strings = Files.readLines(file, Charsets.UTF_8);
        System.out.println(strings.get(0));

        Map<String, String> map = objectMapper.readValue(file, Map.class);
        System.out.println(map.get("key1"));
        System.out.println(map.get("key2"));
    }
}
