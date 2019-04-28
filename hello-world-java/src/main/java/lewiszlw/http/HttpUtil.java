package lewiszlw.http;

import com.google.common.io.CharStreams;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;

/**
 * Desc:
 *
 * @author zhanglinwei02
 * @date 2019-04-25
 */
public class HttpUtil {

    public void get(String url) throws IOException {
        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
        connection.setRequestMethod("GET");
        connection.setConnectTimeout(1000);
        connection.setReadTimeout(1000);

        connection.connect();

        int responseCode = connection.getResponseCode();
        System.out.println("response code: " + responseCode);

        InputStreamReader isr = new InputStreamReader(connection.getInputStream(), Charset.forName("UTF-8"));
        String s = CharStreams.toString(isr);
        System.out.println("data: " + s);
    }


    @Test
    public void testGet() throws IOException {
        get("http://127.0.0.1:8080/home");
    }
}
