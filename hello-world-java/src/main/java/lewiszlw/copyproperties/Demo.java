package lewiszlw.beanutils.copyproperties;

import org.springframework.beans.BeanUtils;

/**
 * Desc:
 * ------------------------------------
 * Author:zhanglinwei
 * Date:2018/9/29
 * Time:16:54
 */
public class Demo {

    public static void main(String[] args) {
        Person person = new Person();
        person.setName("lewis");
        person.setAge(18);
        person.setEmail("123@qq.com");
        person.setIsOld(false);

        Student student = new Student();

        BeanUtils.copyProperties(person,student);

        System.out.println(student.getName());
        System.out.println(student.getAge());
        System.out.println(student.getAddress());
        System.out.println(student.getIsOld());
    }
}
