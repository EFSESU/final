const { defineConfig } = require('@vue/cli-service')
module.exports = {
  devServer: {
    port: 8081, // 明确指定前端开发服务器端口
    proxy: {
      '/api': {
        target: 'http://localhost:8080', // 确保指向Spring Boot端口（默认8080）
        changeOrigin: true,
       // pathRewrite: { '^/api': '' }
      }
    }
  }
}
