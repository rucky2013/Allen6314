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
        	doSendRequestGetRecords(res,cookies);
    }
});

function doSendRequestGetRecords(res,cookies){

	var url = config.getBackendUrlPrefix() + "auth/operation/get-records";
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


