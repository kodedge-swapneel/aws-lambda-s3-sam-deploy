package org.example;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.S3Event;
import com.amazonaws.services.lambda.runtime.events.models.s3.S3EventNotification;

public class LambdaS3SamDeploy implements RequestHandler<S3Event, String> {
    @Override
    public String handleRequest(S3Event input, Context context) {
        LambdaLogger logger = context.getLogger();

        S3EventNotification.S3EventNotificationRecord record =  input.getRecords().get(0);

        S3EventNotification.S3Entity s3Entity = record.getS3();
        String srcBucket = s3Entity.getBucket().getName();
        String srcKey = s3Entity.getObject().getUrlDecodedKey();

        logger.log("Event Name : " + record.getEventName());
        logger.log("Bucket : " + srcBucket);
        logger.log("Key: " + srcKey);

        return srcKey;
    }
}
