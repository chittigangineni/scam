package car.demo.service;

import car.demo.pojo.ReceiveRequest;
import car.demo.pojo.ReceiveResponse;

/**
 * 接收消息服务
 * @author yancheng11334
 *
 */
public interface ReceiveMessageService {

	public ReceiveResponse receive(ReceiveRequest request);
}
