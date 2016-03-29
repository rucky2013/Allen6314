var express = require('express');
var router = express.Router();

var request = require('request');

router.get('',function(req,res,next){

	var url = "http://localhost:8080/admin/find-admin";

	var admin;

	request(url, function (error, response, body) {
		if (!error && response.statusCode == 200) {
			admin = JSON.parse(body).data.admin;
			res.render('aboutme',{admin:admin});
		} else {
			console.log("request for http://localhost:8080/admin/getadmin error !");
		}
	})

});

module.exports = router;
