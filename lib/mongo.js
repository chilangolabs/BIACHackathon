'use strict';

var mongoose = require('mongoose');

module.exports = function(url) {
  mongoose.connect(url);

  var db = mongoose.connection;

  db.once('open', console.log.bind(console, 'Connected to MongoDB'));
  db.on('error', console.error.bind(console, 'MongoDB connection error: '));
};
