package minio.client.services;

import com.google.inject.ImplementedBy;
import io.minio.errors.*;
import minio.client.services.impl.FileUploadServiceImpl;

import java.io.File;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * @program: minio-client
 * @description: file upload service
 * @author: shiyi
 * @create: 2020-12-07 17:44
 */
@ImplementedBy(FileUploadServiceImpl.class)
public interface FileUploadService {

    String upload(String filePath) throws IOException, InvalidKeyException, InvalidResponseException, InsufficientDataException, NoSuchAlgorithmException, ServerException, InternalException, XmlParserException, ErrorResponseException;

    String upload(String filePath, String bucket) throws IOException, InvalidKeyException, InvalidResponseException, InsufficientDataException, NoSuchAlgorithmException, ServerException, InternalException, XmlParserException, ErrorResponseException;

    String upload(String filePath, String bucket, String contentType) throws IOException, InvalidKeyException, InvalidResponseException, InsufficientDataException, NoSuchAlgorithmException, ServerException, InternalException, XmlParserException, ErrorResponseException;

    String upload(File file) throws IOException, InvalidKeyException, InvalidResponseException, InsufficientDataException, NoSuchAlgorithmException, ServerException, InternalException, XmlParserException, ErrorResponseException;

    String upload(File file, String bucket) throws IOException, InvalidKeyException, InvalidResponseException, InsufficientDataException, NoSuchAlgorithmException, ServerException, InternalException, XmlParserException, ErrorResponseException;

    String upload(File file, String bucket, String contentType) throws IOException, InvalidKeyException, InvalidResponseException, InsufficientDataException, NoSuchAlgorithmException, ServerException, InternalException, XmlParserException, ErrorResponseException;

}
