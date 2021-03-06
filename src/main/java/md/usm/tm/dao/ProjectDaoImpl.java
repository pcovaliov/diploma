package md.usm.tm.dao;

import md.usm.tm.model.Project;
import org.apache.log4j.Logger;
import org.apache.log4j.spi.RootLogger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by pcovaliov on 5/28/2017.
 */
@Repository
public class ProjectDaoImpl extends AbstractGenericDao<Project, Integer> {

    private static final Logger logger = RootLogger.getLogger(ProjectDaoImpl.class);

    @Autowired
    private SessionFactory sessionFactory;

    protected ProjectDaoImpl() {
        super(Project.class);
    }

    public List<Project> getAllUsersProjects(int userId) {
        sessionFactory.getCurrentSession().getEntityManagerFactory();
        return sessionFactory.getCurrentSession().createNativeQuery(
                "SELECT * FROM project WHERE user_id=:user_id", Project.class)
                .setParameter("user_id", userId)
                .getResultList();
    }

    public void deleteProjectById(int id) {
        System.out.println("We in delete mod");
        getCurrentSession().delete(getById(id));
    }

    public Project findByProjectName(String projectName){
        System.out.println("In DAO -----------------------------------------------");
        List<Project> projectList = sessionFactory.getCurrentSession()
                .createQuery("FROM Project WHERE projectName='" + projectName +"'")
                .list();
        if (projectList == null || projectList.size() == 0){
            return null;
        }
        return projectList.get(0);
    }

    public Project findByProjectShortName (String shortName){
        System.out.println("In DAO -----------------------------------------------");
        List<Project> projectList = sessionFactory.getCurrentSession()
                .createQuery("FROM Project WHERE shortName='" + shortName +"'")
                .list();
        if (projectList == null || projectList.size() == 0){
            return null;
        }
        return projectList.get(0);
    }

    @Override
    protected Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }
}
