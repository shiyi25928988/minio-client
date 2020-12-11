package minio.client.spring;

import io.minio.MinioClient;
import minio.client.services.BucketListService;
import minio.client.services.FileDeleteService;
import minio.client.services.FileDownloadService;
import minio.client.services.FileListService;
import minio.client.services.FileUploadService;
import minio.client.services.impl.BucketListServiceImpl;
import minio.client.services.impl.FileDeleteServiceImpl;
import minio.client.services.impl.FileDownloadServiceImpl;
import minio.client.services.impl.FileListServiceImpl;
import minio.client.services.impl.FileUploadServiceImpl;
import okhttp3.OkHttpClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @program: minio-client
 * @description: spring configuration
 * @author: shiyi
 * @create: 2020-12-08 14:06
 */
@Configuration
@PropertySource(value="classpath:application.properties", ignoreResourceNotFound=true, encoding="UTF-8")
public class MinioConfiguration {

    @Value("${minio.host}")
    private String host;

    @Value("${minio.host.port}")
    private int port;

    @Value("${minio.username}")
    private String username;

    @Value("${minio.password}")
    private String password;

    @Value("${minio.host.isHttps}")
    private boolean isHttps;

    @Bean
    MinioClient getMinioClient() {
        OkHttpClient okHttpClient = new OkHttpClient();
        return MinioClient
                .builder()
                .httpClient(okHttpClient)
                .endpoint(host, port, isHttps)
                .region(null)
                .credentials(username, password)
                .build();
    }

    @Bean
    FileUploadService getFileUploadService(){
        return new FileUploadServiceImpl();
    }

    @Bean
    FileDownloadService getFileDownloadService(){
        return new FileDownloadServiceImpl();
    }
    
    @Bean
    FileDeleteService getFileDeleteService(){
        return new FileDeleteServiceImpl();
    }
    
    @Bean
    BucketListService getBucketListService(){
        return new BucketListServiceImpl();
    }
    
    @Bean
    FileListService getFileListService(){
        return new FileListServiceImpl();
    }
    
}
