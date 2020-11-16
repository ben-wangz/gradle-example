### 知识点
* 基础语法
* 灵活的子模块组织结构，模块之间相对独立
* 模块之间的依赖

### 基础语法
* settings.gradle 内，依据路径添加子模块

```text
.
├── build.gradle
├── cpp_project
│   ├── build.gradle
│   └── src
├── java_project
│   ├── build.gradle
│   └── src
├── multi_java_project
│   ├── common
│   │   ├── build.gradle
│   │   └── src
│   ├── dist
│   │   ├── build.gradle
│   │   └── src
│   └── features
│       ├── build.gradle
│       └── src
```

* 根目录本身也是一个project
* 根目录的build.gradle 不用做任何特殊处理
* 每一个子模块都带有一个build.gradle 作为自身的配置
* settings.gradle 只存在于根目录
* gradle.properties 只存在于根目录

### 灵活的子模块组织结构
1. 多种语言的组合
    * 其实gradle 不关心你的语言
    * 例如本例就是由一个cpp project 和 2个 java project 组成
2. 平铺的子模块组织
    * 多个子模块，以目录结构平铺，然后在settings.gradle 内添加子模块即可
3. 嵌套的子模块组织
    * 一个子模块包含另外几个更深层次的子模块，按照目录结构组织并在settings.gradle内添加即可

### 模块之间相对独立
1. 每个模块其实只关心自己的配置，拥有独立的 build.gradle 文件

### 模块之间的依赖
1. 引用其他模块
    * 以multi_java_project 为例
    * common 作为基础模块，不依赖任何其他模块，build.gradle 的配置与正常java_project 没有区别
    * features 作为功能实现模块，依赖了common 内定义的接口等，需要加入common 作为依赖

    ```
    implementation(project(":multi_java_project:common"))
    ```
    * dist 作为门面模块，依赖feature，间接依赖common，因此，需要将两个模块的依赖都添加进去