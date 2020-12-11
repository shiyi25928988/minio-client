package minio.client.modules;

import com.google.inject.AbstractModule;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.name.Names;
import io.minio.MinioClient;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;

import java.io.IOException;

/**
 * @program: minio-client
 * @description: module
 * @author: shiyi
 * @create: 2020-12-07 17:42
 */
@Slf4j
public class MinioClientModule extends AbstractModule {

    /**
     *
     */
    @Override
    protected void configure() {
        try {
            System.getProperties().load(this.getClass().getClassLoader().getResourceAsStream("application.properties"));
            Names.bindProperties(binder(), System.getProperties());
        } catch (IOException e) {
            log.error(e.getMessage());
            System.exit(1);
        }

        bind(OkHttpClient.class).toProvider(OkHttpClientProvider.class);
        bind(MinioClientBuilder.class).toInstance(new MinioClientBuilder());
        bind(MinioClient.class).toProvider(MinioClientProsvider.class);
    }


    /**
     * OkHttpClientProvider
     * 
     * @author shiyi
     *
     */
    public static class OkHttpClientProvider implements Provider<OkHttpClient>{
        @Override
        public OkHttpClient get() {
            OkHttpClient okHttpClient = new OkHttpClient();
            return okHttpClient;
        }
    }

    /**
     * MinioClientProsvider
     * 
     * @author shiyi
     *
     */
    public static class MinioClientProsvider implements Provider<MinioClient> {

        @Inject
        MinioClientBuilder builder;

        @Override
        public MinioClient get() {
            return builder.build();
        }
    }
}
