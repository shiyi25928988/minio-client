package minio.client.modules;

import io.minio.MinioClient;
import io.minio.credentials.Credentials;
import io.minio.credentials.Provider;
import io.minio.messages.ResponseDate;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;

import javax.inject.Inject;
import javax.inject.Named;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

/**
 * @program: minio-client
 * @description: builder
 * @author: shiyi
 * @create: 2020-12-08 08:41
 */
public class MinioClientBuilder {

    @Inject
    @Named("minio.host")
    private String host;

    @Inject
    @Named("minio.host.port")
    private int port;

    @Inject
    @Named("minio.username")
    private String username;

    @Inject
    @Named("minio.password")
    private String password;

    @Inject
    @Named("minio.sessiontoken")
    private String sessiontoken;

    @Inject
    @Named("minio.host.isHttps")
    private boolean isHttps;

    @Inject
    @Named("minio.bucket")
    private String region;

    @Inject
    private OkHttpClient okHttpClient;

    public MinioClient build() {

        return MinioClient
                .builder()
                .httpClient(okHttpClient)
                .endpoint(host, port, isHttps)
                .region(region)
                .credentials(username, password)
                .build();

    }
}
