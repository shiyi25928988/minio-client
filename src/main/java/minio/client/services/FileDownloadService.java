package minio.client.services;

import com.google.inject.ImplementedBy;
import minio.client.services.impl.FileDownloadServiceImpl;
import org.springframework.beans.factory.annotation.Value;

import javax.inject.Inject;
import javax.inject.Named;
import java.io.File;

/**
 * @program: minio-client
 * @description: download
 * @author: shiyi
 * @create: 2020-12-09 08:46
 */
@ImplementedBy(FileDownloadServiceImpl.class)
public interface FileDownloadService {

    File download(String fileName, String bucketName);

    File download(String fileName);

}
