package minio.client.services.impl;

import io.minio.*;
import io.minio.errors.*;
import io.minio.http.Method;
import lombok.extern.slf4j.Slf4j;
import minio.client.http.MimeType;
import minio.client.services.FileUploadService;

import org.springframework.beans.factory.annotation.Autowired;
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
	@Autowired
	MinioClient minioClient;

	@Inject
	@Named("minio.default.bucket")
	@Value("${minio.default.bucket}")
	private String defaultBucket;

	@Inject
	@Named("minio.auto.create.bucket")
	@Value("${minio.auto.create.bucket}")
	private boolean autoCreateBucket;

	private static final long UPLOAD_MAX_PART_SIZE = 5 * 1024 * 1024L;

	/**
	 *
	 */
	@Override
	public String upload(String filePath)
			throws IOException, InvalidKeyException, InvalidResponseException, InsufficientDataException,
			NoSuchAlgorithmException, ServerException, InternalException, XmlParserException, ErrorResponseException {
		File file = new File(filePath);
		return upload(file);
	}

	/**
	 *
	 */
	@Override
	public String upload(String filePath, String bucket)
			throws IOException, InvalidKeyException, InvalidResponseException, InsufficientDataException,
			NoSuchAlgorithmException, ServerException, InternalException, XmlParserException, ErrorResponseException {
		File file = new File(filePath);
		return upload(file, bucket);
	}

	/**
	 *
	 */
	@Override
	public String upload(String filePath, String bucket, String contentType)
			throws IOException, InvalidKeyException, InvalidResponseException, InsufficientDataException,
			NoSuchAlgorithmException, ServerException, InternalException, XmlParserException, ErrorResponseException {
		File file = new File(filePath);
		return upload(file, bucket, contentType);
	}

	/**
	 *
	 */
	@Override
	public String upload(File file)
			throws IOException, InvalidKeyException, InvalidResponseException, InsufficientDataException,
			NoSuchAlgorithmException, ServerException, InternalException, XmlParserException, ErrorResponseException {
		return upload(file, defaultBucket);
	}

	/**
	 *
	 */
	@Override
	public String upload(File file, String bucket)
			throws IOException, InvalidKeyException, InvalidResponseException, InsufficientDataException,
			NoSuchAlgorithmException, ServerException, InternalException, XmlParserException, ErrorResponseException {

		return upload(file, bucket, MimeType.getTypeByFileName(file.getName()));
	}

	/**
	 *
	 */
	@Override
	public String upload(File file, String bucket, String contentType)
			throws IOException, InvalidKeyException, InvalidResponseException, InsufficientDataException,
			NoSuchAlgorithmException, ServerException, InternalException, XmlParserException, ErrorResponseException {

		return upload(new BufferedInputStream(new FileInputStream(file)), file.getName(), bucket, contentType);
	}

	/**
	 *
	 */
	@Override
	public String upload(InputStream inputStream, String fileName)
			throws IOException, InvalidKeyException, InvalidResponseException, InsufficientDataException,
			NoSuchAlgorithmException, ServerException, InternalException, XmlParserException, ErrorResponseException {

		return upload(inputStream, fileName, MimeType.getTypeByFileName(fileName));
	}

	/**
	 *
	 */
	@Override
	public String upload(InputStream inputStream, String fileName, String contentType)
			throws IOException, InvalidKeyException, InvalidResponseException, InsufficientDataException,
			NoSuchAlgorithmException, ServerException, InternalException, XmlParserException, ErrorResponseException {

		return upload(inputStream, fileName, contentType, defaultBucket);
	}

	/**
	 *
	 */
	@Override
	public String upload(InputStream inputStream, String fileName, String contentType, String bucket)
			throws IOException, InvalidKeyException, InvalidResponseException, InsufficientDataException,
			NoSuchAlgorithmException, ServerException, InternalException, XmlParserException, ErrorResponseException {

		checkBucket(bucket);

		PutObjectArgs args = PutObjectArgs.builder().stream(inputStream, -1, UPLOAD_MAX_PART_SIZE)
				.contentType(contentType).bucket(bucket).object(fileName).build();

		minioClient.putObject(args);
		return minioClient.getPresignedObjectUrl(GetPresignedObjectUrlArgs.builder().bucket(bucket).object(fileName)
				.expiry(7, TimeUnit.DAYS).method(Method.GET).build());
	}

	/**
	 * @param bucketName
	 * @throws InvalidKeyException
	 * @throws ErrorResponseException
	 * @throws InsufficientDataException
	 * @throws InternalException
	 * @throws InvalidResponseException
	 * @throws NoSuchAlgorithmException
	 * @throws ServerException
	 * @throws XmlParserException
	 * @throws IllegalArgumentException
	 * @throws IOException
	 */
	private void checkBucket(String bucketName) throws InvalidKeyException, ErrorResponseException,
			InsufficientDataException, InternalException, InvalidResponseException, NoSuchAlgorithmException,
			ServerException, XmlParserException, IllegalArgumentException, IOException {
		boolean isExist = minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());
		if (isExist) {
			log.info("Bucket already exists.");
		} else {
			if (autoCreateBucket) {
				log.info("Bucket doesn't exist. Creating new bucket : " + bucketName);
				minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
			}
		}
	}
}
