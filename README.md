快嘉框架是一个面向变化的J2EE开发模型最小实践，它将一个传统的J2EE单体应用模块化，分成了包括war、批处理等在内的主应用和可被依赖的普通jar独立维护；这个模型利用maven构建，主应用通过spring集成它所依赖的所有jar后可以独立部署、启动，对外提供服务。 

 **快嘉框架将需求迭代过程中最容易变化的数据库持久层面和控制器层面的代码作为模块独立出来，并使用快嘉网平台提供代码生成服务；一般开发人员只需要维护针对数据库定义和接口分别维护pdm文件和xml文件，就可以通过快嘉网平台提供的服务迅速获得这部分模块代码，可以在一定程度上减轻工作量。** 

快嘉框架将一个标准的J2EE项目拆分为通用组件(Common)、工具组件(Util)、基础组件(Base)、持久层组件(Persistence-Impl)、接口和业务定义组件(Bundle)、业务实现组件(Service-Impl)、批处理系统（Batch）、接口系统(Rest/Main)、Web系统(Web/Main)和自动化接口测试组件(RestTest)10个部分并行开发建设，其中接口系统(Rest/Main)和Web系统(Web/Main)又将环境配置信息(Rest/Config)和(Web/Config)作为独立的maven项目单独维护。

该框架基于spring4.2.4+mybatis3.2.7搭建，集成了mysql、rabbitmq、activemq、redis等api和相关配置信息，示例接口系统(Rest/Main)完整实现了注册、登录和自动登录接口，示例Web系统(Web/Main)提供了一个manage.html，主要提供了对用户表的分页、列表、增、删、改、查等基本功能实现。

具体业务逻辑可参考代码实现。