package minio.client.services;

import com.google.inject.ImplementedBy;

import io.minio.errors.ErrorResponseException;
import io.minio.errors.InsufficientDataException;
import io.minio.errors.InternalException;
import io.minio.errors.InvalidResponseException;
import io.minio.errors.ServerException;
import io.minio.errors.XmlParserException;
import minio.client.services.impl.FileDownloadServiceImpl;

import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * @program: minio-client
 * @description: download
 * @author: shiyi
 * @create: 2020-12-09 08:46
 */
@ImplementedBy(FileDownloadServiceImpl.class)
public interface FileDownloadService {

    /**
     * @param fileName
     * @return
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
    InputStream downloadStream(String fileName) throws InvalidKeyException, ErrorResponseException, InsufficientDataException, InternalException, InvalidResponseException, NoSuchAlgorithmException, ServerException, XmlParserException, IllegalArgumentException, IOException;
    
    /**
     * @param fileName
     * @param bucketName
     * @return
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
    InputStream downloadStream(String fileName, String bucketName) throws InvalidKeyException, ErrorResponseException, InsufficientDataException, InternalException, InvalidResponseException, NoSuchAlgorithmException, ServerException, XmlParserException, IllegalArgumentException, IOException;

    

}
