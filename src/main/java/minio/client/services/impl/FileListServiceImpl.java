package minio.client.services.impl;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import io.minio.ListObjectsArgs;
import io.minio.MinioClient;
import io.minio.Result;
import io.minio.errors.ErrorResponseException;
import io.minio.errors.InsufficientDataException;
import io.minio.errors.InternalException;
import io.minio.errors.InvalidResponseException;
import io.minio.errors.ServerException;
import io.minio.errors.XmlParserException;
import io.minio.messages.Item;
import minio.client.services.FileListService;

/**
 * @author shiyi
 *
 */
public class FileListServiceImpl implements FileListService{

    @Inject
    @Named("minio.default.bucket")
    @Value("${minio.default.bucket}")
    String bucket;

    @Inject
    @Autowired
    MinioClient minioClient;
    
	/**
	 *
	 */
	@Override
	public List<String> listFileName() throws JsonParseException, InvalidKeyException, JsonMappingException, ErrorResponseException, IllegalArgumentException, InsufficientDataException, InternalException, InvalidResponseException, NoSuchAlgorithmException, ServerException, XmlParserException, IOException {
		return listFileName(bucket);
	}

	/**
	 *
	 */
	@Override
	public List<String> listFileName(String bucket) throws JsonParseException, InvalidKeyException, JsonMappingException, ErrorResponseException, IllegalArgumentException, InsufficientDataException, InternalException, InvalidResponseException, NoSuchAlgorithmException, ServerException, XmlParserException, IOException {
		
		List<String> fileNameList = new ArrayList<>();
		Iterable<Result<Item>> list = minioClient.listObjects(ListObjectsArgs.builder().bucket(bucket).build());
		for(Result<Item> result : list) {
			fileNameList.add(result.get().objectName());
		}
		return fileNameList;
	}

}
