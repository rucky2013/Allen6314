var express = require('express');
var request = require('request');
var Config = require('../../config/globalconfig.js');
var config = new Config();
var router = express.Router();

router.get('',function(req,res,next){
    res.render('admin/index');
});

module.exports = router;


