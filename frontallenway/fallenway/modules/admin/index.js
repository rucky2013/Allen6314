var express = require('express');
var request = require('request');
var Config = require('../../config/globalconfig.js');
var config = new Config();
var router = express.Router();

router.get('',function(req,res,next){
    var path = "<li><a href = \"/admin\" class = \"active\">Index</a></li>";
    var data = {
        'path':path
    };
    res.render('admin/index',{'data':data});
});

module.exports = router;


