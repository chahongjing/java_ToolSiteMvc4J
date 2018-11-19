'use strict'
const merge = require('webpack-merge')
const prodEnv = require('./prod.env')

module.exports = merge(prodEnv, {
  NODE_ENV: '"development"',
  baseHost:'"localhost"',
  basePort: '"8099"',
  context:'"/ToolSiteMvc4J"',

  targetHost:'"http://localhost"',
  targetPort:'"8088"',
  // targetPort:'"9999"',
  proxyPrefix: '"/api"'
})
