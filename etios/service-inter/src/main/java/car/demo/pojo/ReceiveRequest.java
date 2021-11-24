package car.demo.pojo;

public class ReceiveRequest implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2671722303741060170L;

	private String url;
	
	private String content;

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
	
}
