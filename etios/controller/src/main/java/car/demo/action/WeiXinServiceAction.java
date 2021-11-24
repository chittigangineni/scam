package car.demo.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.tinygroup.weixin.WeiXinConnector;

@Controller
@RequestMapping("/demo")
public class WeiXinServiceAction {

	@Autowired
	private WeiXinConnector weiXinConnector;
	
	@RequestMapping(value = "/service")
	public void service(HttpServletRequest request,HttpServletResponse response){
		weiXinConnector.receive(request, response);
	}
}
