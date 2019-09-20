package lewiszlw.json.jsontomap;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.MapType;
import lombok.NonNull;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class JacksonUtils {

    public static void main(String[] args) throws JsonProcessingException {
        Map<String, Product> productMap = new HashMap<>();
        productMap.put("A", new Product().setTitle("A").setPrice(1));
        productMap.put("B", new Product().setTitle("B").setPrice(2));

        String json = new ObjectMapper().writeValueAsString(productMap);
        System.out.println("json:  " + json);

        Map<String, Product> newProductMap = fromJson(json, String.class, Product.class);
        System.out.println(newProductMap.size());
        System.out.println(newProductMap);
    }

    public static <K, V> Map<K, V> fromJson(@NonNull final String json,
                                            @NonNull final Class<K> keyType,
                                            @NonNull final Class<V> valueType) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            MapType mapType = objectMapper.getTypeFactory()
                    .constructMapType(HashMap.class, keyType, valueType);
            return objectMapper.readValue(json, mapType);
        } catch (IOException e) {
            throw new IllegalStateException("Failed to convert json string to Map");
        }
    }

}

