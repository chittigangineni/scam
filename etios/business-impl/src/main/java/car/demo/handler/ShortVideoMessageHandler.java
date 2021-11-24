package car.demo.handler;

import org.tinygroup.weixin.WeiXinContext;
import org.tinygroup.weixin.WeiXinHandlerMode;
import org.tinygroup.weixin.handler.AbstractWeiXinHandler;
import org.tinygroup.weixin.message.ShortVideoMessage;
import org.tinygroup.weixin.replymessage.TextReplyMessage;

public class ShortVideoMessageHandler extends AbstractWeiXinHandler{

	public WeiXinHandlerMode getWeiXinHandlerMode() {
		return WeiXinHandlerMode.RECEIVE;
	}

	public <T> boolean isMatch(T message, WeiXinContext context) {
		return message instanceof ShortVideoMessage;
	}

	//具体业务处理
	public <T> void process(T message, WeiXinContext context)  {
		ShortVideoMessage mess = ( ShortVideoMessage) message;
		
		//逻辑处理
		TextReplyMessage replyMessage= new TextReplyMessage();
		replyMessage.setContent("回复小视频消息["+mess.getMediaId()+"]");
		replyMessage.setToUserName(mess.getFromUserName());
		replyMessage.setFromUserName(mess.getToUserName());
		replyMessage.setCreateTime((int)(System.currentTimeMillis()/1000));
		
		context.setOutput(replyMessage);
	}

}
