package lewiszlw.httpclient;

import com.alibaba.fastjson.JSONObject;

/**
 * Desc:
 *
 * @author zhanglinwei02
 * @date 2019-06-05
 */
public class HttpClientDemo {

    public static void main(String[] args) {
        JSONObject content = new JSONObject();
        content.put("clientId", "39932b59-c9f1-461d-af98-b287bd8d4798");
        content.put("clientSecret", "3c1d49c0-11ea-4579-9e38-d462d42af4d5");
        content.put("code", "30bf19ad-1a9f-466f-840a-0461c66b01a8");
        JSONObject jsonObject = HttpClientUtils
                .doPost("http://127.0.0.1:8080/oauth/access_token", content.toJSONString(), JSONObject.class, null);
        System.out.println(jsonObject.toJSONString());
    }
}
