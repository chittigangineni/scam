package car.demo.service.impl;

import car.demo.pojo.ReceiveRequest;
import car.demo.pojo.ReceiveResponse;
import car.demo.service.ReceiveMessageService;
import org.tinygroup.weixinhttp.WeiXinHttpConnector;

public class ReceiveMessageServiceImpl implements ReceiveMessageService{

	private WeiXinHttpConnector weiXinHttpConnector;

	public WeiXinHttpConnector getWeiXinHttpConnector() {
		return weiXinHttpConnector;
	}

	public void setWeiXinHttpConnector(WeiXinHttpConnector weiXinHttpConnector) {
		this.weiXinHttpConnector = weiXinHttpConnector;
	}

	public ReceiveResponse receive(ReceiveRequest request) {
		String result = weiXinHttpConnector.postUrl(request.getUrl(), request.getContent(),null);
		ReceiveResponse response = new ReceiveResponse();
		response.setResult(result);
		return response;
	}

}
