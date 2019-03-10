快嘉框架是一个面向变化的J2EE开发模型最小实践，它将一个传统的J2EE单体应用模块化，分成了包括war、批处理等在内的主应用和可被依赖的普通jar独立维护；这个模型利用maven构建，主应用通过spring集成它所依赖的所有jar后可以独立部署、启动，对外提供服务。 

**快嘉框架将需求迭代过程中最容易变化的数据库持久层面和控制器层面的代码作为模块独立出来，并通过集成快嘉代码生成插件生产代码；一般开发人员只需要维护针对数据库定义和接口分别维护sql文件和xml文件，就可以通过快嘉代码生成插件迅速获得这部分模块代码，可以在一定程度上减轻工作量。** 

快嘉框架将一个标准的J2EE项目划分4类模块如下:
- **交付模块**
	- task：批处理任务，依赖biz模块，目前集成的任务框架是quartz
	- provider：rpc和http服务，依赖biz模块，目前集成的rpc框架是dubbo，http框架是spring mvc，以war包形式发布
	- api：接口相关dto和rpc接口定义（根据接口定义文件生成）
- **业务模块**
	- base：基于mybatis注解使用方式的单表CRUD代码（根据sql文件生成）
	- bundle：接口相关dto、 rpc接口定义及实现、controller和依赖的service定义（根据接口定义文件生成）
	- biz.：业务模块，依赖base和bundle模块，除了bundle中service的实现会集中在这个模块，还包括一些没有定义在接口和任务层面的service及其实现；此外还包括一些工具类、与其他第三方的通信类、自定义的持久层访问类，错误码常量等
- **开发辅助模块**
	- bundle-mock：接口相关dto、rpc相关接口和实现、controller、依赖的service定义及service的简单mock，这里的dto、 controller 都是带swagger-ui标签的，此外，对于rpc相关接口，也提供了带swagger-ui标签的controller （根据接口定义文件生成）
	- provider-mock： rpc服务和http服务，依赖bundle-mock模块，目前集成的rpc框架是dubbo，http框架是spring mvc，以war包形式发布，可作为接口文档服务器和联调服务使用
- **测试模块**
	- test：单元测试模块、依赖biz、base和bundle，基于testng框架实现，
	- api-test： 接口测试模块，接口相关dto、 rpc相关接口定义及通信类（根据接口定义文件生成）

![](https://oscimg.oschina.net/oscnet/af2a7c724eaac506a025fd28bd320b62633.jpg)

该框架基于spring4+mybatis3.3.0搭建，持久层为mysql，示例demo完整实现了注册、登录和自动登录接口，具体业务逻辑可参考demo-biz模块。

具体业务逻辑可参考业务模块(demo-biz)代码实现。