'use strict';

var bcrypt = require('bcrypt-nodejs');
var mongoose = require('mongoose');

/* CONSTANTS */
var SALT_FACTOR = 10;

var UserModel = function() {
  var userSchema = mongoose.Schema({
    name: String,
    email: {
      type: String,
      lowercase: true,
      trim: true,
      unique: true
    },
    phone: String,
    password: String,
    medicalIds: [
      {
        relation: String,
        birthday: Date,
        bloodType: {
          type: String,
          enum: ['a+', 'a-', 'b+', 'b-', 'ab+', 'ab-', 'o-', 'o+']
        },
        height: Number,
        weight: Number,
        medications: String,
        medicalConditions: String,
        allergies: String,
        medicalNotes: String
      }
    ],
    medicalHistory: [
      {
        diagnostic: String,
        medication: String,
        _occurredAt: {
          type: Date,
          default: Date.now
        }
      }
    ]
  });

  userSchema.pre('save', function(next) {
    var user = this;

    if (!user.isModified('password')) {
      return next();
    }

    bcrypt.genSalt(SALT_FACTOR, function(err, salt) {
      if (err) {
        return next(err);
      }

      bcrypt.hash(user.password, salt, null, function(err, hash) {
        if (err) {
          return next(err);
        }

        user.password = hash;
        next();
      });
    });
  });

  userSchema.methods.passwordMatches = function(candidatePassword) {
    return bcrypt.compareSync(candidatePassword, this.password);
  };

  var user = mongoose.model('User', userSchema);

  return user;
};

module.exports = new UserModel();
