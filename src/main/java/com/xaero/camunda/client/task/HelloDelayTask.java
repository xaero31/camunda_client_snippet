package com.xaero.camunda.client.task;

import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.client.spring.annotation.ExternalTaskSubscription;
import org.camunda.bpm.client.task.ExternalTask;
import org.camunda.bpm.client.task.ExternalTaskHandler;
import org.camunda.bpm.client.task.ExternalTaskService;
import org.springframework.stereotype.Component;

import java.util.Map;

import static java.util.Optional.ofNullable;

@Slf4j
@Component
@ExternalTaskSubscription("helloDelayTopic")
public class HelloDelayTask implements ExternalTaskHandler {

    @Override
    public void execute(ExternalTask externalTask, ExternalTaskService externalTaskService) {
        final int count = (int) ofNullable(externalTask.getVariable("count")).orElse(0);
        final Object message = externalTask.getVariable("message");

        log.info("delay task print: {}", message);
        log.info("delay task count: {}", count);

        final Map<String, Object> variables = externalTask.getAllVariables();
        variables.put("count", count + 1);

        externalTaskService.complete(externalTask, variables);
    }
}
