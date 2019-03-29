package lewiszlw.builder;

/**
 * Desc:
 *
 * @author zhanglinwei02
 * @date 2018/11/6
 */
public class Client {

    public static void main(String[] args) {
        UserDO userDO = UserDO.builder().id(1).name("lewis").address("北京").build();
        System.out.println(userDO.getId());
        System.out.println(userDO.getName());
        System.out.println(userDO.getAddress());
    }
}
