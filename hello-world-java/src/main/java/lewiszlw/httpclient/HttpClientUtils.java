package lewiszlw.httpclient;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.util.Map;

/**
 * Desc:
 *
 * @author zhanglinwei02
 * @date 2019-06-05
 */
public class HttpClientUtils {
    private static HttpClient httpClient = HttpClients.createDefault();

    public static <T> T doGet(String url, Class<T> returnType, Map<String, String> headers) {
        HttpGet httpGet = new HttpGet(url);
        // 超时 & headers 设置
        setTimeoutAndHeaders(httpGet, headers);
        // 执行请求
        return execute(httpGet, returnType);
    }

    public static <T> T doPost(String url, String content, Class<T> returnType, Map<String, String> headers) {
        HttpPost httpPost = new HttpPost(url);
        // 超时 & headers 设置
        setTimeoutAndHeaders(httpPost, headers);
        httpPost.setHeader("Content-Type", "application/json;charset=UTF-8");
        // 设置body
        httpPost.setEntity(new StringEntity(content, "utf-8"));
        // 执行请求
        return execute(httpPost, returnType);
    }

    public static <T> T execute(HttpUriRequest request, Class<T> returnType) {
        try {
            HttpResponse response = httpClient.execute(request);
            if (response.getStatusLine().getStatusCode() != 200) {
                throw new RuntimeException("http响应状态码非200");
            }
            HttpEntity entity = response.getEntity();
            String content = entity != null ? EntityUtils.toString(entity, "utf-8") : null;
            return JSONObject.parseObject(content, returnType);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static void setTimeoutAndHeaders(HttpRequestBase request, Map<String, String> headers) {
        // 超时设置
        request.setConfig(RequestConfig.custom()
                .setSocketTimeout(5 * 1000)
                .setConnectTimeout(2 * 1000)
                .build());
        // headers设置
        if (!CollectionUtils.isEmpty(headers)) {
            for (Map.Entry<String, String> header : headers.entrySet()){
                request.addHeader(header.getKey(), header.getValue());
            }
        }
    }
}
