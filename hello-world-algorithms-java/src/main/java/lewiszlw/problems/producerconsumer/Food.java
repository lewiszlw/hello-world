package lewiszlw.problems.producerconsumer;

/**
 * Desc:
 *
 * @author zhanglinwei02
 * @date 2019-05-14
 */
public class Food {

    // 计数器，用于菜品序号计数
    public static volatile int counter = 0;

    // 菜品序号
    private int no;

    public Food() {
        no = ++counter;
    }

    @Override
    public String toString() {
        return "第" + no + "道菜";
    }
}
