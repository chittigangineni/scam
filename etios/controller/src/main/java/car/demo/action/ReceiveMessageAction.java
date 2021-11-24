package car.demo.action;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.tinygroup.commons.tools.StringUtil;
import car.demo.pojo.ReceiveRequest;
import car.demo.pojo.ReceiveResponse;
import car.demo.service.ReceiveMessageService;

@Controller
@RequestMapping("demo/test")
public class ReceiveMessageAction {

	@Autowired
	private ReceiveMessageService receiveMessageService;
	
	@RequestMapping(value = "/receiveMessage", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String receiveMessage(String content,String timestamp,String nonce,HttpServletRequest request,Model model){
		
		String url = getUrl(timestamp,nonce,request);
		ReceiveRequest nRequest = new ReceiveRequest();
		nRequest.setContent(content);
		nRequest.setUrl(url);
		
		ReceiveResponse nResponse =receiveMessageService.receive(nRequest);
		if(nResponse!=null){
		   model.addAttribute("result", nResponse.getResult());
		}
		
		return "demo/test/receive.page";
	}
	

	/**
	 * 模拟跳转的请求地址
	 * @param request
	 * @return
	 */
	private String getUrl(String timestamp,String nonce,HttpServletRequest request){
		StringBuffer sb = new StringBuffer();
		
		sb.append("http://").append(request.getRemoteAddr());
		sb.append(":").append(request.getServerPort());
		sb.append(request.getRequestURI().replaceFirst("demo/test/receiveMessage", "demo/service"));
		
		//模拟密文消息
		if(!StringUtil.isEmpty(timestamp) && !StringUtil.isEmpty(nonce)){
		   sb.append("?encrypt_type=aes").append("&timestamp=").append(timestamp).append("&nonce=").append(nonce);
		}
		
		return sb.toString();
	}
}
