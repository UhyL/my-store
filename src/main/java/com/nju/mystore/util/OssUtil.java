package com.nju.mystore.util;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.PutObjectRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.Date;

/**
 * 这个类实现了文件上传。
 * @ConfigurationProperties注解能够读取配置文件。 利用了阿里云OSS服务。
 */
@Component
@Getter
@Setter
@NoArgsConstructor
@ConfigurationProperties("aliyun.oss")
public class OssUtil {
    private String endpoint;
    private String accessKeyId;
    private String accessKeySecret;
    private String bucketName;

    public String upload(String objectName, InputStream inputStream) {
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentType(getcontentType(objectName.substring(objectName.lastIndexOf("."))));

        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, objectName, inputStream);
        putObjectRequest.setMetadata(objectMetadata);
        try {
            ossClient.putObject(putObjectRequest);
        } finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }
        return ossClient.generatePresignedUrl(bucketName, objectName, new Date()).toString().split("\\?Expires")[0];
    }

    private static String getcontentType(String FilenameExtension) {
        if (FilenameExtension.equalsIgnoreCase(".bmp")) {
            return "image/bmp";
        }
        if (FilenameExtension.equalsIgnoreCase(".gif")) {
            return "image/gif";
        }
        if (FilenameExtension.equalsIgnoreCase(".jpeg") ||
                FilenameExtension.equalsIgnoreCase(".jpg") ||
                FilenameExtension.equalsIgnoreCase(".png")) {
            return "image/jpg";
        }
        if (FilenameExtension.equalsIgnoreCase(".html")) {
            return "text/html";
        }
        if (FilenameExtension.equalsIgnoreCase(".txt")) {
            return "text/plain";
        }
        if (FilenameExtension.equalsIgnoreCase(".vsd")) {
            return "application/vnd.visio";
        }
        if (FilenameExtension.equalsIgnoreCase(".pptx") ||
                FilenameExtension.equalsIgnoreCase(".ppt")) {
            return "application/vnd.ms-powerpoint";
        }
        if (FilenameExtension.equalsIgnoreCase(".docx") ||
                FilenameExtension.equalsIgnoreCase(".doc")) {
            return "application/msword";
        }
        if (FilenameExtension.equalsIgnoreCase(".xml")) {
            return "text/xml";
        }
        if (FilenameExtension.equalsIgnoreCase(".xlsx")) {
            return "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
        }
        return "image/jpg";
    }

}

