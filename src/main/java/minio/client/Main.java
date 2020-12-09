package minio.client;

import com.google.inject.Guice;
import com.google.inject.Injector;
import io.minio.MinioClient;
import io.minio.errors.*;
import lombok.extern.slf4j.Slf4j;
import minio.client.modules.MinioClientModule;
import minio.client.services.FileDownloadService;
import minio.client.services.FileUploadService;
import okhttp3.HttpUrl;

import java.io.File;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

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
        injector.getInstance(FileUploadService.class).upload("D:\\星蝶公主\\星蝶公主.Star.vs.The.Forces.of.Evil.S02E01.WEB-HR.Chs.Eng-Deefun迪幻字幕组.mp4","common", "video/mp4");

//        File file = injector.getInstance(FileDownloadService.class).download("minio.exe");
//
//        log.info(file.getName());
//        log.info(file.getAbsolutePath());
    }
}
