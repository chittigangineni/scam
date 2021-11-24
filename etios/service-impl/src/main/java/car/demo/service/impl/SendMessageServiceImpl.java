package car.demo.service.impl;

import org.tinygroup.beancontainer.BeanContainerFactory;
import org.tinygroup.commons.tools.StringUtil;
import car.demo.pojo.SendRequest;
import car.demo.pojo.SendResponse;
import car.demo.service.SendMessageService;
import org.tinygroup.weixin.WeiXinConnector;
import org.tinygroup.weixin.result.AccessToken;
import org.tinygroup.weixinhttp.cert.WeiXinCert;

public class SendMessageServiceImpl implements SendMessageService {

	private WeiXinConnector weiXinConnector;

	public WeiXinConnector getWeiXinConnector() {
		return weiXinConnector;
	}

	public void setWeiXinConnector(WeiXinConnector weiXinConnector) {
		this.weiXinConnector = weiXinConnector;
	}

	public SendResponse sendMessage(SendRequest request) {
		AccessToken accessToken = weiXinConnector.getAccessToken();
		String url = appendToken(accessToken, request.getUrl());
		return connect(url,request.getContent(),request.getCertBean());
	}

	private String appendToken(AccessToken accessToken, String url) {
		String newUrl = null;
		if (url.endsWith("?") || url.endsWith("&")) {
			newUrl = url + "access_token=" + accessToken.getAccessToken();
		} else if (url.indexOf("?") < 0) {
			newUrl = url + "?access_token=" + accessToken.getAccessToken();
		} else {
			newUrl = url + "&access_token=" + accessToken.getAccessToken();
		}
		return newUrl;
	}

	private SendResponse connect(String url, String content, String certBean) {
		String result = "";

		if (StringUtil.isEmpty(content)) {
			// 模拟get
			result = weiXinConnector.getWeiXinSender().getWeiXinHttpConnector()
					.getUrl(url);
		} else {
			if (StringUtil.isEmpty(certBean)) {
				// 模拟非认证post
				result = weiXinConnector.getWeiXinSender()
						.getWeiXinHttpConnector().postUrl(url, content, null);
			} else {
				// 模拟认证post
				WeiXinCert cert = BeanContainerFactory.getBeanContainer(
						getClass().getClassLoader()).getBean("weixinPayCert");
				result = weiXinConnector.getWeiXinSender()
						.getWeiXinHttpConnector().postUrl(url, content, cert);
			}
		}
		SendResponse response = new SendResponse();
		response.setResult(result);
		return response;
	}

}
