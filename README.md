### 习惯于maven 的java

* 对于java 工程师来说，maven 深入人心，有些细节，知道很烦，但是还得用
* springboot 本身的编译从今年6月份开始迁移到gradle: https://spring.io/blog/2020/06/08/migrating-spring-boot-s-build-to-gradle
    + 减少了整个项目的build 时间: "an hour or more" => "9 minutes 22 seconds"
* 其他
    + "task graph" vs "fixed and linear model of phases"
    + incremental build
    + groovy: clean and as easy as java
    + ...
* 起步: [hello/](hello)
* 开箱即用: [boxed-hello/](boxed-hello)
* gradle是支持其他语言的，比如 cpp_project: [cpp_project/](cpp_project)
* 多层次目录结构，gradle 随便玩: [multi/](multi)
* build.gradle 使用groovy, groovy 与java 一样简单: [groovy/](groovy)
* [integration/](integration) 一个可以直接抄的 web 例子: 前后端代码统一在一个仓库内，一起打包; 与docker 集成，测试与生产行为一致

