package minio.client.spring;

import io.minio.MinioClient;
import minio.client.services.FileUploadService;
import minio.client.services.FileUploadServiceImpl;
import okhttp3.OkHttpClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * @program: minio-client
 * @description: spring configuration
 * @author: shiyi
 * @create: 2020-12-08 14:06
 */
@Configuration
@PropertySource(value="classpath:application.properties")
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

    @Value("${minio.bucket}")
    private String region;


    @Bean
    MinioClient getMinioClient() {
        OkHttpClient okHttpClient = new OkHttpClient();
        return MinioClient
                .builder()
                .httpClient(okHttpClient)
                .endpoint(host, port, isHttps)
                .region(region)
                .credentials(username, password)
                .build();
    }

    @Bean
    FileUploadService getFileUploadService(){
        return new FileUploadServiceImpl();
    }
}
