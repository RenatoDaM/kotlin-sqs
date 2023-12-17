## Bugs bugs bugs
The actual version Micronaut JMS core (3.1.0) have some bugs checking messageSelector, but someone
already have done a pull request that I downloaded and compiled at "libs" directory

# Kotlin Micronaut SQS
Micronaut SQS library is just an abstraction and quality life improvements from JMS Library.

In this example I created an SQS named "ticket_queue" (you can create by CLI or AWS Console),
the tickets are published to an SQS queue and consumed in the listener.

# How to run
- Create an application yml (or .properties) structure containing your AWS Credentials and
another information like database, jpa, etc.

- Do login via CLI with "aws configure" terminal command

Now just run the application and hit the /tickets endpoint with a json like:

    {
        "price": 2.99,
        "date": "2023-12-11 15:30:00",
        "movieId": 1
    }

But I haven't implemented movie's endpoint yet...