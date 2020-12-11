package spring.test;

import minio.client.services.FileUploadService;
import minio.client.spring.MinioConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @program: minio-client
 * @description: test
 * @author: shiyi
 * @create: 2020-12-08 14:27
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = MinioConfiguration.class)
public class MinioTest {

    @Autowired
    private FileUploadService fileUploadService;

    @Test
    public void contextLoads() {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(MinioConfiguration.class);
        FileUploadService fileUploadService = ctx.getBean(FileUploadService.class);
//        try {
//            fileUploadService.upload("D:\\Downloads\\minio.exe");
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (InvalidKeyException e) {
//            e.printStackTrace();
//        } catch (InvalidResponseException e) {
//            e.printStackTrace();
//        } catch (InsufficientDataException e) {
//            e.printStackTrace();
//        } catch (NoSuchAlgorithmException e) {
//            e.printStackTrace();
//        } catch (ServerException e) {
//            e.printStackTrace();
//        } catch (InternalException e) {
//            e.printStackTrace();
//        } catch (XmlParserException e) {
//            e.printStackTrace();
//        } catch (ErrorResponseException e) {
//            e.printStackTrace();
//        }
        System.out.println("done");
    }

}
