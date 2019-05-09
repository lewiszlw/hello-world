package lewiszlw.rocketmq.demo.controller;

import lewiszlw.rocketmq.demo.mq.DemoTopicProducer;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;

/**
 * Desc:
 *
 * @author zhanglinwei02
 * @date 2019-05-09
 */
@RestController
public class MessageController {

    @Autowired
    private DemoTopicProducer demoTopicProducer;

    @RequestMapping("send")
    public SendResult send(@RequestParam(value = "body") String body) throws InterruptedException, RemotingException, MQClientException, MQBrokerException, UnsupportedEncodingException {
        return demoTopicProducer.send(body);
    }
}
