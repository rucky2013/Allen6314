var express = require('express');
var app = express();

app.set('views','views');
app.use(express.static('public'));

var engines = require('consolidate');
app.engine('html',engines.swig);
app.set('view engine','html');

var bodyParser = require('body-parser');
app.use(bodyParser.json());
app.use(bodyParser.urlencoded({extended: true}));

//游客
var visitor_index = require('./modules/visitor/index');
var visitor_article = require('./modules/visitor/article');
var visitor_aboutme = require('./modules/visitor/aboutme');
app.use('/visitor',visitor_index);
app.use('/visitor/article',visitor_article);
app.use('/visitor/aboutme',visitor_aboutme);

//管理员
var admin_index = require('./modules/admin/index.js');
var admin_article = require('./modules/admin/article.js');
var admin_operation = require('./modules/admin/operation.js');
var admin_bug = require('./modules/admin/bug.js');
var admin_login = require('./modules/admin/login.js');
app.use('/admin',admin_index);
app.use('/admin/login',admin_login);
app.use('/admin/article',admin_article);
app.use('/admin/operation',admin_operation);
app.use('/admin/bug',admin_bug);

var port = 7000;
app.listen(port);
console.log("Application started on http://localhost:" + port + "/");
