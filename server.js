'use strict';

var app = require('./index');
var http = require('http');

var server;

server = http.createServer(app);
server.listen(process.env.OPENSHIFT_NODEJS_PORT || 8000,
  process.env.OPENSHIFT_NODEJS_IP || '0.0.0.0');
server.on('listening', function() {
  console.log('Server listening on http://%s:%d', this.address().address,
    this.address().port);
});
