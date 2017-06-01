package md.usm.tm.validator;

import md.usm.tm.model.Task;
import md.usm.tm.service.TaskServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * 6/1/2017
 */
@Component
public class TaskValidator implements Validator {

    @Autowired
    private TaskServiceImpl taskService;

    @Override
    public boolean supports(Class<?> clazz) {
        return Task.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Task task = (Task) target;
        Task initialTask = task;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "Required");
        if (task.getProject() == null){
            errors.rejectValue("project", "Select.task.project");
        }

        if (task.getStatus() == null){
            errors.rejectValue("status", "Select.task.status");
        }

        if (task.getPeriod() == null){
            errors.rejectValue("period", "Select.task.period");
        }
    }
}
