package minio.client.services.impl;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import io.minio.MinioClient;
import io.minio.RemoveObjectArgs;
import io.minio.errors.ErrorResponseException;
import io.minio.errors.InsufficientDataException;
import io.minio.errors.InternalException;
import io.minio.errors.InvalidResponseException;
import io.minio.errors.ServerException;
import io.minio.errors.XmlParserException;
import minio.client.services.FileDeleteService;

/**
 * @author shiyi
 *
 */
public class FileDeleteServiceImpl implements FileDeleteService{
	
    @Inject
    @Named("minio.default.bucket")
    @Value("${minio.default.bucket}")
    String bucket;

    @Inject
    @Autowired
    MinioClient minioClient;

	@Override
	public void deleteFile(String fileName) throws InvalidKeyException, ErrorResponseException, InsufficientDataException, InternalException, InvalidResponseException, NoSuchAlgorithmException, ServerException, XmlParserException, IllegalArgumentException, IOException {
		deleteFile(fileName, bucket);
	}

	@Override
	public void deleteFile(String fileName, String bucket) throws InvalidKeyException, ErrorResponseException, InsufficientDataException, InternalException, InvalidResponseException, NoSuchAlgorithmException, ServerException, XmlParserException, IllegalArgumentException, IOException {
		minioClient.removeObject(RemoveObjectArgs.builder().bucket(bucket).object(fileName).build());
	}

}
