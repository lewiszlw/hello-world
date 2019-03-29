package lewiszlw.validation;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Date;
import java.util.Set;

/**
 * Desc:
 *
 * @author zhanglinwei02
 * @date 2019/1/29
 */
public class ValidationTest {

    public static void main(String[] args) {
        ValidationBean bean = ValidationBean.builder()
                .id(null)
                .name("")
                .nickName("111111111111111111111111111111111111111111111111111111111")
                .age(101)
                .isStudent(false)
                .nullField(1)
                .birth(new Date(2996, 2, 18))
                .death(new Date(1100, 2, 18)).build();
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<ValidationBean>> violations = validator.validate(bean);
        System.out.println("size: " + violations.size());
        violations.stream().forEach(validationBeanConstraintViolation -> {
            System.out.println(validationBeanConstraintViolation.getMessage());
        });
    }
}
