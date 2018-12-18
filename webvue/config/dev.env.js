'use strict'
const merge = require('webpack-merge')
const prodEnv = require('./prod.env')

module.exports = merge(prodEnv, {
  NODE_ENV: '"development"',
  baseHost:'"localhost"',
  basePort: '"8099"',
  context:'"/ToolSiteMvc4J"',

  // targetHost:'"http://localhost"', targetPort:'"21000"',
  targetHost:'"http://10.4.132.60"', targetPort:'"20000"',
  // targetHost:'"http://localhost"', targetPort:'"20000"',
  proxyPrefix: '"/api"'
})
