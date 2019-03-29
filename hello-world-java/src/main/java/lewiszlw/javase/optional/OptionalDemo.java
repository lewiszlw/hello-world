package lewiszlw.javase.optional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * Desc: Optional 是 java 优雅处理 NPE 的方式
 *
 * @author zhanglinwei02
 * @date 2019-02-26
 */
public class OptionalDemo {

    final static User DEFAULT_USER = User.builder().name("default").build();

    public static void main(String[] args) {

        // of()入参不能为null
        Optional<User> user_not_null = Optional.of(User.builder().name("lewiszlw").build());

        // ofNullable()入参可能为null
        Optional<User> user = Optional.ofNullable(null);

        List orders = user.map(user1 -> user1.orders).orElse(Collections.EMPTY_LIST);
        System.out.println("orders: " + orders);

        String name = user.map(user1 -> user1.name).map(name1 -> name1.toUpperCase()).orElse("null");
        System.out.println("name: " + name);

        User user1 = user.orElse(DEFAULT_USER);
        System.out.println("user1: " + user1.toString());

        User user2 = user.orElseGet(() -> {
            return DEFAULT_USER;
        });
        System.out.println("user2: " + user2.toString());

        User user3 = user.orElseThrow(() -> {
            return new IllegalArgumentException("user is null");
        });
    }
}
