package car.demo.service.impl;

import java.security.Security;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import car.demo.pojo.UploadRequest;
import car.demo.pojo.UploadResponse;
import car.demo.service.WeiXinUploadService;
import org.tinygroup.vfs.FileObject;
import org.tinygroup.weixin.WeiXinConnector;
import org.tinygroup.weixin.WeiXinContext;
import org.tinygroup.weixin.impl.WeiXinContextDefault;
import org.tinygroup.weixin.result.AccessToken;
import org.tinygroup.weixinhttp.WeiXinHttpUpload;
import org.tinygroup.weixinkf.kfaccount.UploadHeadIcon;
import org.tinygroup.weixinmeterial.message.PermanentImageMessage;
import org.tinygroup.weixinmeterial.message.PermanentThumbMessage;
import org.tinygroup.weixinmeterial.message.PermanentVideoMessage;
import org.tinygroup.weixinmeterial.message.PermanentVoiceMessage;
import org.tinygroup.weixinmeterial.message.TempImageMessage;
import org.tinygroup.weixinmeterial.message.TempThumbMessage;
import org.tinygroup.weixinmeterial.message.TempVideoMessage;
import org.tinygroup.weixinmeterial.message.TempVoiceMessage;

public class WeiXinUploadServiceImpl implements WeiXinUploadService {

    static{
       //解决"Could not generate DH keypair"
       Security.addProvider(new BouncyCastleProvider());
    }
	private WeiXinConnector weiXinConnector;

	public WeiXinConnector getWeiXinConnector() {
		return weiXinConnector;
	}

	public void setWeiXinConnector(WeiXinConnector weiXinConnector) {
		this.weiXinConnector = weiXinConnector;
	}

	public UploadResponse upload(UploadRequest request) {
		WeiXinHttpUpload upload = createWeiXinHttpUpload(request);
		AccessToken accessToken = weiXinConnector.getAccessToken();
		WeiXinContext context = new WeiXinContextDefault();
		context.setInput(upload);
		context.put(WeiXinConnector.ACCESS_TOKEN, accessToken.getAccessToken());
		String url = weiXinConnector.getWeiXinSender().getWeiXinManager()
				.renderUrl(request.getType(), context);
		String result = weiXinConnector.getWeiXinSender()
				.getWeiXinHttpConnector().upload(url, upload);
				
	    UploadResponse  response = new UploadResponse();
	    response.setResult(result);
		return response;
	}

	private WeiXinHttpUpload createWeiXinHttpUpload(UploadRequest request) {
		String type = request.getType();
		FileObject fileObject = request.getFileObject();
		if (type.equals("uploadTempImage")) {
			TempImageMessage message = new TempImageMessage(fileObject);
			return message;
		} else if (type.equals("uploadTempThumb")) {
			TempThumbMessage message = new TempThumbMessage(fileObject);
			return message;
		} else if (type.equals("uploadTempVoice")) {
			TempVoiceMessage message = new TempVoiceMessage(fileObject);
			return message;
		} else if (type.equals("uploadTempVideo")) {
			TempVideoMessage message = new TempVideoMessage(fileObject);
			return message;
		} else if (type.equals("uploadPermanentImage")) {
			PermanentImageMessage message = new PermanentImageMessage(
					fileObject);
			return message;
		} else if (type.equals("uploadPermanentThumb")) {
			PermanentThumbMessage message = new PermanentThumbMessage(
					fileObject);
			return message;
		} else if (type.equals("uploadPermanentVoice")) {
			PermanentVoiceMessage message = new PermanentVoiceMessage(
					fileObject);
			return message;
		} else if (type.equals("uploadPermanentVideo")) {
			PermanentVideoMessage message = new PermanentVideoMessage(
					fileObject, "视频样例", "视频测试");
			return message;
		} else if (type.equals("uploadHeadIcon")) {
			UploadHeadIcon message = new UploadHeadIcon(fileObject,
					"yanc@Tiny_Framework");
			return message;
		}
		return null;
	}

}
