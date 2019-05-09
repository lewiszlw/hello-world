package lewiszlw.rocketmq.demo.mq;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;

/**
 * Desc:
 *
 * @author zhanglinwei02
 * @date 2019-05-09
 */
@Service
public class DemoTopicProducer implements InitializingBean, DisposableBean {

    DefaultMQProducer defaultMQProducer;

    private String topic = "demo-topic";
    private String producerGroup = "demo-topic-producer-group";

    @Override
    public void afterPropertiesSet() throws Exception {
        defaultMQProducer = new DefaultMQProducer(producerGroup);
        defaultMQProducer.setNamesrvAddr("localhost:9876");
        defaultMQProducer.start();
    }

    @Override
    public void destroy() throws Exception {
        defaultMQProducer.shutdown();
    }

    public SendResult send(String message) throws UnsupportedEncodingException, InterruptedException, RemotingException, MQClientException, MQBrokerException {
        Message msg = new Message(topic, message.getBytes(RemotingHelper.DEFAULT_CHARSET));
        return defaultMQProducer.send(msg);
    }

}
