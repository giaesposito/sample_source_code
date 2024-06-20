import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

import com.amazonaws.auth.AWSStaticCredentialsProvider
import com.amazonaws.auth.BasicAWSCredentials
import com.amazonaws.services.s3.AmazonS3ClientBuilder
import com.amazonaws.services.s3.model.GetObjectRequest
import com.kms.katalon.core.configuration.RunConfiguration

import java.nio.file.Files
import java.nio.file.Paths


// AWS credentials
def accessKeyId = System.getenv('accessKeyID')
def secretAccessKey = System.getenv('secretAccessKey')
def region = "us-east-1"

// Create AWS credentials object
def awsCredentials = new BasicAWSCredentials(accessKeyId, secretAccessKey)

// Create S3 client
def s3Client = AmazonS3ClientBuilder.standard()
					.withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
					.withRegion(region)
					.build()

// Bucket and object key
def bucketName = "csvtestdatabucket"
def objectKey = "credentials - Sheet1.csv"

// Specify local file path to save the downloaded object
def localFilePath = RunConfiguration.getProjectDir()+ "/Data Files/credentials-Sheet1.csv"

// Download the object to a local file
s3Client.getObject(new GetObjectRequest(bucketName, objectKey), new File(localFilePath))

