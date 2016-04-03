var request = require('request');
var express = require('express');
var Config = require('../../config/globalconfig.js');
var config = new Config();
var router = express.Router();

//跳到添加文章首页
router.get('/addArticle',function(req,res,next){

    //获得文章分类
    var url = config.getBackendUrlPrefix() + "classify/find-all-first-level-classifies";

    console.log('@@@@@@ url = ' + url);

    request(url,function(error,response,body){
        if(!error && response.statusCode == 200 ){
            var data = JSON.parse(body);
            console.log('&&&&&&&&& = ' + data);
            if(data.statusCode == 0){
                var classifies = data.data.classifies;
                console.log('###### classifies = ' + classifies);
                res.render('admin/addArticle',{'classifies':classifies});
            } else {
                console.log('request for getting first level classifies fail!');
            }
        } else {
            console.log('request for getting first level classifies fail!!!!!!!');
        }
    });
});

module.exports = router;
