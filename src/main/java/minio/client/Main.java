package minio.client;

import com.google.inject.Guice;
import com.google.inject.Injector;
import io.minio.MinioClient;
import io.minio.errors.*;
import minio.client.modules.MinioClientModule;
import minio.client.services.FileUploadService;
import okhttp3.HttpUrl;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * @program: minio-client
 * @description: main
 * @author: shiyi
 * @create: 2020-12-07 17:40
 */
public class Main {

    public static void main(String...strings) throws IOException, InvalidResponseException, InvalidKeyException, NoSuchAlgorithmException, ServerException, ErrorResponseException, XmlParserException, InsufficientDataException, InternalException {
        Injector injector = Guice.createInjector(new MinioClientModule());
        injector.getInstance(FileUploadService.class).upload("D:\\2007.doc");
    }
}
