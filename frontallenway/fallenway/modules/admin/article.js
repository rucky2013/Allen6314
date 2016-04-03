var request = require('request');
var async = require('async');
var express = require('express');
var Config = require('../../config/globalconfig.js');
var config = new Config();
var router = express.Router();


//跳到添加文章首页
router.get('/addArticle',function(req,res,next){

    async.waterfall([
            //请求文章分类
            function(callback){
                request(config.getBackendUrlPrefix() + "classify/find-all-first-level-classifies",function(error,response,body){
                    if(!error && response.statusCode == 200 ){
                        var returnData = JSON.parse(body);
                        if(returnData.statusCode == 0){
                            callback(null,returnData.data);
                        } else {
                            console.log('request for getting first level classifies fail!');
                        }
                     } else {
                        console.log('request for getting first level classifies fail!!!!!!!');
                    }
                });
            //请求tags
            },function(data,callback){
                request(config.getBackendUrlPrefix() + "tag/find-all-tags",function(error,response,body){
                    if(!error && response.statusCode == 200 ){
                        var returnData = JSON.parse(body);
                        if(returnData.statusCode == 0){
                            data.tags = returnData.data.tags;
                            callback(null,data);
                        } else {
                            console.log('request for getting first level classifies fail!');
                        }
                     } else {
                        console.log('request for getting first level classifies fail!!!!!!!');
                    }
                });
            }
    ],function(err,result){
        console.log('err = ' + err + ' ,result = ' + JSON.stringify(result));
        res.render('admin/addArticle',{'data':result});
    })
});

router.post('/addArticle/doAdd',function(req,res,next){
    var data = {
         'title': req.body.title,
         'content': req.body.mdData,
         'classifyId': req.body.classifyId,
         'tagId': req.body.tagId
    }

    var url = config.getBackendUrlPrefix() + "article/save-article";
    request.post({url:url,form:data},function(error,response,body){

        console.log(response.statusCode + ' url = ' + url + ' error = ' + error + ' data = ' + JSON.stringify(data));

        if(!error && response.statusCode == 200 ){
            var returnData = JSON.parse(body);
            if(returnData.statusCode == 0){
                console.log('save success');
            } else {
                console.log('request for getting first level classifies fail!');
            }
        } else {
            console.log('request for getting first level classifies fail!!!!!!!');
        }
    });
});

module.exports = router;
