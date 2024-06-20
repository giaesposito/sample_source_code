package aws

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows

import internal.GlobalVariable



import com.amazonaws.auth.AWSStaticCredentialsProvider
import com.amazonaws.auth.BasicAWSCredentials
import com.amazonaws.services.s3.AmazonS3ClientBuilder
import com.amazonaws.services.s3.model.GetObjectRequest
import com.kms.katalon.core.configuration.RunConfiguration

import java.nio.file.Files
import java.nio.file.Paths

public class S3_buckets {

	/**
	 * 
	 * @param accessKeyId - ID for aws access key (best practice to set with environment variable
	 * @param secretAccessKey - access key for aws S3 Bucket (best practice to set with environment variable)
	 * @param region - aws region
	 * @param bucketName - name of S3 bucket
	 * @param objectKey - file name within S3 bucket
	 * @param localFilePath - where you want to store the file (Use RunConfiguration.getProjectDir() + filepath to save to project)
	 */
	public void s3ObjectDownload(String accessKeyId, String secretAccessKey, String region, String bucketName, String objectKey, String localFilePath) {

		// Create AWS credentials object
		def awsCredentials = new BasicAWSCredentials(accessKeyId, secretAccessKey)

		// Create S3 client
		def s3Client = AmazonS3ClientBuilder.standard()
				.withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
				.withRegion(region)
				.build()

		// Download the object to a local file
		s3Client.getObject(new GetObjectRequest(bucketName, objectKey), new File(localFilePath))
	}
}
