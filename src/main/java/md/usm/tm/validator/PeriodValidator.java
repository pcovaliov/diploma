package md.usm.tm.validator;

import md.usm.tm.model.Period;
import md.usm.tm.service.PeriodServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * 6/1/2017
 */
@Component
public class PeriodValidator implements Validator {

    @Autowired
    private PeriodServiceImpl periodService;

    @Override
    public boolean supports(Class<?> clazz) {
        return Period.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Period period = (Period)target;
        Period initialPeriod = period;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "periodName", "Required");
        if (initialPeriod.getId() != 0){
            initialPeriod = periodService.getById(initialPeriod.getId());

            if (periodService.findByPeriodName(period.getPeriodName()) != null &&
                    !initialPeriod.getPeriodName().equals(period.getPeriodName())){
                errors.rejectValue("periodName", "Duplicate.project.name");
            }
        } else {
            if (periodService.findByPeriodName(period.getPeriodName()) != null){
                errors.rejectValue("periodName", "Duplicate.project.name");
            }
        }
    }
}
