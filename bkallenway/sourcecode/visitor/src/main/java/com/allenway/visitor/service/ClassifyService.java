package com.allenway.visitor.service;


import com.allenway.visitor.entity.Classify;

import java.util.List;

/**
 * Created by wuhuachuan on 16/3/9.
 */
public interface ClassifyService {
    void save(Classify classify);

    void deleteClassifyById(Classify classify);

    Classify findClassifyById(String id);

    List<Classify> findSubClassifiesByParentClassifyId(String id);

    List<Classify> findAllFirstLevelClassifies();
}
