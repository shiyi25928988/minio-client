package minio.client.services;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import com.google.inject.ImplementedBy;

import io.minio.errors.ErrorResponseException;
import io.minio.errors.InsufficientDataException;
import io.minio.errors.InternalException;
import io.minio.errors.InvalidResponseException;
import io.minio.errors.ServerException;
import io.minio.errors.XmlParserException;
import minio.client.services.impl.FileDeleteServiceImpl;

/**
 * @author shiyi
 *
 */
@ImplementedBy(FileDeleteServiceImpl.class)
public interface FileDeleteService {

	/**
	 * @param fileName
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
	void deleteFile(String fileName) throws InvalidKeyException, ErrorResponseException, InsufficientDataException, InternalException, InvalidResponseException, NoSuchAlgorithmException, ServerException, XmlParserException, IllegalArgumentException, IOException;

	/**
	 * @param fileName
	 * @param bucket
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
	void deleteFile(String fileName, String bucket) throws InvalidKeyException, ErrorResponseException, InsufficientDataException, InternalException, InvalidResponseException, NoSuchAlgorithmException, ServerException, XmlParserException, IllegalArgumentException, IOException;
}
