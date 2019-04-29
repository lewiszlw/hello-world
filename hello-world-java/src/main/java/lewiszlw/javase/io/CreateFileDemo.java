package lewiszlw.javase.io;

import com.google.common.io.Files;

import java.io.File;
import java.io.IOException;

/**
 * Desc:
 *
 * @author zhanglinwei02
 * @date 2019-04-28
 */
public class CreateFileDemo {

    public static void main(String[] args) throws IOException {
        File file = new File("/opt/dcc/test");
        File dir = file.getParentFile();
        if (!dir.exists()) {
            dir.mkdirs();
        }
        if (!file.exists()) {
            file.createNewFile();
        }
        Files.write("content".getBytes(), file);
    }
}
