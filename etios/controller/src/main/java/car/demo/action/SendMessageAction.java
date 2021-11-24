package car.demo.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import car.demo.pojo.SendRequest;
import car.demo.pojo.SendResponse;
import car.demo.service.SendMessageService;
import org.tinygroup.logger.LogLevel;
import org.tinygroup.logger.Logger;
import org.tinygroup.logger.LoggerFactory;

/**
 * 模拟往微信服务器发送消息
 * 
 * @author yancheng11334
 * 
 */
@Controller
@RequestMapping("demo/test")
public class SendMessageAction {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(SendMessageAction.class);

	@Autowired
	private SendMessageService sendMessageService;

	@RequestMapping(value = "/sendMessage", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String sendMessage(String url, String content, String certBean,
			Model model) {

		LOGGER.logMessage(LogLevel.INFO, "url=" + url);
		LOGGER.logMessage(LogLevel.INFO, "content=" + content);
		LOGGER.logMessage(LogLevel.INFO, "certBean=" + certBean);
		
		SendRequest request = new SendRequest();
		request.setUrl(url);
		request.setCertBean(certBean);
		request.setContent(content);
		
		SendResponse response = sendMessageService.sendMessage(request);
		if(response!=null){
		   model.addAttribute("result", response.getResult());
		}
		return "demo/test/message.page";
	}
}
