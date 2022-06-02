package com.uet.recipeweb.util;

import java.net.URL;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.amazonaws.HttpMethod;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;

@Component
public class AwsUtils {

	private AmazonS3 s3;

	public AwsUtils(@Value("${aws.accessKey}") String accessKey,
			@Value("${aws.secretKey}") String secretKey,
			@Value("${aws.bucketRegion}") String bucketRegion) {
		AWSCredentials credentials = new BasicAWSCredentials(accessKey, secretKey);
		s3 = AmazonS3ClientBuilder.standard()
				.withCredentials(new AWSStaticCredentialsProvider(credentials))
				.withRegion(bucketRegion).build();
	}
	
	public String generateLink(String objectKey) {
		Date expiration = new Date();
		expiration = new Date();
		long expTimeMillis = expiration.getTime();
		expTimeMillis += 1000 * 60 * 60;
		expiration.setTime(expTimeMillis);
		
		if (objectKey == null) {
			return null;
		}
		GeneratePresignedUrlRequest generatePresignedUrlRequest = new GeneratePresignedUrlRequest("social-cook", objectKey)
				.withMethod(HttpMethod.GET).withExpiration(expiration);
		URL url = s3.generatePresignedUrl(generatePresignedUrlRequest);
		return url.toString();
	}
}