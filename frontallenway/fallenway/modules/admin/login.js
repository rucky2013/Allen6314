var express = require('express');
var request = require('request');
var Config = require('../../config/globalconfig.js');
var config = new Config();
var router = express.Router();


router.get('',function(req,res,next){
    res.render('admin/login.html');
});

router.post('/dologin',function(req,res,next){

    var username = req.body.username;
    var password = req.body.password;

    var url = config.getBackendUrlPrefix() + "login";
    var data = {
        "username":username,
        "password":password
    };

    request.post({url:url,form:data},function(error,response,body){
        if(!error){

            console.log("res.body = " + body);

            var returnData = JSON.parse(body);

            if(returnData.statusCode != 0){
                console.log('request for login fail,returnData.statusCode = ' + returnData.statusCode);
            } else {
                var token = returnData.data.token.access_token;
                res.setHeader('Set-Cookie', ['Authorization: Bearer ='+token]);
                res.redirect('/admin');
            }
        } else {
            console.log('request for login fail!!');
        }
    });
})


module.exports = router;







