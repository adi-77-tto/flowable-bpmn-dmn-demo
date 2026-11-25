package com.example.demo_flowable.delegate;

import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.JavaDelegate;

public class SendMailDelegate implements JavaDelegate {
    @Override
    public void execute(DelegateExecution execution) {
        String employee = (String) execution.getVariable("employee");
        System.out.println("Leave is approved,mail is sent to " + employee);
    }
}