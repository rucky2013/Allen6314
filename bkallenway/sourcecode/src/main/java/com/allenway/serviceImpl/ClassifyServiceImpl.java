package com.allenway.serviceImpl;

import com.allenway.dao.ClassifyDao;
import com.allenway.entity.Classify;
import com.allenway.service.ClassifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by wuhuachuan on 16/3/9.
 */

@Service
public class ClassifyServiceImpl implements ClassifyService{

    @Autowired
    private ClassifyDao classifyDao;

    @Override
    public void save(Classify classify) {
        classifyDao.saveAndFlush(classify);
    }

    @Override
    public void deleteClassifyById(Classify classify) {
        classify.setIsDelete(true);
        classifyDao.saveAndFlush(classify);
    }

    @Override
    public Classify findClassifyById(String id) {
        return classifyDao.findClassifyById(id);
    }

    @Override
    public List<Classify> findSubClassifiesByParentClassifyId(String id) {
        return classifyDao.findSubClassifiesByParentClassifyId(id);
    }

    @Override
    public List<Classify> findAllFirstLevelClassifies() {
        return classifyDao.findAllFirstLevelClassifies();
    }
}
