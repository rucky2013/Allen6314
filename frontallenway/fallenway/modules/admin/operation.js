var express = require('express');
var router = express.Router();
var request = require('request');
var Config = require('../../config/globalconfig.js');
var config = new Config();

router.get('',function(req,res,next){
    var url = config.getBackendUrlPrefix() + "auth/operation/get-records";
    request(url,function(error,response,body){
        if(!error){
            var returnData = JSON.parse(body);

            if(returnData.statusCode != 0){
                console.log('request for operation/get-records fail,returnData.statusCode = ' + returnData.statusCode);
            } else {
                var path = "<li><a href = \"/admin\">Index</a></li>" +
                            "<li>Operation Manage</li>";

                var data = {
                    'records':returnData.data.records,
                    'path':path
                }

                res.render('admin/operation/operIndex',{'data':data});
            }
        } else {
            console.log('request for operation/get-records fail!!');
        }
    });
});

module.exports = router;


