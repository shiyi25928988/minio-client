package minio.client.services.impl;

import io.minio.GetObjectArgs;
import io.minio.MinioClient;
import io.minio.errors.*;
import minio.client.services.FileDownloadService;
import org.springframework.beans.factory.annotation.Value;

import javax.inject.Inject;
import javax.inject.Named;
import java.io.*;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * @program: minio-client
 * @description:
 * @author: shiyi
 * @create: 2020-12-09 08:51
 */
public class FileDownloadServiceImpl implements FileDownloadService {

    @Inject
    @Named("minio.bucket")
    @Value("${minio.bucket}")
    String bucket;

    @Inject
    MinioClient minioClient;
    
	/**
	 *
	 */
	@Override
	public InputStream downloadStream(String fileName) throws InvalidKeyException, ErrorResponseException, InsufficientDataException, InternalException, InvalidResponseException, NoSuchAlgorithmException, ServerException, XmlParserException, IllegalArgumentException, IOException {
		
		return downloadStream(fileName, bucket);
	}


	/**
	 *
	 */
	@Override
	public InputStream downloadStream(String fileName, String bucketName) throws InvalidKeyException, ErrorResponseException, InsufficientDataException, InternalException, InvalidResponseException, NoSuchAlgorithmException, ServerException, XmlParserException, IllegalArgumentException, IOException {
		
        return minioClient.getObject(
                GetObjectArgs.builder()
                        .bucket(bucketName)
                        .object(fileName)
                        .build());
	}
}
