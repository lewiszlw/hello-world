package lewiszlw.spi.experiment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Desc:
 *
 * @author zhanglinwei02
 * @date 2019-06-21
 */
public class SPIDestroyParentalDelegationModel {

    public static void main(String[] args) throws SQLException {
        // 基于SPI机制
        Connection connection = DriverManager
                .getConnection("jdbc:mysql://127.0.0.1:3306/red-envelope?useSSL=false&useUnicode=true&characterEncoding=utf8", "root", "123");
        System.out.println(connection.getClass().getName());
        System.out.println(connection.getClass().getClassLoader());
        System.out.println(Connection.class.getClassLoader());

        // 打印
        //com.mysql.jdbc.JDBC4Connection
        //sun.misc.Launcher$AppClassLoader@18b4aac2
        //null

        // 说明
        // com.mysql.jdbc.JDBC4Connection由AppClassLoader加载，
        // 而java.sql.Connection由BootstrapClassLoader加载，并没有破坏双亲委派模型
    }
}
