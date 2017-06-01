package md.usm.tm.validator;

import md.usm.tm.model.Project;
import md.usm.tm.service.ProjectServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * 5/31/2017
 */
@Component
public class ProjectValidator implements Validator {
    @Autowired
    private ProjectServiceImpl projectService;

    @Override
    public boolean supports(Class<?> clazz) {
        return Project.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Project project = (Project) target;
        Project initialProject = project;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "projectName", "Required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "shortName", "Required");
        if (initialProject.getId() != 0){
            initialProject = projectService.getById(project.getId());
            if (projectService.findByProjectName(project.getProjectName()) != null &&
                    !initialProject.getProjectName().equals(project.getProjectName())){
                errors.rejectValue("projectName", "Duplicate.project.name");
            }

            if (projectService.findByProjectShortName(project.getShortName()) != null &&
                    !initialProject.getShortName().equals(project.getShortName())){
                errors.rejectValue("shortName", "Duplicate.project.shortName");
            }
        }else {
            if (projectService.findByProjectName(project.getProjectName()) != null){
                errors.rejectValue("projectName", "Duplicate.project.name");
            }

            if (projectService.findByProjectShortName(project.getShortName()) != null){
                errors.rejectValue("shortName", "Duplicate.project.shortName");
            }
        }
    }
}
