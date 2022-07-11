package com.reza.number.number.activity;

import com.reza.number.number.constant.WorkflowConstants;
import com.reza.number.number.entity.Number;
import com.reza.number.number.repository.NumberRepository;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@Component
public class ChooseTypeActivity implements JavaDelegate {

    @Autowired
    private NumberRepository numberRepository;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        Long numberId = (Long) delegateExecution.getVariable(WorkflowConstants.NUMBER_ID_VARIABLE_KEY);
        System.out.printf("ChooseTypeActivity PROCESS: %s\n", numberId);

        String result;
        Optional<Number> currentNumber = numberRepository.findById(numberId);

        if (currentNumber.isEmpty()){
            throw new RuntimeException("number not exist");
        }

        if  (currentNumber.get().getNumber() % 2 == 0) {
            result = "EVEN";
        } else {
            result = "ODD";
        };

        System.out.printf("ChooseTypeActivity DONE: %s\n", numberId);
        delegateExecution.setVariable(WorkflowConstants.WORKFLOW_CHOOSE_TYPE_PASS, result);
    }
}
