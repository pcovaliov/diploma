package md.usm.tm.service;

import md.usm.tm.dao.ProjectDaoImpl;
import md.usm.tm.model.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by pcovaliov on 5/29/2017.
 */
@Service
@Transactional
public class ProjectServiceImpl {

    @Autowired
    private ProjectDaoImpl projectDao;

    public List<Project> getAllUsersProjects(int userId){
        return  projectDao.getAllUsersProjects(userId);
    }

    public void deleteProject(int id){
        projectDao.deleteProjectById(id);
    }

    public Project getById(int id){
        return projectDao.getById(id);
    }

    public Project save(Project project) {return projectDao.persist(project);}

    public Project update(Project project) {return projectDao.update(project);}

    public Project findByProjectName(String projectName){
        System.out.println("In Service -----------------------------------------------");
        return projectDao.findByProjectName(projectName);
    }

    public Project findByProjectShortName (String shortName){
        return projectDao.findByProjectShortName(shortName);
    }
}
