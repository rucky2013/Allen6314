var express = require('express');
var router = express.Router();
var request = require('request');
var Config = require('../../config/globalconfig.js');
var config = new Config();

router.get('',function(req,res,next){
    var url = config.getBackendUrlPrefix() + "auth/bug/get-all-bugs";

    var cookies = {};
    req.headers.cookie && req.headers.cookie.split(';').forEach(function( cookie ) {
        var parts = cookie.split('=');
	cookies[ parts[ 0 ].trim() ] = ( parts[ 1 ] || '' ).trim();
    });
    console.log("######authorization = " + cookies['authorization']);

    request(url,function(error,response,body){
        if(!error){
            var returnData = JSON.parse(body);

            if(returnData.statusCode != 0){
                console.log('request for operation/get-records fail,returnData.statusCode = ' + returnData.statusCode);
            } else {
                var path = "<li><a href = \"/admin\">Index</a></li>" +
                "<li>Bug Manage</li>";

                var data = {
                    'bugs':returnData.data.bugs,
                    'path':path
                }

                res.render('admin/bug/bugIndex',{'data':data});
            }
        } else {
            console.log('request for operation/get-records fail!!');
        }
    });
});

module.exports = router;


