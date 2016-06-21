'use strict';

var express = require('express');
var kraken = require('kraken-js');
var mongoLib = require('./lib/mongo');

var options;
var app;

options = {
  onconfig: function(config, next) {
    mongoLib(config.get('mongodb:url'));
    next(null, config);
  }
};

app = module.exports = express();
app.use(kraken(options));
app.on('start', function() {
  console.log('Application ready to serve requests.');
  console.log('Environment: %s', app.kraken.get('env:env'));
});
