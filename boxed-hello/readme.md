### 知识点
* gradle init 产生的文件
* 指定gradle 包的来源
* 预先下载一个gradle 包
* 拿到一个自构建的project 如何执行

### gradle init 产生的文件
1. gradle/gradle-wrapper.jar
    * gradle wrapper 的依赖jar包，gradlew/gradlew.bat 会调用它完成基础操作
2. gradle/gradle-wrapper.properties
    * gradle wrapper 的属性定义，能够改变 gradle wrapper 的行为，例如变更实现版本
3. gradlew
    * gradle wrapper 为Linux/Unix 系统编写的入口脚本
4. gradlew.bat
    * gradle wrapper 为Windows 系统编写的入口脚本

### 指定gradle 包的来源
1. 在 gradle-wrapper.properties 文件里面可以指定来源（修改版本也是改这个值）
2. gradle init 命令生成gradle-wrapper.properties，可以手动修改
    * 默认值 distributionUrl=https\://services.gradle.org/distributions/gradle-5.2.1-bin.zip
    * 默认值是gradle init 命令生成的是gradle init 命令本身的版本
    * 支持http/https/ftp/file
    * file 支持相对路径
    * 这里改成 distributionUrl=../pkg/gradle-6.1.1-all.zip
    * 此相对路径对应的是 gradle/pkg/gradle-6.1.1-all.zip
    * 如果设置为file 需要预先准备好gradle 包（通过脚本wget_gradle_all_package.sh 来完成，见后文）

### 预先下载一个gradle 包
1. 通过 wget_gradle_all_package.sh 完成
2. 通过 $OSTYPE 变量来判断系统
3. 不同的系统使用不同的命令来下载 gradle 包
4. 通过 d1df7d94816bbedb2ab45187fceda7e5 码 来判断包的正确性
5. 使用setup_project.sh 来封装（如果有其他系统也可以在此封装内增加功能）

### 拿到一个自构建的project 如何执行
1. 拉去所有代码: git clone xxx
2. 初始化项目: bash setup_project.sh
    * 如果嫌下载慢，可以打开 gradle/wget_gradle_all_package.sh，拿到下载地址和目标路径
    * 手动下载该文件后，复制到目标路径
3. ./gradlew tasks --all
