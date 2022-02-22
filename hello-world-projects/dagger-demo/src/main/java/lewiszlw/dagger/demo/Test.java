package lewiszlw.dagger.demo;

/**
 * Desc:
 *
 * @author zhanglinwei02
 * @date 2019-08-14
 */
public class Test {

    public static void main(String[] args) {
        App app = new App();
        System.out.println(app.serviceA.getName());
        System.out.println(app.getServiceBName());
        System.out.println(app.serviceA.getServiceBName());
    }
}
