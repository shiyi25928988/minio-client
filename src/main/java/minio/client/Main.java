package minio.client;

import io.minio.errors.*;
import lombok.extern.slf4j.Slf4j;
import minio.client.modules.MinioClientModule;
import minio.client.services.FileListService;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import com.google.inject.Guice;
import com.google.inject.Injector;

/**
 * @program: minio-client
 * @description: main
 * @author: shiyi
 * @create: 2020-12-07 17:40
 */
@Slf4j
public class Main {

    public static void main(String...strings) throws IOException, InvalidResponseException, InvalidKeyException, NoSuchAlgorithmException, ServerException, ErrorResponseException, XmlParserException, InsufficientDataException, InternalException {
        Injector injector = Guice.createInjector(new MinioClientModule());
        FileListService fileUploadService = injector.getInstance(FileListService.class);
        fileUploadService.listFileName().forEach(e ->{
        	log.info(e);
        });
        
    }
}
