# version_control_git

这是java课程第2小组的以git原理实现版本控制工具的项目实践

## 内容介绍

Git仓库实际上是一个key-value存储/对象存储（类似HashSet），在其上用一些操作命令实现版本控制。

- **objects**

一个value即一个对象（object）

value是文件内容，key是这段内容的哈希值

给定一个key值，能够得到一个value

给定一个value，能够判断是否存在这个value，若不存在，可以插入新的value

Git有三种类型的object：

1. Blob
2. Tree
3. Commit

- **command**

用add提交要生成objects的文件（生成blob）或文件夹（生成tree），并修改暂存区（index），用commit生成新的根目录tree和commit文件。

用户可以创建多个branch，在其之间切换，合并或删除，head永远指向当前分支（暂不考虑指向某一次历史commit的功能），当前分支永远指向最新一次commit；使用log命令可以查看当前分支的全部历史commit；如果想要回退到之前的commit版本，需要用reset命令。而reset命令可分为soft、mixed、hard三种，后两者分别将暂存区、暂存区和工作区恢复到最近一次commit的情形。

Git有五种类型的command：

1. init
2. Commit
3. Branch
4. log
5. reset

### Blob对象

每个用户文件的每个版本都会对应一个blob对象

Blob对象的key是用户文件的hash值

Blob对象的value是文件内容

Blob对象没有文件名信息

### Tree对象

每个用户文件夹（和子文件夹）都会对应一个Tree对象

Tree对象的value包含：

1. 此文件夹内包含的文件名，以及这些文件对应的Blob key
2. 此文件夹内包含的子文件夹名，以及这些子文件夹对应的Tree key

### Commit对象

每次提交对应一个Commit对象

Commit对象的value包含：

1. 项目根目录对应的Tree对象的key
2. 前驱Commit对象的key
3. 代码author
4. 代码commiter
5. commit时间戳
6. commit备注/注释

### init命令

初始化仓库，添加.git，默认branch：master，默认HEAD内容：refs/heads/master

### branch命令

分支的创建，切换，删除

### commit命令

提交修改

### log命令

查看历史版本

### reset命令

reset命令可分为soft、mixed、hard三种，后两者分别将暂存区、暂存区和工作区恢复到最近一次commit的情形。

## 代码解释

（可以对自己的代码思路，每个类，每个函数进行详细诠释）

## 更新日志

### 2020-12-09

添加了src文件夹（包括GitObject.java、Blob.java、Tree.java、SHA1CheckSum.java四个文件）
实现了key-value存储（任务一）和根据给定目录生成对应的Tree、Blob对象（任务二）

### 2020-12-15

添加了根据key寻找指定路径下的value

### 2020-12-16

重新调整了项目结构，项目重命名为version_control_git；添加了Commit、Head、Branch类

### 2021-1-6
对原有代码内容进行了优化（包括调整Object、Object、Head文件存储路径等）；
添加了Repository类和Command类，其中Repository类用于实现对工作区和Git仓库区文件的统一管理，Command类用于实现命令行交互

### 2021-1-10
对代码内容进一步进行了优化，取消了firstCommit（生成初次commit）等不必要的功能，同时增加了暂存区，优化了add及生成新commit的功能
上次添加Commit、Head、Branch类忘修改readme的内容介绍部分，现在补上
