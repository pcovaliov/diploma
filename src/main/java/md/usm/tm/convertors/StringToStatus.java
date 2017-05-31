package md.usm.tm.convertors;

import md.usm.tm.dao.StatusDaoImpl;
import md.usm.tm.model.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

/**
 * 5/31/2017
 */
final class StringToStatus implements Converter<String,Status> {

    @Autowired
    StatusDaoImpl statusDao;

    @Override
    public Status convert(String source) {
        return statusDao.getStatusByDescription(source);
    }
}
