var express = require('express');
var router = express.Router();
var request = require('request');
var Config = require('../../config/globalconfig.js');
var config = new Config();
var MyCookies = require('../../config/mycookies.js');
var mycookies = new MyCookies();

router.get('',function(req,res,next){

    var cookies = mycookies.getMyCookies(req);
    if(cookies['Authorization'] == 'undefined'){
	console.log("cookies[Authorization] == undefined......");
	res.render('admin/login');
    } else {
        doSendRequestGetAllBugs(res,cookies);
    }	
});

function doSendRequestGetAllBugs(res,cookies){
    var url = config.getBackendUrlPrefix() + "auth/bug/get-all-bugs";

    var options = {
	url:url,
	headers:{
		'Authorization': "Bearer " + cookies['Authorization']
	}	
    }

    request(options,function(error,response,body){
        if(!error && response.statusCode == 200){
            var returnData = JSON.parse(body);

            if(returnData.statusCode != 0){
		console.log("unknow error in kong or java,because response.statusCode = 200, returnData.statusCode != 0 ");
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
	    console.log("error = " + error);
	    console.log("response.statusCode = " + response.statusCode);
	    console.log("response.body = " + response.body);
	    
	    if(response.statusCode == 401){
	    	res.render('admin/login'); 
	    } else {
	        res.render('error/unknowerror',{
		    'error':error,
		    'response':response	
	        });
	    }
        }
    });

}

module.exports = router;


