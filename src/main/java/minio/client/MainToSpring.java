package minio.client;

import io.minio.errors.*;
import minio.client.spring.MinioConfiguration;
import minio.client.spring.services.FileUploadService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * @program: minio-client
 * @description: main
 * @author: shiyi
 * @create: 2020-12-08 14:20
 */
public class MainToSpring {

    public static void main(String...strings) {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(MinioConfiguration.class);

        FileUploadService fileUploadService = ctx.getBean(FileUploadService.class);
        try {
            fileUploadService.upload("D:\\Downloads\\minio.exe");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (InvalidResponseException e) {
            e.printStackTrace();
        } catch (InsufficientDataException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (InternalException e) {
            e.printStackTrace();
        } catch (XmlParserException e) {
            e.printStackTrace();
        } catch (ErrorResponseException e) {
            e.printStackTrace();
        }
    }
}
