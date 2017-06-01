package md.usm.tm.convertors;

import md.usm.tm.dao.PeriodDaoImpl;
import md.usm.tm.model.Period;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

/**
 * 5/30/2017
 */
final class StringToPeriod implements Converter<String,Period> {

    @Autowired
    private PeriodDaoImpl periodDao;

    @Override
    public Period convert(String source) {
        if (source.equals("Please Select Period")){
            return null;
        }
        return periodDao.getById(Integer.parseInt(source));
    }
}
