package com.example.demo_flowable.delegate;

import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.JavaDelegate;


public class RejectMailDelegate implements JavaDelegate {
    @Override
    public void execute(DelegateExecution execution) {
        String employee = (String) execution.getVariable("employee");
        System.out.println("Leave is declined,mail is sent to " + employee);
    }
}