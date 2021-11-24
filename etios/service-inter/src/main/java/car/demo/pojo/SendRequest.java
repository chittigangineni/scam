package car.demo.pojo;

/**
 * 发送消息请求对象
 * @author yancheng11334
 *
 */
public class SendRequest implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5337827582094338468L;

	private String url;
	
	private String content;
	
	private String certBean;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getCertBean() {
		return certBean;
	}

	public void setCertBean(String certBean) {
		this.certBean = certBean;
	}
	
}
