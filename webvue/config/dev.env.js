'use strict'
const merge = require('webpack-merge')
const prodEnv = require('./prod.env')

module.exports = merge(prodEnv, {
  NODE_ENV: '"development"',
  HOST: 'localhost',
  PORT: 8081,
  baseUrl:'"http://localhost:8099"'
})
