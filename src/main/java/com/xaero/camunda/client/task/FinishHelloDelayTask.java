package com.xaero.camunda.client.task;

import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.client.spring.annotation.ExternalTaskSubscription;
import org.camunda.bpm.client.task.ExternalTask;
import org.camunda.bpm.client.task.ExternalTaskHandler;
import org.camunda.bpm.client.task.ExternalTaskService;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@ExternalTaskSubscription("finishMessageTopic")
public class FinishHelloDelayTask implements ExternalTaskHandler {

    @Override
    public void execute(ExternalTask externalTask, ExternalTaskService externalTaskService) {
        final Object message = externalTask.getVariable("message");
        log.info("finish delay task message: {}", message);

        externalTaskService.complete(externalTask);
    }
}
