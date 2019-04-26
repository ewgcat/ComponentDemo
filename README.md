#  组件化介绍使用

Android APP组件化架构的目标是告别结构臃肿，让各个业务变得相对独立，业务组件在组件模式下可以独立开发，而在集成模式下又可以变为arr包集成到“app壳工程”中，组成一个完整功能的APP； 
从组件化工程模型中可以看到，业务组件之间是独立的，没有关联的，这些业务组件在集成模式下是一个个library，被app壳工程所依赖，组成一个具有完整业务功能的APP应用，但是在组件开发模式下，业务组件又变成了一个个application，它们可以独立开发和调试，由于在组件开发模式下，业务组件们的代码量相比于完整的项目差了很远，因此在运行时可以显著减少编译时间。
这是组件化工程模型下的业务关系，业务之间将不再直接引用和依赖，而是通过“路由”这样一个中转站间接产生联系，但是整改后的架构能够带来很多好处：

1、加快业务迭代速度，各个业务模块组件更加独立，不再出现业务耦合情况； 
2、稳定的公共模块采用依赖库方式，提供给各个业务线使用，减少重复开发和维护工作量； 
3、迭代频繁的业务模块采用组件方式，各业务研发可以互不干扰、提升协作效率，并控制产品质量； 
4、为新业务随时集成提供了基础，所有业务可上可下，灵活多变； 
5、降低团队成员熟悉项目的成本，降低项目的维护难度； 
6、加快编译速度，提高开发效率； 
7、控制代码权限，将代码的权限细分到更小的粒度；

51cto：https://blog.51cto.com/13598859/2069719

