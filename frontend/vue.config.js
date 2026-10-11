const { defineConfig } = require('@vue/cli-service')
const nodeExternals = require('webpack-node-externals');


module.exports = defineConfig({
  transpileDependencies: true,
  devServer: {
    host: 'localhost',
    port: 8081, // 端口
  }

})
module.exports = defineConfig({
  transpileDependencies: true,
  devServer: {
    host: 'localhost',
    port: 8081, // 端口
  },
  configureWebpack: config => {
    if (process.env.NODE_ENV === 'production') {
      // 在生产环境中排除所有 node_modules 模块
      config.externals = [nodeExternals()];
    }
  }
});
