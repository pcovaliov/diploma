package md.usm.tm.service;

import md.usm.tm.dao.StatusDaoImpl;
import md.usm.tm.model.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by pcovaliov on 5/30/2017.
 */
@Service
public class StatusServiceImpl {

    @Autowired
    private StatusDaoImpl statusDao;

    public Status getStatusById(int id) {
        return statusDao.getById(id);
    }

    public List<Status> getAll() {
        return statusDao.getAll();
    }
}
