var express = require('express');
var app = express();

app.set('views','views');
app.use(express.static('public'));

var engines = require('consolidate');
app.engine('html',engines.swig);
app.set('view engine','html');

//游客
var visitor_index = require('./modules/visitor/index');
var visitor_aboutme = require('./modules/visitor/aboutme');
app.use('/',visitor_index);
app.use('/visitor/aboutme',visitor_aboutme);

//管理员
var admin_article = require('./modules/admin/article.js');
app.use('/admin/article',admin_article);

var port = 7000;
app.listen(port);
console.log("Application started on http://localhost:" + port + "/");
