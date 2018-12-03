package com.psptio.test;

import java.io.UnsupportedEncodingException;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.apache.rocketmq.remoting.exception.RemotingException;

public class Producer {
	public static void main(String[] args) throws MQClientException, RemotingException, MQBrokerException, InterruptedException {
		//声明并初始化一个producer
		//需要一个producer group 名字作为构造方法的参数看，这里为producer1
		DefaultMQProducer producer = new DefaultMQProducer("producer1");
		//设置NameServer地址，此处应改为实际NameServer地址，多个地址之间用；分隔
		//NameServer的地址必须有，但是也可以通过环境变量的方式设置，不一定非得写死在这里
		producer.setNamesrvAddr("127.0.0.1:9876");
		//调用start()方法启动一个producer实例
		producer.start();
		
		//发送10条消息：Topic为TopicTest,tag为TagA,消息内容为“Hello RoketMQ”拼接上i
		for(int i=0;i<10;i++)
		{
			try {
				Message msg = new Message("TopicTest",//topic
							"TagA",
							("Hello RocketMQ"+i).getBytes(RemotingHelper.DEFAULT_CHARSET)
						);
				//调用producer的send()方法发送消息
				//这里调用的是同步方式，所以会有返回结果
				SendResult sendResult=producer.send(msg);
				//打印返回结果，可以看到消息发送的状态以及一些相关信息
				System.out.println("发送消息；"+sendResult);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
				Thread.sleep(1000);
			}
		}
		//发送完消息之后，调用shutdown()方法关闭producer
		producer.shutdown();
	}
}
