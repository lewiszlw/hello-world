package lewiszlw.json.jsontomap;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Product {
    private String title;
    private Integer price;
}
