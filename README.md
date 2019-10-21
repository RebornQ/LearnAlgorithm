# LearnAlgorithm
数据结构/算法学习

## 编译运行（仅适用于Linux/Mac）

### 编译
本项目采用`AspectJ`实现`AOP`，用于计算方法执行时间；同时也改用`slf4j`打印日志。

使用前请下载相关依赖并放到项目根目录的`lib`文件夹中导入，下面是`lib`目录结构

```
├── lib
│   ├── asm-7.1-sources.jar
│   ├── asm-7.1.jar
│   ├── aspectjrt.jar
│   ├── aspectjtools.jar
│   ├── aspectjweaver.jar
│   ├── fastjson-1.2.62-sources.jar
│   ├── fastjson-1.2.62.jar
│   ├── log4j-api-2.12.1-sources.jar
│   ├── log4j-api-2.12.1.jar
│   ├── log4j-core-2.12.1-sources.jar
│   ├── log4j-core-2.12.1.jar
│   ├── log4j-slf4j-impl-2.12.1-sources.jar
│   ├── log4j-slf4j-impl-2.12.1.jar
│   ├── slf4j-api-1.7.9-sources.jar
│   └── slf4j-api-1.7.9.jar
```

再用`ajc`命令编译后运行，`ajc`脚本已集成在项目的`bin`文件夹中。若要使用自己下载的版本，请注意环境变量，可在脚本开头加上环境变量：

```shell script
ASPECTJ_HOME=$PWD
JAVA_HOME=$(/usr/libexec/java_home)

# ...原脚本代码...
```

命令如下示例：

```shell script
$ProjectFileDir$/bin/ajc -encoding UTF-8 -classpath $Classpath$ -d $OutputPath$ -sourceroots $Sourcepath$ -8
```

### 运行
由于使用了`slf4j`，因此运行的时候需要参数指定其配置文件，如：

```shell script
java -Dlog4j.configurationFile=log4j.xml ...
```

`log4j.xml`需要在`src`目录下一起编译，否则此处需要填完整路径

若是使用IDEA，可直接在`Edit Configuration`设置`VM Options`为`-Dlog4j.configurationFile=log4j.xml`即可。

## 目录
### 数据结构
- [栈的定义、实现和应用等](https://github.com/RebornQ/LearnAlgorithm/tree/master/src/datastructure/_2_stack)
- [队列的定义、实现和应用等](https://github.com/RebornQ/LearnAlgorithm/tree/master/src/datastructure/_3_queue)