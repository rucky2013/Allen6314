package com.allenway.controller;

import com.allenway.entity.Article;
import com.allenway.entity.Classify;
import com.allenway.service.ArticleService;
import com.allenway.service.ClassifyService;
import com.allenway.utils.ReturnStatusCode;
import com.allenway.utils.ReturnTemplate;
import com.allenway.utils.ValidUtils;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by wuhuachuan on 16/3/9.
 */

@Slf4j
@Data
@RestController
@RequestMapping(value = "/classify")
public class ClassifyController {

    @Autowired
    private ClassifyService classifyService;

    @Autowired
    private ArticleService articleService;

    /**
     * 增加类别,或者修改类别
     * @param classify
     * @return
     */
    @RequestMapping(value = "/save-classify",method = RequestMethod.POST)
    public String saveClassify(@RequestParam Classify classify){

        ReturnTemplate returnTemplate = new ReturnTemplate();

        if(validClassifyParam(classify)){
            classifyService.save(classify);

            return returnTemplate.toString();
        } else {
            returnTemplate.setStatusCode(ReturnStatusCode.PARAM_INVALID);
            return  returnTemplate.toString();
        }
    }

    /**
     * 验证新添加的类别的参数合理性
     * @param classify
     * @return
     */
    private boolean validClassifyParam(Classify classify) {
        if(StringUtils.isEmpty(classify.getName()) || StringUtils.isEmpty(classify.getParentClassifyId())){
            return false;
        } else {
            return true;
        }
    }

    /**
     * 根据 id 删除类别
     * @param id
     * @return
     */
    @RequestMapping(value = "/delete-classify-by-id",method = RequestMethod.POST)
    public String deleteClassifyById(@RequestParam String id){

        ReturnTemplate returnTemplate = new ReturnTemplate();

        if(ValidUtils.validIdParam(id)){
            //如果该分类下有子分类 或者 有 还有 文章,那么则不能删除
            if(classifyHasArticleOrHasSubClassify(id)){
                returnTemplate.setStatusCode(ReturnStatusCode.CLASSIFY_HAS_ARTICLE_OR_SUBCLASSIFY);
                return returnTemplate.toString();
            } else {
                Classify classify = classifyService.findClassifyById(id);
                if(classify == null){
                    returnTemplate.setStatusCode(ReturnStatusCode.DATA_IS_NOT_FOUND);
                    return returnTemplate.toString();
                } else {
                    classifyService.deleteClassifyById(classify);
                    return returnTemplate.toString();
                }
            }
        } else {
            returnTemplate.setStatusCode(ReturnStatusCode.PARAM_INVALID);
            return  returnTemplate.toString();
        }
    }

    /**
     * 删除
     * 如果该分类下有子分类 或者 有 还有 文章,那么则不能删除
     * @param id
     * @return
     */
    private boolean classifyHasArticleOrHasSubClassify(String id) {

        List<Classify> classifyList = classifyService.findSubClassifiesByParentClassifyId(id);
        //判断是否有子分类
        if(classifyList != null && classifyList.size() != 0){
            return true;
        } else {
            List<Article> articleList = articleService.findArticlesByClassifyId(id);
            //判断是否有文章
            if(articleList != null && articleList.size() != 0){
                return true;
            } else {
                return false;
            }
        }
    }

    /**
     * 根据 ID 查找某一个类别信息
     * @param id
     * @return
     */
    @RequestMapping(value = "/find-classify-by-id",method = RequestMethod.POST)
    public String findClassifyById(@RequestParam String id){

        ReturnTemplate returnTemplate = new ReturnTemplate();

        if(ValidUtils.validIdParam(id)){
            Classify classify = classifyService.findClassifyById(id);

            if(classify == null) {
                returnTemplate.setStatusCode(ReturnStatusCode.DATA_IS_NOT_FOUND);
                return  returnTemplate.toString();
            } else {
                returnTemplate.addData("classify",classify);
                return returnTemplate.toString();
            }

        } else {
            returnTemplate.setStatusCode(ReturnStatusCode.PARAM_INVALID);
            return  returnTemplate.toString();
        }
    }
}
