package lewiszlw.validation;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.*;
import java.util.Date;

/**
 * Desc:
 *
 * @author zhanglinwei02
 * @date 2019/1/29
 */
@Data
@Builder
public class ValidationBean {

    @NotNull(message = "id不能为null")
    private Integer id;

    @org.hibernate.validator.constraints.NotBlank(message = "name不能为null或空串")
    private String name;

    @Size(min = 1, max = 25, message = "nickName长度要在1~25之间")
    private String nickName;

    @Max(value = 100, message = "age不能超过100岁")
    @Min(value = 1, message = "age不能小于1岁")
    private Integer age;

    @AssertTrue(message = "isStudent只能为true")
    private Boolean isStudent;

    @Null(message = "nullField必须为null")
    private Object nullField;

    // @Past(message = "birth只能在当前时间之前")
    private Date birth;

    // @Future(message = "death只能在当前时间之后")
    private Date death;
}
