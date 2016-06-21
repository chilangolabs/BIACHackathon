'use strict';

var errors = require('errors');

errors.create({
  name: 'ClientError',
  defaultMessage: 'Acción invalida, por favor intenta de nuevo',
  scope: exports
});

errors.create({
  name: 'ClientBadRequest',
  defaultMessage: 'Algún parámetro está faltando',
  parent: exports.ClientError,
  status: 400,
  scope: exports
});

errors.create({
  name: 'ClientUnauthorized',
  defaultMessage: 'Necesitas autenticación, por favor intenta de nuevo',
  parent: exports.ClientError,
  status: 401,
  scope: exports
});

errors.create({
  name: 'ClientForbidden',
  defaultMessage: 'No autorizado',
  parent: exports.ClientError,
  status: 403,
  scope: exports
});

errors.create({
  name: 'ClientInvalidToken',
  defaultMessage: 'Su token no es válido, por favor intenta de nuevo',
  parent: exports.ClientError,
  scope: exports
});

errors.create({
  name: 'ServerError',
  defaultMessage: 'Ocurrió un error, por favor intenta de nuevo',
  defaultExplanation: 'Error at the server',
  scope: exports
});

errors.create({
  name: 'MongoError',
  defaultMessage: 'Ocurrió un error, por favor intenta de nuevo',
  defaultExplanation: 'An error ocurred at DB',
  parent: exports.ServerError,
  scope: exports
});
