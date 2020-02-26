# java_ToolSiteMvc4J
- 前端使用bootstrap，vue
- 后端使用spring，springMVC，redis，shiro多realm加盐验证
- 统一枚举方案
- 统一日志方案（logback），包括异常，正常处理完成，日志记录到数据库
- 基于角色管理的权限方案
- 下载方案
- 国际化
- 统一日期处理方案

# 其它
### 消息模块设计
添加用户消息表，系统消息表，系统消息表为批量投送，如哪一类型的消息，哪一类型的人接收，当那一类型的人登录系统后，从用户消息表中查询是否包含系统表中的消息，如果不包含，则说明还未收到消息，则把消息插入到用户消息表，然后再从用户消息表中查询用户的所有消息。

### 待办列表
- [x] 已完成的项
- [ ] 操作日志添加主键logid

# 创建vue项目
~~~ cmd
npm install --global vue-cli
# vue3.0
npm install -g @vue/cli
# vue init webpack my-project
cd my-project
npm install
npm run dev
~~~
~~~ cmd
# 安装store
npm install vuex --save
# 安装axios
npm install axios --save
# toastr
npm install vue-toastr --save
# 安装jsonp模块
npm install vue-jsonp --save

# 删除模块
npm uninstall xx-abc：删除模块，但不删除模块留在package.json中的对应信息
# 如果是安装在 dependencies
npm uninstall xx-abc --save 删除模块，同时删除模块留在package.json中dependencies下的对应信息
# 如果是安装在 devDependencies
npm uninstall xx-abc --save-dev 删除模块，同时删除模块留在package.json中devDependencies下的对应信息
~~~