aws sns create-topic --name cinema-topic
aws sqs create-queue --queue-name pub-sub-test-consumer --attributes file://sqs-attributes.json
aws sqs get-queue-attributes --queue-url https://sqs.us-east-1.amazonaws.com/yourAccountNumber/ticket_queue --attribute-names QueueArn
aws sns subscribe --topic-arn arn:aws:sns:us-east-1:yourAccountNumber:pub-sub-test --protocol sqs --notification-endpoint arn:aws:sqs:us-east-1:yourAccountNumber:ticket_queue --attributes RawMessageDelivery=true
# aws sns publish --topic-arn arn:aws:sns:us-east-1:yourAccountNumber:cinema-topic --message "{\"price\": 29.99, \"date\": \"2023-12-17 15:30:00\", \"movieId\": 1}"