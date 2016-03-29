var express = require('express');
var app = express();

app.set('views','views');
app.use(express.static('public'));

var engines = require('consolidate');
app.engine('html',engines.swig);
app.set('view engine','html');

var index = require('./modules/index');
var aboutme = require('./modules/aboutme');

app.use('/',index);
app.use('/aboutme',aboutme);

var port = 7000;
app.listen(port);
console.log("Application started on http://localhost:" + port + "/");
