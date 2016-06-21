'use strict';

var errors = require('../../../utils/errors');
var jwt = require('jsonwebtoken');
var User = require('../../../models/User');

module.exports = function(router) {
  router.route('/')
    .get(function(req, res, next) {
      var accessToken = req.headers['x-access-token'];

      if (!accessToken) {
        return next(new errors.ClientUnauthorized());
      }

      var jwtSecret = req.app.kraken.get('jwt:secret');
      jwt.verify(accessToken, jwtSecret, function(err, decoded) {
        if (err) {
          return next(new errors.ClientInvalidToken());
        }

        User.findOne({
          _id: decoded.id
        }, function(err, user) {
          if (err) {
            return next(new errors.MongoError(err.errmsg));
          }

          res.status.json({
            ok: true,
            medicalIds: user.medicalIds
          });
        });
      });
    })
    .post(function(req, res, next) {
      var accessToken = req.headers['x-access-token'];

      if (!accessToken) {
        return next(new errors.ClientUnauthorized());
      }

      var jwtSecret = req.app.kraken.get('jwt:secret');
      jwt.verify(accessToken, jwtSecret, function(err, decoded) {
        if (err) {
          return next(new errors.ClientInvalidToken(err.message));
        }

        var relation = req.body.relation;
        var birthday = req.body.birthday;
        var bloodType = req.body.bloodType;
        var height = req.body.height;
        var weight = req.body.weight;
        var medications = req.body.medications;
        var medicalConditions = req.body.medicalConditions;
        var allergies = req.body.allergies;
        var medicalNotes = req.body.medicalNotes;

        if (!relation || !birthday || !bloodType || !height || !weight ||
          !medications || !medicalConditions || !allergies || !medicalNotes) {
          return next(new errors.ClientBadRequest());
        }

        var candidateMedicalId = {
          relation: relation,
          bloodType: bloodType,
          height: height,
          weight: weight,
          medications: medications,
          medicalConditions: medicalConditions,
          allergies: allergies,
          medicalNotes: medicalNotes
        };

        User.findOneAndUpdate({
          _id: decoded.id
        }, {
          $push: {
            medicalIds: candidateMedicalId
          }
        }, function(err) {
          if (err) {
            return next(new errors.MongoError(err.errmsg));
          }

          res.status(201).json({
            ok: true,
            message: 'Ficha medica enviada correctamente'
          });
        });
      });
    });
};
