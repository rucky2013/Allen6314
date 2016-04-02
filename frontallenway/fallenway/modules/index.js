var express = require('express');
var router = express.Router();
var request = require('request');
var async = require('async');

var Config = require('../config/globalconfig.js');
var config = new Config();

router.get('', function(req, res, next) {
    var _result = [];

    async.waterfall([
            //请求 分类 数据
            function(callback){
                request(config.getBackendUrlPrefix() + "classify/find-all-first-level-classifies",function(error,response,body){
                    if(!error && response.statusCode == 200){
                        var returnData = JSON.parse(body);

                        if(returnData.statusCode != 0){
                            console.log('request for getFirstLevelClassifies fail!');
                         } else {
                         var data = {'classifies':returnData.data.classifies};
                         _result.push(data);
                         callback(null,data);
                        }
                     } else {
                         console.log('request for getFirstLevelClassifies fail!');
                     }
                });
            //请求 主页文章 数据
            },function(data,callback){
                callback(null,data);
            //请求 标签 数据
            },function(data,callback){
                callback(null,data);
            }
    ],function(err,result){
        console.log('err = '+ err + ',result = ' + JSON.stringify(result));
        res.render('index',{'data':result});
    });
});

module.exports = router;
