### 知识点
* 对于构建cpp 程序，最简单的build.gradle 配置如何写
* cpp 代码默认的目录结构
* cpp 代码编译产物默认的目录结构
* 拿到一个cpp_project 后如何开始

### 最简单的构建cpp 的 build.gradle
* 加两个插件: cpp-application 和 cpp-unit-test
* 构建不同类型的cpp 程序，可以使用不同的插件，右转官方文档，这里不展开
* application.targetMachines 需要指定操作系统类型
* 没了，就这么简单！

### cpp 代码默认的目录结构

```text
src
├── main
│   ├── cpp
│   │   └── app.cpp
│   └── headers
│       └── app.h
└── test
    └── cpp
        └── app_test.cpp
```

### cpp 代码编译产物默认的目录结构

```text
build
├── exe
│   ├── main
│   │   └── debug
│   │       └── macos
│   │           └── cpp_project
│   └── test
│       └── macos
│           └── cpp_projectTest
├── install
│   ├── main
│   │   └── debug
│   │       └── macos
│   │           ├── cpp_project
│   │           └── lib
│   │               └── cpp_project
│   └── test
│       └── macos
│           ├── cpp_projectTest
│           └── lib
│               └── cpp_projectTest
├── obj
│   ├── for-test
│   │   └── main
│   │       └── debug
│   │           └── macos
│   │               └── app.o
│   ├── main
│   │   └── debug
│   │       └── macos
│   │           └── c0rn65lkroj8p4fvz0qgmzzjr
│   │               └── app.o
│   └── test
│       └── macos
│           └── 9yxcrtbw8l8n123chso4js8ln
│               └── app_test.o
├── test-results
│   └── test
│       └── macos
└── tmp
    ├── compileDebugMacosCpp
    │   ├── options.txt
    │   └── output.txt
    ├── compileTestMacosCpp
    │   ├── options.txt
    │   └── output.txt
    ├── linkDebugMacos
    │   ├── options.txt
    │   └── output.txt
    └── linkTestMacos
        ├── options.txt
        └── output.txt
```

* 编译产出可执行文件: build/exe/main/debug/macos/cpp_project
    + 可直接运行 ./build/exe/main/debug/macos/cpp_project
* 编译产出单元测试文件: build/exe/test/macos/cpp_projectTest
    + 可直接运行 ./build/exe/test/macos/cpp_projectTest
    + 不过推荐还是用 ./gradlew test 来执行单元测试
* 拿到一个cpp_project 后如何开始
    + git clone xxx
    + 老样子，先 bash setup_project.sh
    + 查看所有任务: ./gradlew -q tasks --all
    + 编译: ./gradlew -q build
    + 单测: ./gradlew -q test
    + 找到build/ 目录内生成的可执行文件，并执行
