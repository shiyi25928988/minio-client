package spring.test;

import minio.client.spring.MinioConfiguration;
import minio.client.spring.services.FileUploadService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
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


        System.out.println("111");
    }

}
