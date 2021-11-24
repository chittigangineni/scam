package car.demo.service;

import car.demo.pojo.SendRequest;
import car.demo.pojo.SendResponse;

/**
 * 发送消息的服务
 * @author yancheng11334
 *
 */
public interface SendMessageService {

	public SendResponse sendMessage(SendRequest request);
}
