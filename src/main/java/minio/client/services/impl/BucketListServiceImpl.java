package minio.client.services.impl;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;

import io.minio.MinioClient;
import io.minio.errors.ErrorResponseException;
import io.minio.errors.InsufficientDataException;
import io.minio.errors.InternalException;
import io.minio.errors.InvalidResponseException;
import io.minio.errors.ServerException;
import io.minio.errors.XmlParserException;
import io.minio.messages.Bucket;
import minio.client.services.BucketListService;

public class BucketListServiceImpl implements BucketListService{
	
    @Inject
    @Autowired
    MinioClient minioClient;

	/**
	 *
	 */
	@Override
	public List<Bucket> listBucket() throws InvalidKeyException, ErrorResponseException, InsufficientDataException, InternalException, InvalidResponseException, NoSuchAlgorithmException, ServerException, XmlParserException, IOException {
		return minioClient.listBuckets();
		
	}

	/**
	 *
	 */
	@Override
	public List<String> listBucketName()
			throws InvalidKeyException, ErrorResponseException, InsufficientDataException, InternalException,
			InvalidResponseException, NoSuchAlgorithmException, ServerException, XmlParserException, IOException {

		CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<>();
		
		listBucket().forEach(bucket ->{
			list.add(bucket.name());
		});
		return list;
	}

}
