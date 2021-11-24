package car.demo.handler;

import org.tinygroup.menucommand.MenuCommandConstants;
import org.tinygroup.menucommand.MenuConfigManager;
import org.tinygroup.template.TemplateContext;
import org.tinygroup.template.TemplateRender;
import org.tinygroup.template.impl.TemplateContextDefault;
import org.tinygroup.weixin.WeiXinContext;
import org.tinygroup.weixin.WeiXinHandlerMode;
import org.tinygroup.weixin.exception.WeiXinException;
import org.tinygroup.weixin.handler.AbstractWeiXinHandler;
import org.tinygroup.weixin.message.TextMessage;
import org.tinygroup.weixin.replymessage.TextReplyMessage;

/**
 * 默认的文本消息处理
 * @author yancheng11334
 *
 */
public class TextMessageHandler extends AbstractWeiXinHandler{
	
	private TemplateRender templateRender;
	private MenuConfigManager menuConfigManager;
	
	public TemplateRender getTemplateRender() {
		return templateRender;
	}

	public void setTemplateRender(TemplateRender templateRender) {
		this.templateRender = templateRender;
	}

	public WeiXinHandlerMode getWeiXinHandlerMode() {
		return WeiXinHandlerMode.RECEIVE;
	}

	public MenuConfigManager getMenuConfigManager() {
		return menuConfigManager;
	}

	public void setMenuConfigManager(MenuConfigManager menuConfigManager) {
		this.menuConfigManager = menuConfigManager;
	}

	public <T> boolean isMatch(T message, WeiXinContext context) {
		return message instanceof TextMessage;
	}

	/**
	 * 具体业务的实现
	 */
	public <T> void process(T message, WeiXinContext context)  {
		TextMessage mess = (TextMessage) message;
		
		try{
			//只有其他文本处理器不处理才进行处理
			if(context.getOutput()==null){
				TemplateContext templateContext = new TemplateContextDefault();
				templateContext.setParent(context);
				//保存系统命令列表
				templateContext.put(MenuCommandConstants.SYSTEM_COMMAND_LIST_PAGE_NAME, menuConfigManager.getSystemCommandList());
				//测试文本自动回复
				TextReplyMessage replyMessage= new TextReplyMessage();
				//支持动态输出系统命令
				String content = templateRender.renderTemplateWithOutLayout("/game/text.page", templateContext);
				replyMessage.setContent(content);
				//replyMessage.setContent("回复文本消息["+mess.getContent()+"]");
				replyMessage.setToUserName(mess.getFromUserName());
				replyMessage.setFromUserName(mess.getToUserName());
				
				context.setOutput(replyMessage);
				templateContext.setParent(null);
			}
		}catch(Exception e){
			throw new WeiXinException(e);
		}
		
		
	}
	
	public int getPriority() {
		return 100;  //降低优先级
	}

}
