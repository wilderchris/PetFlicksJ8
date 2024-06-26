package com.petflicks.app.Service;

import java.io.IOException;
import java.util.UUID;

import com.amazonaws.SdkClientException;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;

import io.github.cdimascio.dotenv.Dotenv;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

@Service
public class S3ServiceImpl implements FileService {

	private final AmazonS3Client awsS3Client;
	static Dotenv dotenv = Dotenv.load();
	
	public static final String BUCKET = dotenv.get("ENV_AWS_S3_BUCKET");
	
	public S3ServiceImpl(AmazonS3Client awsS3Client) {
		super();
		this.awsS3Client = awsS3Client;
	}

	@Override
	public String uploadFile(MultipartFile file) {
		
		//upload to s3
		
		String fileExt = StringUtils.getFilenameExtension(file.getOriginalFilename());
		
		String key = UUID.randomUUID().toString() + fileExt;
		ObjectMetadata metadata = new ObjectMetadata();
		metadata.setContentLength(file.getSize());
		metadata.setContentType(file.getContentType());

		try {
			awsS3Client.putObject(BUCKET, key,file.getInputStream(), metadata);
		} catch (SdkClientException | IOException e) {
				throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
							" An Exception was occured when uploading");
		}
		
		awsS3Client.setObjectAcl(BUCKET, key, CannedAccessControlList.PublicRead);
		
		return awsS3Client.getResourceUrl(dotenv.get("ENV_AWS_S3_BUCKET"), key);
		
	}
}
