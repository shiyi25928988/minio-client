package minio.client.services;

import io.minio.MinioClient;
import io.minio.ObjectWriteResponse;
import io.minio.PutObjectArgs;
import io.minio.errors.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;

import javax.inject.Inject;
import javax.inject.Named;
import java.io.*;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * @program: minio-client
 * @description: upload service
 * @author: shiyi
 * @create: 2020-12-08 10:22
 */
@Slf4j
public class FileUploadServiceImpl implements FileUploadService{

    @Inject
    MinioClient minioClient;

    @Inject
    @Named("minio.bucket")
    @Value("${minio.bucket}")
    private String bucket;

    @Override
    public void upload(String filePath) throws IOException, InvalidKeyException, InvalidResponseException, InsufficientDataException, NoSuchAlgorithmException, ServerException, InternalException, XmlParserException, ErrorResponseException {
        File file = new File(filePath);
        upload(file);
    }

    @Override
    public void upload(String filePath, String bucket) throws IOException, InvalidKeyException, InvalidResponseException, InsufficientDataException, NoSuchAlgorithmException, ServerException, InternalException, XmlParserException, ErrorResponseException {
        File file = new File(filePath);
        upload(file, bucket);
    }

    @Override
    public void upload(File file) throws IOException, InvalidKeyException, InvalidResponseException, InsufficientDataException, NoSuchAlgorithmException, ServerException, InternalException, XmlParserException, ErrorResponseException {
        upload(file, bucket);
    }

    @Override
    public void upload(File file, String bucket) throws IOException, InvalidKeyException, InvalidResponseException, InsufficientDataException, NoSuchAlgorithmException, ServerException, InternalException, XmlParserException, ErrorResponseException {
        PutObjectArgs args = PutObjectArgs.builder().stream(new BufferedInputStream(new FileInputStream(file)), -1 , 5*1024*1024L).bucket(bucket).object(file.getName()).build();
        ObjectWriteResponse resp = minioClient.putObject(args);
        log.info(resp.etag());
        log.info(resp.versionId());
        log.info(resp.bucket());
        log.info(resp.region());

        resp.headers().forEach(e ->{
            log.info(e.component1());
            log.info(e.component2());
            log.info(e.getFirst());
            log.info(e.getSecond());
        });

    }
}
