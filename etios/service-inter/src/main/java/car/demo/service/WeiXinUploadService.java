package car.demo.service;

import car.demo.pojo.UploadRequest;
import car.demo.pojo.UploadResponse;

/**
 * 上传服务
 * @author yancheng11334
 *
 */
public interface WeiXinUploadService {

	public UploadResponse upload(UploadRequest request);
}
