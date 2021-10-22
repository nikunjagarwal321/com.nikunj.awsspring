package com.nikunj.awsspring.s3;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.core.ResponseInputStream;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.nio.file.Paths;

@Slf4j
@Component
public class S3OperationsImpl implements CommandLineRunner {

    private S3Client s3Client = S3Client.builder().region(Region.AP_SOUTH_1).build();

    @Value("${bucket}")
    private String bucketName;

    private String key = "file1";

    @Override
    public void run(String... args) throws Exception {
        upload();
        download();
        delete();
    }

    public void upload(){
        log.info("Inside upload");
        PutObjectRequest objectRequest = PutObjectRequest.builder()
                .bucket(bucketName)
                .key(key)
                .build();

        Path path = Paths.get("src/main/resources/static/payload.txt");
        RequestBody requestBody = RequestBody.fromFile(path);
        log.info("Uploading file : {}", requestBody);
        s3Client.putObject(objectRequest, requestBody);
        log.info("File uploaded successfully");
    }

    public void download() {
        log.info("Inside download");
        GetObjectRequest getObject = GetObjectRequest.builder()
                .bucket(bucketName)
                .key(key)
                .build();
        ResponseInputStream<GetObjectResponse> response = s3Client.getObject(getObject);
        logFileContents(response);
        log.info("File downloaded successfully : {}", response.toString());
    }

    public void delete(){
        log.info("Inside delete");
        DeleteObjectRequest deleteObjectRequest = DeleteObjectRequest.builder()
                .bucket(bucketName)
                .key(key)
                .build();
        DeleteObjectResponse response = s3Client.deleteObject(deleteObjectRequest);
        log.info("File deleted successfully : {}", response.toString());
    }

    private void logFileContents(ResponseInputStream<GetObjectResponse> response){
        BufferedReader reader = new BufferedReader(new InputStreamReader(response));
        String line;
        String file = "";
        try {
            while ((line = reader.readLine()) != null) {
                log.info(line);
                file = file + line + "\n" ;
            }
            log.info("File Contents : " + file);
        } catch(Exception e) {
            log.error("Error: {}", e);
        }
    }

}
