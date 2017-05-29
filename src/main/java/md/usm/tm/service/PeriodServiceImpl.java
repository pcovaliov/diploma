package md.usm.tm.service;

import md.usm.tm.dao.PeriodDaoImpl;
import md.usm.tm.model.Period;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by pcovaliov on 5/29/2017.
 */
@Service
@Transactional
public class PeriodServiceImpl {

    @Autowired
    PeriodDaoImpl periodDao;

    public List<Period> getAllUsersPeriods(int userId){
        return periodDao.getAllUsersProjects(userId);
    }
}
