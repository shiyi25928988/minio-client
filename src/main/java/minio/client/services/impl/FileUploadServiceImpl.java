package minio.client.services.impl;

import io.minio.*;
import io.minio.errors.*;
import io.minio.http.Method;
import lombok.extern.slf4j.Slf4j;
import minio.client.services.FileUploadService;
import org.springframework.beans.factory.annotation.Value;

import javax.inject.Inject;
import javax.inject.Named;
import java.io.*;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.TimeUnit;

/**
 * @program: minio-client
 * @description: upload service
 * @author: shiyi
 * @create: 2020-12-08 10:22
 */
@Slf4j
public class FileUploadServiceImpl implements FileUploadService {

    @Inject
    MinioClient minioClient;

    @Inject
    @Named("minio.bucket")
    @Value("${minio.bucket}")
    private String bucket;

    @Override
    public String upload(String filePath) throws IOException, InvalidKeyException, InvalidResponseException, InsufficientDataException, NoSuchAlgorithmException, ServerException, InternalException, XmlParserException, ErrorResponseException {
        File file = new File(filePath);
        return upload(file);
    }

    @Override
    public String upload(String filePath, String bucket) throws IOException, InvalidKeyException, InvalidResponseException, InsufficientDataException, NoSuchAlgorithmException, ServerException, InternalException, XmlParserException, ErrorResponseException {
        File file = new File(filePath);
        return upload(file, bucket);
    }

    @Override
    public String upload(String filePath, String bucket, String contentType) throws IOException, InvalidKeyException, InvalidResponseException, InsufficientDataException, NoSuchAlgorithmException, ServerException, InternalException, XmlParserException, ErrorResponseException {
        File file = new File(filePath);
        return upload(file, bucket, contentType);
    }

    @Override
    public String upload(File file) throws IOException, InvalidKeyException, InvalidResponseException, InsufficientDataException, NoSuchAlgorithmException, ServerException, InternalException, XmlParserException, ErrorResponseException {
        return upload(file, bucket);
    }

    @Override
    public String upload(File file, String bucket) throws IOException, InvalidKeyException, InvalidResponseException, InsufficientDataException, NoSuchAlgorithmException, ServerException, InternalException, XmlParserException, ErrorResponseException {

        boolean isExist = minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucket).build());
        if(isExist) {
            System.out.println("Bucket already exists.");
        } else {
            minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucket).build());
        }

        PutObjectArgs args = PutObjectArgs.builder()
                .stream(new BufferedInputStream(new FileInputStream(file)), -1 , 5*1024*1024L)
                .bucket(bucket)
                .object(file.getName())
                .build();

        ObjectWriteResponse resp = minioClient.putObject(args);
        return minioClient.getPresignedObjectUrl(GetPresignedObjectUrlArgs.builder().bucket(bucket).object(file.getName()).expiry(7, TimeUnit.DAYS).method(Method.GET).build());
    }

    @Override
    public String upload(File file, String bucket, String contentType) throws IOException, InvalidKeyException, InvalidResponseException, InsufficientDataException, NoSuchAlgorithmException, ServerException, InternalException, XmlParserException, ErrorResponseException {
        PutObjectArgs args = PutObjectArgs.builder()
                .stream(new BufferedInputStream(new FileInputStream(file)), -1 , 5*1024*1024L)
                .contentType(contentType)
                .bucket(bucket)
                .object(file.getName())
                .build();

        ObjectWriteResponse resp = minioClient.putObject(args);
        return minioClient.getPresignedObjectUrl(GetPresignedObjectUrlArgs.builder().bucket(bucket).object(file.getName()).expiry(7, TimeUnit.DAYS).method(Method.GET).build());
    }
}
