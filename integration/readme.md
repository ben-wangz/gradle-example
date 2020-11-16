### 知识点
* 项目模块简介
* 如何与docker 集成
* 前后端代码统一在一个仓库内
* 与docker 集成构建统一的环境
* 小技巧

### 项目模块简介
* 前端模块 frontend
    + 采用vue 基本框架
    + 使用node 进行编译，在build.gradle 中直接引用插件 com.github.node-gradle.node
    + 直接执行 yarnBuild 任务 即可执行编译
* 后端模块 backend
    + 后端子模块 common
        * 本例中只定义了一个 Configurable 接口 供其他模块引用
        * build.gradle 内是最简单的设置
    + 后端子模块 web
        * 本例中使用springboot 启动了一个简单的web 服务
        * 引用了 common 模块
        * build.gradle 内使用了springboot的插件
        * build.gradle 内定义了一个任务 runWithJar, 这个任务依赖了 springboot 插件内自带的打包任务 bootJar
        * 可以直接执行 runWithJar 来本地编译并启动 springboot 的 web 服务
        * 任务输出会打印出一个链接, 用 Chrome 打开可直接访问, 例如 http://localhost:8080/greeting?username=ObIBAymS
* 集成模块 docker
    + base 目录下是创建基础镜像的 dockerfile
    + web 目录下是创建 web 服务镜像的 dockerfile (包括配置文件)
    + 如何调用docker 命令: 借助 gradle 内置的 exec 起一个 docker 进程
    + build.gradle 内 用 groovy function 分别定义了 docker container 的存在检测、启动和停止
    + buildBaseImage 调用 docker 命令打包了基础镜像
    + copyFrontend 依赖了 frontend 这个project 的 yarnBuild 任务，并把打包好的前端文件复制到 build/ 目录内
    + copyBackend 依赖了 backend:web 这个project 的 bootJar 任务，并把打包好的后端文件复制到 build/ 目录内
    + buildWebImage 调用 docker 命令打包 web 镜像
    + runWebDocker 调用 docker 命令启动 web 服务容器
    + stopWebDocker 调用 docker 命令停止 web 服务容器
    + restartWebDocker 调用 docker 命令重启 web 服务容器

### 如何与docker 集成
    + 借助 gradle 内置的 exec 起一个 docker 进程
    + exec 内的参数: commandLine, args, ignoreExitValue (可以设置进程失败的行为)
    + 利用 ``` execResult.getExitValue() ``` 可以获得这个进程的返回值

### 前后端代码统一在一个仓库内
* 可以引用共同代码文件，维护一致性
* 可同时编译，打包，随时集成

### 与docker 集成构建统一的环境
* 本地测试与生产环境相同: 理论上, 任何拿到代码的人都能复现所有现象
* 一键打包
* 一键集成
* 一键启动

### 小技巧
* only if
* 使用参数调用task

```shell
./gradlew --quiet :docker:restartWebDocker -Prebuild=true
./gradlew --quiet :docker:restartWebDocker -Prebuild=false
./gradlew --quiet :docker:restartWebDocker
```
