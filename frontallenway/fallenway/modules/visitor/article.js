var express = require('express');
var request = require('request');
var async = require('async');
var Config = require('../../config/globalconfig.js');

var config = new Config();
var router = express.Router();

router.get('/getArticleDetail',function(req,res,next){
    request(config.getBackendUrlPrefix() + "article/find-article-by-id?id=" + req.query.id,function(error,response,body){
        if( !error && response.statusCode == 200){
            var returnData = JSON.parse(body);

            console.log('returnData.statusCode = ' + returnData.statusCode);
            if(returnData.statusCode != 0){
                console.log('request for find-article-by-id fail!!!');
            } else {
                console.log('article = ' + JSON.stringify(returnData.data.article));
                //res.render('visitor/articleDetail',{'article':returnData.data.article});
                res.render('visitor/common/header_leftSide');
            }
        } else {
            console.log('error = ' + error + ' ,response.statusCode = ' + response.statusCode);
            console.log('request for find-article-by-id fail!!!');
        }


    });
});

module.exports = router;
