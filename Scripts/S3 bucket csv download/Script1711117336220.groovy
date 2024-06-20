import com.kms.katalon.core.configuration.RunConfiguration

import aws.S3_buckets



// AWS credentials
def accessKeyId = System.getenv("accessKeyID") //used environment variable to keep this value secure
def secretAccessKey = System.getenv("secretAccessKey") //used environment variable to keep this value secure
def region = "us-east-1"

// Bucket and object key
def bucketName = "csvtestdatabucket"
def objectKey = "credentials - Sheet1.csv"

// Specify local file path to save the downloaded object
def localFilePath = RunConfiguration.getProjectDir()+ "/Data Files/credentials-Sheet1.csv"


bucket = new S3_buckets()
bucket.s3ObjectDownload(accessKeyId, secretAccessKey, region, bucketName, objectKey, localFilePath)