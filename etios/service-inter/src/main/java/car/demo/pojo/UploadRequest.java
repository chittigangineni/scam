package car.demo.pojo;

import org.tinygroup.vfs.FileObject;

public class UploadRequest implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8267430588211697633L;

	private String type;
	
	private FileObject fileObject;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public FileObject getFileObject() {
		return fileObject;
	}

	public void setFileObject(FileObject fileObject) {
		this.fileObject = fileObject;
	}

	
}
