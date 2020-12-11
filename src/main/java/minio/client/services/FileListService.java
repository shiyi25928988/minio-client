package minio.client.services;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import minio.client.services.impl.FileListServiceImpl;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.google.inject.ImplementedBy;

import io.minio.errors.ErrorResponseException;
import io.minio.errors.InsufficientDataException;
import io.minio.errors.InternalException;
import io.minio.errors.InvalidResponseException;
import io.minio.errors.ServerException;
import io.minio.errors.XmlParserException;

/**
 * @author shiyi
 *
 */
@ImplementedBy(FileListServiceImpl.class)
public interface FileListService {

	/**
	 * @return
	 * @throws JsonParseException
	 * @throws InvalidKeyException
	 * @throws JsonMappingException
	 * @throws ErrorResponseException
	 * @throws IllegalArgumentException
	 * @throws InsufficientDataException
	 * @throws InternalException
	 * @throws InvalidResponseException
	 * @throws NoSuchAlgorithmException
	 * @throws ServerException
	 * @throws XmlParserException
	 * @throws IOException
	 */
	List<String> listFileName() throws JsonParseException, InvalidKeyException, JsonMappingException, ErrorResponseException, IllegalArgumentException, InsufficientDataException, InternalException, InvalidResponseException, NoSuchAlgorithmException, ServerException, XmlParserException, IOException;
	
	/**
	 * @param bucket
	 * @return
	 * @throws JsonParseException
	 * @throws InvalidKeyException
	 * @throws JsonMappingException
	 * @throws ErrorResponseException
	 * @throws IllegalArgumentException
	 * @throws InsufficientDataException
	 * @throws InternalException
	 * @throws InvalidResponseException
	 * @throws NoSuchAlgorithmException
	 * @throws ServerException
	 * @throws XmlParserException
	 * @throws IOException
	 */
	List<String> listFileName(String bucket) throws JsonParseException, InvalidKeyException, JsonMappingException, ErrorResponseException, IllegalArgumentException, InsufficientDataException, InternalException, InvalidResponseException, NoSuchAlgorithmException, ServerException, XmlParserException, IOException;
}
