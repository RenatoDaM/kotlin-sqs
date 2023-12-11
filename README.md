## Don't use this branch yet
For some reason, I can't create a consumer, so, in  this code I can publish messages, but I can't consume.

It can be a bug, because of this I used in the main branch JMS directly, without micronaut abstraction.

When you run occurs the error: "javax.jms.JMSException: SQSSession does not support MessageSelector.
This should be null."

Why I think that it can be a bug: https://github.com/micronaut-projects/micronaut-jms/issues/439 and the pull request: https://github.com/micronaut-projects/micronaut-jms/pull/464

