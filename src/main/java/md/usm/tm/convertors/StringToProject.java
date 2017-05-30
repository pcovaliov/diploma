package md.usm.tm.convertors;

import md.usm.tm.dao.ProjectDaoImpl;
import md.usm.tm.model.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
/**
 * Created by pcovaliov on 5/30/2017.
 */
final class StringToProject implements Converter<String,Project> {

    @Autowired
    private ProjectDaoImpl projectDao;

    @Override
    public Project convert(String source) {
        return projectDao.getById(Integer.parseInt(source));
    }
}
