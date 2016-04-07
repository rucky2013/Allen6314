var express = require('express');
var request = require('request');
var async = require('async');
var md = require('node-markdown').Markdown;
var Config = require('../../config/globalconfig.js');

var config = new Config();
var router = express.Router();

router.get('/getArticleDetail',function(req,res,next){
    async.waterfall([
            //请求 文章 数据
            function(callback){
                request(config.getBackendUrlPrefix() + "article/find-article-by-id?id=" + req.query.id,function(error,response,body){
                    if(!error && response.statusCode == 200){
                        var returnData = JSON.parse(body);

                        if(returnData.statusCode != 0){
                            console.log('request for articles fail!');
                         } else {
                             var article = returnData.data.article;
                             article.content = md(article.content);
                            callback(null,returnData.data);
                        }
                     } else {
                         console.log('request for get articles fail!');
                     }
                });
            //请求 标签 数据
            },function(data,callback){
                request(config.getBackendUrlPrefix() + "tag/find-all-tags",function(error,response,body){
                    if(!error && response.statusCode == 200){
                        var returnData = JSON.parse(body);
                        if(returnData.statusCode != 0){
                            console.log('request for get tags fail!');
                         } else {
                         data.tags = returnData.data.tags;
                         callback(null,data);
                        }
                     } else {
                         console.log('request for get tags fail!');
                     }
                });
            }
    ],function(err,result){
        console.log('error = ' + err + ' ,result = ' + JSON.stringify(result));
        res.render('visitor/articleDetail',{'data':result});
    });
});

router.get('/donate',function(req,res,next){
    res.render('visitor/donate');
});

module.exports = router;
