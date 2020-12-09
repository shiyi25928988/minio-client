package minio.client.services.impl;

import io.minio.GetObjectArgs;
import io.minio.MinioClient;
import io.minio.errors.*;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class FileDownloadServiceImpl implements FileDownloadService {

    @Inject
    @Named("minio.bucket")
    @Value("${minio.bucket}")
    String bucket;

    @Inject
    MinioClient minioClient;

    @Override
    public File download(String fileName, String bucketName) {
        return null;
    }

    @Override
    public File download(String fileName) {

        File file = new File("E:\\download\\" + fileName);

        try (InputStream inputstream = minioClient.getObject(
                GetObjectArgs.builder()
                        .bucket(bucket)
                        .object(fileName)
                        .build())) {

            OutputStream outputStream = new FileOutputStream(file);
            byte[] buffer = new byte[4096];


            int len = 0;
            while ((len = inputstream.read(buffer)) != -1) {

                outputStream.write(buffer, 0, len);

            }
            outputStream.flush();
            // Read data from stream
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (ErrorResponseException e) {
            e.printStackTrace();
        } catch (InvalidResponseException e) {
            e.printStackTrace();
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (InsufficientDataException e) {
            e.printStackTrace();
        } catch (XmlParserException e) {
            e.printStackTrace();
        } catch (InternalException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return file;
    }
}
