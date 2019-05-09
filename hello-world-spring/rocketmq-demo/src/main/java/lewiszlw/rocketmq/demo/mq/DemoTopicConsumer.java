package lewiszlw.rocketmq.demo.mq;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Desc:
 *
 * @author zhanglinwei02
 * @date 2019-05-09
 */
@Service
public class DemoTopicConsumer implements InitializingBean {

    DefaultMQPushConsumer consumer;

    private String topic = "demo-topic";
    private String consumerGroup = "demo-topic-consumer-group";

    @Override
    public void afterPropertiesSet() throws Exception {
        consumer = new DefaultMQPushConsumer(consumerGroup);
        consumer.setNamesrvAddr("localhost:9876");
        consumer.subscribe(topic, "*");
        consumer.registerMessageListener(new DemoTopicMessageListener());
        consumer.start();
    }

    class DemoTopicMessageListener implements MessageListenerConcurrently {
        @Override
        public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext consumeConcurrentlyContext) {
            System.out.println("==========本次接收消息=========");
            for (MessageExt messageExt: list) {
                System.out.println("receive msg: " + new String(messageExt.getBody()) + ", msgId: " + messageExt.getMsgId());
            }
            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
        }
    }

}
