### 知识点
* buildscript.dependencies
* groovy 基本语法
* 像java 一样使用类
* 像java 一样使用反射

### buildscript.dependencies
* 这里可以管理groovy 调用时的依赖，需要什么就加入什么
    + 本例中引入 h2
    + 本例中引入 commons-lang3
* 其实groovy 提供了例如SQL 之类的更方便groovy 调用的类，这里不展开

### groovy 基本语法
* 参考 https://www.w3cschool.cn/groovy/
* 可以像java 一样使用引入的包

```
import org.apache.commons.lang3.RandomStringUtils
println(RandomStringUtils.randomAlphabetic(16))
```
* 可以像java 一样使用反射，本例用反射获取了h2 的Driver 实现类，并使用它进行了 sql query