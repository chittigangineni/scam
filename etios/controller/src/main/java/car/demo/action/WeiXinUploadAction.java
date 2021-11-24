package car.demo.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import car.demo.pojo.UploadRequest;
import car.demo.pojo.UploadResponse;
import car.demo.service.WeiXinUploadService;
import org.tinygroup.logger.LogLevel;
import org.tinygroup.logger.Logger;
import org.tinygroup.logger.LoggerFactory;
import org.tinygroup.springmvc.multipart.TinyMultipartFile;

/**
 * 模拟微信素材上传
 * @author yancheng11334
 *
 */
@Controller
@RequestMapping("demo/test")
public class WeiXinUploadAction {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(WeiXinUploadAction.class);
	
	@Autowired
	private WeiXinUploadService weiXinUploadService;
	
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public String upload(String type, @RequestParam(value = "meterial", required = false) TinyMultipartFile meterial,Model model) {
		LOGGER.logMessage(LogLevel.INFO, "type=" + type);
		LOGGER.logMessage(LogLevel.INFO, "meterial=" + meterial.getClass().getName());
		
		UploadRequest request = new UploadRequest();
		request.setType(type);
		request.setFileObject(meterial.toFileObject());
		
		UploadResponse response = weiXinUploadService.upload(request);
		if(response!=null){
			model.addAttribute("result", response.getResult());
		}
		
		return "demo/test/upload.page";
	}
	
}
