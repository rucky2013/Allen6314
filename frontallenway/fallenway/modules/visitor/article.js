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
                console.log('req.query.id = ' + req.query.id);
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
        console.log('url = visitor/article/getArticleDetail, error = '+ err + ' ,data = ' + JSON.stringify(result));
        res.render('visitor/articleDetail',{'data':result});
    });
});

router.post('/leaveMsg',function(req,res,next){
    var url = config.getBackendUrlPrefix() + "comment/add-comment";
    var data = {
        'id':req.body.id,
        'name':req.body.name,
        'email':req.body.email,
        'content':req.body.content
    };
    request.post({url:url,form:data},function(err,response,body){

        console.log('response.statusCode = ' + response.statusCode);

        if(!err && response.statusCode == 200){
            var returnData = JSON.parse(body);
            if(returnData.statusCode != 0){
                console.log('request for save comment fail!');
            } else {
                res.redirect('/visitor/article/getArticleDetail?id=' + req.body.id);
            }
        } else {
            console.log('request for save comment fail!');
        }
    });
})

module.exports = router;
