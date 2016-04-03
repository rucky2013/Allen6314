package com.allenway.visitor.dao;

import com.allenway.visitor.entity.Classify;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by wuhuachuan on 16/3/9.
 */
public interface ClassifyDao  extends JpaRepository<Classify, String> {

    @Query("select classify from Classify classify where isDelete=false and id=:id")
    Classify findClassifyById(@Param(value = "id") String id);

    @Query("select classify from Classify classify where isDelete=false and parentClassifyId=:parentClassifyId")
    List<Classify> findSubClassifiesByParentClassifyId(@Param(value = "parentClassifyId") String parentClassifyId);

    @Query("select classify from Classify classify where isDelete=false and parentClassifyId='' order by createDate")
    List<Classify> findAllFirstLevelClassifies();
}
