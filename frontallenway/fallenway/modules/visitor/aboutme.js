var express = require('express');
var request = require('request');
var async = require('async');
var Config = require('../../config/globalconfig.js');

var config = new Config();
var router = express.Router();

router.get('',function(req,res,next){
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
            //请求 管理员 数据
            },function(data,callback){
                request(config.getBackendUrlPrefix() + "admin/find-admin", function (error, response, body) {
		            if (!error && response.statusCode == 200) {
                        var returnData = JSON.parse(body);
                        if(returnData.statusCode != 0){
                            console.log("request for /admin/find-admin errora!");
                        } else {
			                data.admin = returnData.data.admin;
                            callback(null,data);
		                }
                    } else {
			            console.log("request for /admin/find-admin errorb !");
		            }
            	})
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

        var path = "<li><a href = \"/visitor\">Index</a></li>" +
            "<li><a href = \"/visitor/aboutme\" class = \"active\">About me</a></li>";
        result.path = path;

        console.log('url = /visitor, data = ' + JSON.stringify(result) );
        res.render('visitor/aboutme',{'data':result});
    });

});

module.exports = router;
