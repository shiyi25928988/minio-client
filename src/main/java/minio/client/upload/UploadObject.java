package minio.client.upload;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import com.google.common.base.Strings;

import io.minio.errors.ErrorResponseException;
import io.minio.errors.InsufficientDataException;
import io.minio.errors.InternalException;
import io.minio.errors.InvalidResponseException;
import io.minio.errors.ServerException;
import io.minio.errors.XmlParserException;
import lombok.NonNull;
import minio.client.http.MimeType;
import minio.client.services.FileUploadService;

public class UploadObject {

	private String filePath;

	private File file;

	private InputStream inputStream;

	private String fileName;

	private String bucket;

	private String subFolder;

	private String contentType;

	private FileUploadService fileUploadService;

	public UploadObject file(@NonNull File file) throws FileNotFoundException {
		this.file = file;
		this.fileName = file.getName();
		this.inputStream = new BufferedInputStream(new FileInputStream(file));
		this.contentType = MimeType.getTypeByFileName(this.fileName);
		return this;
	}

	public UploadObject filePath(@NonNull String filePath) throws FileNotFoundException {
		this.filePath = filePath;
		this.file = new File(filePath);
		this.fileName = this.file.getName();
		this.inputStream = new BufferedInputStream(new FileInputStream(file));
		this.contentType = MimeType.getTypeByFileName(this.fileName);
		return this;
	}

	public UploadObject inputStream(@NonNull InputStream inputStream) {
		this.inputStream = inputStream;
		return this;
	}

	public UploadObject contentType(@NonNull String contentType) {
		this.contentType = contentType;
		return this;
	}

	public UploadObject bucket(@NonNull String bucket) {
		this.bucket = bucket;
		return this;
	}

	public UploadObject subFolder(@NonNull String subFolder) {
		if (!subFolder.endsWith("/")) {
			this.subFolder = subFolder + "/";
		} else {
			this.subFolder = subFolder;
		}
		return this;
	}

	public UploadObject fileUploadService(@NonNull FileUploadService fileUploadService) {
		this.fileUploadService = fileUploadService;
		return this;
	}

	public void upload()
			throws InvalidKeyException, InvalidResponseException, InsufficientDataException, NoSuchAlgorithmException,
			ServerException, InternalException, XmlParserException, ErrorResponseException, IOException {

		if (Strings.isNullOrEmpty(bucket)) {
			if (Strings.isNullOrEmpty(subFolder)) {
				this.fileUploadService.upload(inputStream, fileName, contentType);
			} else {
				this.fileUploadService.upload(inputStream, subFolder.concat(fileName), contentType);
			}
		}else {
			if (Strings.isNullOrEmpty(subFolder)) {
				this.fileUploadService.upload(inputStream, fileName, contentType, bucket);
			} else {
				this.fileUploadService.upload(inputStream, subFolder.concat(fileName), contentType, bucket);
			}
		}

	}

}
