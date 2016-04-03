var express = require('express');
var request = require('request');
var Config = require('../../config/globalconfig.js');

var config = new Config();
var router = express.Router();

router.get('',function(req,res,next){

	var url = config.getBackendUrlPrefix() + "admin/find-admin";

	var admin;

	request(url, function (error, response, body) {
		if (!error && response.statusCode == 200) {
            var data = JSON.parse(body).data;
            if(data.statusCode != 0){
                console.log("request for /admin/find-admin error!");
            }
			admin = data.admin;
			res.render('visitor/aboutme',{admin:admin});
		} else {
			console.log("request for /admin/find-admin error !");
		}
	})

});

module.exports = router;
