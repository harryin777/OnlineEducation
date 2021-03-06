package com.yzz.serviceoss.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.yzz.commonutils.vo.AliOSS;
import com.yzz.serviceoss.service.OssService;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.InputStream;
import java.util.UUID;

/**
 * @ClassName OssServiceImpl
 * @Author yky
 * @Date 2020/12/31
 * @Version 1.0
 */
@Slf4j
@Service
public class OssServiceImpl implements OssService {
	
	@Resource
	private AliOSS aliOSS;
	
	@Override
	public String upLoadAvatar(MultipartFile file) {
		
		String endPoint = aliOSS.getEndpoint();
		String bucketName = aliOSS.getBucketName();
		String accessKeySecret = aliOSS.getAccessKeySecret();
		String accessKeyId = aliOSS.getAccessKeyId();
		String fileName = UUID.randomUUID().toString().replaceAll("-","");
		//获取当前日期
		String datePath = new DateTime().toString("yyyy/MM/dd");
		fileName = datePath+"/"+fileName;
		
		OSS ossClient = new OSSClientBuilder().build(endPoint, accessKeyId, accessKeySecret);
		
		InputStream inputStream = null;
		try {
			inputStream = file.getInputStream();
		} catch (Exception e) {
			log.error("上传头像出错, {}", e.fillInStackTrace().toString());
		}
		/**
		 * 第一个参数是bucket名称
		 * 第二个是文件名称，如果a/bc/d.jpg，会创建a和bc两个文件夹
		 */
		ossClient.putObject(bucketName, fileName, inputStream);
		
		//关闭连接
		ossClient.shutdown();
		
		//拼接返回的url
		String returnUrl = "https://"+bucketName+"."+endPoint+"/"+fileName;
		return returnUrl;
	}
}
