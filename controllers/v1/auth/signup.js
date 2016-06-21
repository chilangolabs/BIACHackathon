'use strict';

var errors = require('../../../utils/errors');
var jwt = require('jsonwebtoken');
var User = require('../../../models/User');

/* CONSTANTS */
var THREE_MONTHS_IN_SECONDS = 3 * 30 * 24 * 60 * 60;

module.exports = function(router) {
  router.post('/', function(req, res, next) {
    var name = req.body.name;
    var email = req.body.email;
    var phone = req.body.phone;
    var password = req.body.password;

    if (!name || !email || !phone || !password) {
      return next(new errors.ClientBadRequest());
    }

    User.findOne({
      email: email
    }, function(err, user) {
      if (err) {
        return next(new errors.MongoError(err.errmsg));
      }

      if (user) {
        return next(new errors.ClientError('Usuario ya registrado'));
      }

      var candidateUser = new User({
        name: name,
        email: email,
        phone: phone,
        password: password
      });

      candidateUser.save(function(err, user) {
        if (err) {
          return next(new errors.MongoError(err.errmsg));
        }

        var secret = req.app.kraken.get('jwt:secret');
        var jwtUser = {
          id: user._id
        };
        var jwtOptions = {
          algorithm: req.app.kraken.get('jwt:algorithm'),
          expiresIn: THREE_MONTHS_IN_SECONDS
        };

        jwt.sign(jwtUser, secret, jwtOptions, function(err, accessToken) {
          res.status(200).json({
            ok: true,
            message: 'Usuario creado correctamente',
            accessToken: accessToken
          });
        });
      });
    });
  });
};
