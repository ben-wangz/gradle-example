### 知识点
* gradle 基本文件
* 拿到一个gradle java 的项目，我应该如何执行
* 默认的java 代码组织结构
* gradle 本身的依赖
* java 编译相关的依赖
* 一个task 是什么

### gradle 基本文件
1. build.gradle
    * 核心文件，定义了gradle 编译需要的几乎所有东西
    * 用idea 打开的时候也只需要选中它，open as project 即可
    * 使用groovy 语言
2. gradle.properties
    * 定义了一些属性，可以在build.gradle 文件内引用
    * 初学者可以直接将其当做变量定义
    * 属性的覆盖逻辑前右转官方文档
3. settings.gradle
    * 定义了 项目名称
    * 也可以用于定义子项目的信息
4. 其他套件 gradle wrapper
    * 如果你执行了 gradle init，你会发现gradle 会自动为你生成一些文件
    * gradlew 与 gradlew.bat 是gradle 执行器的wrapper
    * gradle/wrapper/gradle-wrapper.jar 是 gradle wrapper 的依赖jar包
    * gradle/wrapper/gradle-wrapper.properties 定义了gradle 具体实现版本包
    * 有了gradle wrapper 你就可以不用预装gradle, 直接执行./gradlew (或./gradlew.bat) 来代替gradle 命令
    * gradle wrapper 机制的主要作用
        + 兼容不同版本gradle （在充分保障gradle 版本演进的同时，不影响用户使用）
        + 将gradle wrapper 提交到git，就能构造一个"自编译"的代码库
### 拿到一个gradle java 的项目，我应该如何执行
1. 查看是否有gradle wrapper
    * 有的话，直接使用./gradlew（或 ./gradlew.bat) 命令
    * 没有的话，需要预装一个gradle，使用 gradle 命令
        + 也可以执行一下 gradle init 来生成 gradle wrapper
2. 查看tasks
    * 执行如下命令可以看到这个项目中定义的task

    ```shell
    ./gradlew tasks --all
    ```
3. 执行tasks
    * 执行如下命令可以将一个task 执行

    ```shell
    ./gradlew sayHelloWithJar
    ```

### 默认的java 代码组织结构
* 其实与我们平时接触到的标准结构一样(eclipse 那种"标准" 就算了……)

```text
src
├── main
│   ├── java
│   │   └── cn
│   │       └── example
│   │           └── hello
│   │               └── App.java
│   └── resource
│       └── resource.txt
└── test
    └── resource
        └── test-resource.txt
```

### build.gradle 文件释义
* 这里也将同时解答 "gradle 本身的依赖", "java 编译相关的依赖" 的问题
* buildscript 模块，通常位于 plugins 模块之前，且位于顶部
    + build.gradle 本身使用groovy 语言来描述的，因此，他也需要一些jar 包依赖来支持
    + buildscript 模块就是用来定义这些依赖的
    + 本例只展示了如何修改仓库地址
        * buildscript 内定义repositories 模块即可
        * gradle 可以直接使用maven 仓库，本例使用了阿里云的maven 仓库
* plugins
    + 简单的 "id java" 就描述了这个project 将使用"java" 这个插件来编译
    + 添加一个插件的效果是，添加了许多task 到build.gradle 文件中
    + 插件的依赖包将从 buildscript 定义的仓库内下载
* ext
    + ext 模块内，我们可以定义一些变量值
    + 本例中，我们将 buildscript.repositories 赋值给了 aliyun_repositories
* version
    + version 是gradle project 预定义的值
    + 将其覆盖为 project.main_version
    + project.main_version 来自于build.properties 文件的定义
* task sayHello
    + task sayHello 是我自己定义的一个行为
    + 单纯为了测试
* def
    + 我们可以用 def 来定义一些变量
    + 当然，你也可以使用java 的语法样式: String jarName = ...
    + 作用域请自行探索
* jar
    + jar 其实是 "java" 这个 plugin 加入的一个task
    + 这里我们将其重新修饰
    + 使用 manifest 定义了main class
    + 使用 setArchivesBaseName 改变了生成的jar 包的名字
    + 使用 from 来搜集所有的代码文件
* task sayHelloWithJar
    + task sayHelloWithJar 也是我们定义的一个task
    + 这个task 使用了exec 执行了一条命令
    + 这条命令定位到了生成的jar 包，并使用这个jar 包来执行
* repositories
    + 与 buildscript 内的 repositories 类似
    + 都是仓库地址的定义
    + 不同的是 buildscript.repositories 用于定义gradle 的依赖，而 repositories 用于定义代码的依赖
* allprojects
    + 使用 allprojects 可以给所有的子项目(也包括根项目) 定义一些公共的属性

### 一个task 是什么
* task 是构建工作的基本单位
* 一个task 可以去完成一段编译、生成javadoc 和打包等工作
* 一个task 由groovy 语言描述，如果做得足够好，可以用它完成一切可能的事情