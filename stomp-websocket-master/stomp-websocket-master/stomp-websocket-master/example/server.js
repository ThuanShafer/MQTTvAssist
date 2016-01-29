var connect = require('connect');
var logger = require('morgan');
var serveStatic = require('serve-static');

connect()
  .use(logger('dev'))
  .use(serveStatic(__dirname))
  .use(serveStatic(__dirname + '/../lib/'))
  .listen(8080);
console.log('Server running at http://0.0.0.0:8080/');
