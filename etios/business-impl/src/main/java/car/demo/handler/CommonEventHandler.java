package car.demo.handler;

import org.tinygroup.weixin.WeiXinContext;
import org.tinygroup.weixin.WeiXinHandlerMode;
import org.tinygroup.weixin.event.CommonEvent;
import org.tinygroup.weixin.handler.AbstractWeiXinHandler;
import org.tinygroup.weixin.replymessage.TextReplyMessage;

public class CommonEventHandler extends AbstractWeiXinHandler{

	public WeiXinHandlerMode getWeiXinHandlerMode() {
		return WeiXinHandlerMode.RECEIVE;
	}

	public <T> boolean isMatch(T message, WeiXinContext context) {
		return message instanceof CommonEvent;
	}

	//具体业务处理
	public <T> void process(T message, WeiXinContext context) {
		CommonEvent mess = ( CommonEvent) message;
		
		if(context.getOutput()==null){
			//逻辑处理
			TextReplyMessage replyMessage= new TextReplyMessage();
			replyMessage.setContent("接收服务器事件["+mess.getEvent()+"]");
			replyMessage.setToUserName(mess.getFromUserName());
			replyMessage.setFromUserName(mess.getToUserName());
			replyMessage.setCreateTime((int)(System.currentTimeMillis()/1000));
			
			context.setOutput(replyMessage);
		}
		
	}

}
