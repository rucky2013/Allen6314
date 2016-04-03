var express = require('express');
var router = express.Router();
var request = require('request');
var async = require('async');

var Config = require('../../config/globalconfig.js');
var config = new Config();

router.get('', function(req, res, next) {
    async.waterfall([
            //请求 分类 数据
            function(callback){
                request(config.getBackendUrlPrefix() + "classify/find-all-first-level-classifies",function(error,response,body){
                    if(!error && response.statusCode == 200){
                        var returnData = JSON.parse(body);

                        if(returnData.statusCode != 0){
                            console.log('request for getFirstLevelClassifies fail!');
                         } else {
                            callback(null,returnData.data);
                        }
                     } else {
                         console.log('request for getFirstLevelClassifies fail!');
                     }
                });
            //请求 主页文章 数据
            },function(data,callback){
                /*request(config.getBackendUrlPrefix() + "classify/find-all-first-level-classifies",function(error,response,body){*/
                    //if(!error && response.statusCode == 200){
                        //var returnData = JSON.parse(body);

                        //if(returnData.statusCode != 0){
                            //console.log('request for getFirstLevelClassifies fail!');
                         //} else {
                         //var data = {'classifies':returnData.data.classifies};
                         //_result.push(data);
                         //callback(null,data);
                        //}
                     //} else {
                         //console.log('request for getFirstLevelClassifies fail!');
                     //}
                /*});*/

                callback(null,data);
            //请求 标签 数据
            },function(data,callback){
                request(config.getBackendUrlPrefix() + "tag/find-all-tags",function(error,response,body){
                    if(!error && response.statusCode == 200){
                        var returnData = JSON.parse(body);

                        if(returnData.statusCode != 0){
                            console.log('request for getFirstLevelClassifies fail!');
                         } else {
                         data.tags = returnData.data.tags;
                         callback(null,data);
                        }
                     } else {
                         console.log('request for getFirstLevelClassifies fail!');
                     }
                });
            }
    ],function(err,result){
        console.log('err = '+ err + ',result = ' + JSON.stringify(result));
        console.log('###### = ' + result.tags);

        res.render('index',{'data':result});
    });
});

module.exports = router;