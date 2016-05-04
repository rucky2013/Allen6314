var request = require('request');
var async = require('async');
var express = require('express');
var md = require('node-markdown').Markdown;

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
        var path = "<li><a href = \"/admin\">Index</a></li>" +
                "<li><a href = \"/admin/article/articleManage\">Article Manage</a></li>" +
                "<li><a href = \"#\" class = \"active\">Add Article</a></li>";
        result.path = path;

        console.log('err = ' + err + ' ,result = ' + JSON.stringify(result));
        res.render('admin/article/add_updateArticle',{'data':result});
    })
});

router.post('/addArticle/doAdd',function(req,res,next){
    var data = {
         'title': req.body.title,
         'content': req.body.mdData,
         'classifyId': req.body.classifyId,
         'tagId': req.body.tagId
    }

    var url = config.getBackendUrlPrefix() + "auth/article/save-article";
    request.post({url:url,form:data},function(error,response,body){
        if(!error && response.statusCode == 200 ){
            var returnData = JSON.parse(body);
            if(returnData.statusCode == 0){
                res.redirect('/admin/article/articleManage');
            } else {
                console.log('request for getting first level classifies fail! returnData.statusCode = ' + returnData.statusCode);
            }
        } else {
            console.log('request for getting first level classifies fail!!!!!!! error = ' + error + ' , response.statusCode = ' + response.statusCode);
        }
    });
});

router.get('/deleteArticle',function(req,res,next){

    var url = config.getBackendUrlPrefix() + "auth/article/delete-article-by-id";
    var data = {id:req.query.id};

    request.post({url:url,form:data},function(error,response,body){
        res.redirect('/admin/article/articleManage');
    });
});

router.get('/modifyArticle',function(req,res,next){
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
            },function(data,callback){
                request(config.getBackendUrlPrefix() + "article/find-article-by-id?id=" + req.query.id,function(error,response,body){
                    if(!error && response.statusCode == 200 ){
                        var returnData = JSON.parse(body);
                        if(returnData.statusCode == 0){
                            data.article = returnData.data.article;
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
        res.render('admin/article/add_updateArticle',{'data':result});
    })
});

router.get('/articleManage',function(req,res,next){
    request(config.getBackendUrlPrefix() + "article/get-all-articles",function(error,response,body){
        if(!error && response.statusCode == 200 ){
            var returnData = JSON.parse(body);
            if(returnData.statusCode == 0){
                var articles = returnData.data.articles;
                articles.forEach(function(item){
                   var html = md(item.content);
                   item.content = html;
                });

                var path = "<li><a href = \"/admin\">Index</a></li>" +
                "<li>Article Manage</li>";

                var data = {
                    'articles':articles,
                    'path':path
                }

                console.log('returnData = ' + JSON.stringify(data));

                res.render('admin/article/articleManageIndex',{'data':data});
            } else {
                console.log('request for getting first level classifies fail!');
            }
        } else {
            console.log('request for getting first level classifies fail!!!!!!!');
        }
    });
});

module.exports = router;
