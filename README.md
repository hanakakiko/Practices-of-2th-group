# version_control_git

这是java课程第2小组的以git原理实现版本控制工具的项目实践

## 功能介绍

**目前实现的功能有：**

Blob、Tree和Commit对象的生成以及本地存储，分支的创建、查看、切换、回滚（包括soft、mixed、hard三种形式），暂存区（index）的操作，文件的添加功能，提交功能，log文件的记录和查看，命令行控制。

## 设计亮点

实现了暂存区（index文件）的管理，可根据index内容查看文件修改情况，并能根据index文件的内容生成新的commit。

## 原理简介

Git仓库实际上是一个key-value存储/对象存储（类似HashSet），在其上用一些操作命令实现版本控制。

- #### **objects**

一个value即一个对象（object）

value是文件内容，key是这段内容的哈希值

给定一个key值，能够得到一个value

给定一个value，能够判断是否存在这个value，若不存在，可以插入新的value

Git有三种类型的object：

1. Blob
2. Tree
3. Commit

- #### **command**s

用add提交要生成objects的文件（生成blob）或文件夹（生成tree），并修改暂存区（index），用commit生成新的根目录tree和commit文件。

用户可以创建多个branch，在其之间切换，合并或删除，head永远指向当前分支（暂不考虑指向某一次历史commit的功能），当前分支永远指向最新一次commit；使用log命令可以查看当前分支的全部历史commit；如果想要回退到之前的commit版本，需要用reset命令。而reset命令可分为soft、mixed、hard三种，后两者分别将暂存区、暂存区和工作区恢复到最近一次commit的情形。

Git有六种类型的command：

1. init
2. add
3. commit
4. branch(包含checkout)
5. log
6. reset

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

分支的创建，切换（checkout），删除

### add命令

提交工作区的修改到暂存区

### commit命令

提交暂存区的修改到仓库

### log命令

查看历史commit版本

### reset命令

reset命令可分为soft、mixed、hard三种，后两者分别将暂存区、暂存区和工作区恢复到最近一次commit的情形。

## 代码解释

### 项目类图

![image-20210111004742774](C:\Users\huashizi\Desktop\README.assets\image-20210111004742774.png)

### **SHA1CheckSum类**

用于根据文件内容或字符串内容生成相应的哈希值

**数据域：**

**Sha1**：用于保存SHA1CheckSum对象的哈希值，其类型为String

**方法：**

**SHA1CheckSum**（File）：通过传入的文件对象初始化SHA1CheckSum对象

**SHA1CheckSum**（String）：通过传入的字符串初始化SHA1CheckSum对象

**Sha1CheckSum**（InputStream）：void方法，根据传入的根据文件生成的InputStream流生成对应的SHA-1哈希值，并为对象属性Sha1赋值

**Sha1CheckSum**（String）：void方法，根据传入的字符串生成对应的SHA-1哈希值，并为对象属性Sha1赋值

**getSha1**（）：获取对象的SHA-1哈希值，其返回值类型为String

### **GitObject类**

抽象类，是Blob、Tree、Commit三个类的父类

**数据域：**

**objectPath**：用于存放对应本地Git仓库中GitObject文件的路径，其类型为String

**sourcePath**：GitObject对象所对应的源文件所在路径，其类型为String

**sourceFile**：GitObject对象所对应的源文件对象，其类型为File

**type**：GitObject对象类型，包括Blob、Tree、Commit三种，其类型为String

**value**：用于存放GitObject对象对应的value值，其类型为String

**key**：用于存放GitObject对象对应的key（SHA-1哈希值），其类型为String

**方法：**

**setObjectPath**（）：void方法，用于修改GitObject对象对应文件存储路径

**getObjectPath**（）：返回GitObject对应文件存放路径，返回值类型为String

**genKey**（）：void方法，用于生成GitObject对象的key

**getKey**（）：访问器方法，返回GitObject对象的key，返回值类型为String

**writeWithChars**（）：void方法，将GitObject对象的value内容写入本地Git仓库，且文件名为GitObject对象的key

**writeWithFile**（）：void方法，将GitObject对象对应源文件的内容写入本地Git仓库，且文件名为GitObject对象的key

### **Blob类**

是GitObject类的子类，用于表示仓库中的文件

**方法：**

**Blob**（File）：根据传入的文件对象初始化Blob对象，同时写入Git仓库

**Blob**（String）：根据传入的文件路径初始化Blob对象，同时写入Git仓库

**genValue**（File）：void方法，根据传入的文件对象生成Blob对象的value

**genValue**（String）：void方法，根据传入的String对象生成Blob对象的value

### **Tree类**

是GitObject类的子类，用于表示仓库中的文件夹

Tree（File）：根据暂存区index的内容初始化Tree对象，同时写入Git仓库

Tree（String）：根据传入的文件路径初始化Tree对象，同时写入Git仓库

genValue（）：void方法，根据Tree对象对应文件目录下的各个文件文件生成相应的value

### **Commit类**

GitObject的子类，代表一次对Git仓库的提交

**数据域：**

**parentKey**：用于存储上一次commit的key值，其类型为String，可以为空

**treeKey**：本次提交对应根目录的Tree对象的key值，其类型为String

方法：

**Commit**（String，String）：根据传入的上一次commit对象key值以及本次commit对象对应根目录key值初始化Commit对象，同时写入Git仓库

### **Branch类**

用于管理分支，进行本地Branch文件写入和修改、回滚等操作

**数据域：**

**branchPath**：用于存放当前分支对应本地Git仓库branch文件存储路径，其类型为String

**branchFile**：Branch对象对应本地Git仓库中存放的branch文件，其类型为File

**branchName**：当前分支名称，其类型为String

**currentCommit**：该分支对象当前指向的Commit对象，其类型为Commit

**currentCommitKey**：该分支对象档当前指向Commit对象的key值，其类型为String

方法：

**Branch**（String）：根据传入的分支名称初始化Branch对象

**Branch**（String，Commit）：根据传入的分支名称和当前指向commit初始化Branch对象，并写入本地Git仓库

**setBranchPath**（String）：设置本地branch文件存放路径

**getBranchName**（）：访问器方法，返回当前分支名称，其返回值类型为String

**getCommit**（）：访问器方法，返回当前分支指向的Commit对象，其返回值类型为Commit

**softReset**（）：void方法，使得该分支指向当前commit对象对应的上一次commit对象

**mixedReset**（）：void方法，使得该分支指向当前commit对象对应的上一次commit对象的同时，将暂存区index中的内容还原到上一次commit提交时的状态

**hardReset**（）：void方法，在实现mixedReset（）方法全部功能的同时，将git仓库中的文件还原到本地工作区，即将本地工作区中的文件还原到上一次commit提交时的状态

**resetBlob**（）：将Git仓库中的blob文件还原到本地工作区

**resetTree**（）：根据Git仓库中的Tree文件内容，在本地工作区创建相应目录并将Tree文件中存放的Blob或Tree文件递归地还原到本地工作区

### **Head类**

1）  用于管理当前分支，进行分支切换等操作

**数据域：**

**headPath**：用于存放head文件路径，其类型为String

**currentBranch**：head对象当前指向的branch对象，其类型为Branch

**head**：head对象对应的本地文件，其类型为File

**方法：**

**Head**（Branch）：根据传入的Branch对象初始化Head对象，使得Head对象指向该Branch对象并写入Git仓库

**getBranch**（）：返回head对象当前指向分支，其返回值为Branch

**setHead**（Branch）：更改Head对象指向的Branch对象并更改Git仓库中对应HEAD文件中的内容

### **Repository类**

用于进行工作区、暂存区和Git仓库的文件管理，从而实现文件更改、commit提交、暂存区管理等功能

数据域：

**repositoryPath**：用于存放本地工作区路径，其类型为String

**gitPath**：用于存放本地Git仓库路径，其类型为String

**head**：仓库对应Head对象，其类型为Head

**index**：暂存区文件，其类型为File

**log**：用于记录commit提交记录，其类型为File

方法：

**Repository**（String）：根据给定的仓库路径初始化Repository对象，设置工作区路径、Git仓库路径、object文件存放路径、branch文件存放路径等，同时根据仓库中现有文件或文件夹递归地生成对应的tree和blob文件并存入Git仓库，进行第一次commit提交后，根据初次commit初始化main分支对象和Head对象，并写入本地文件

**addFile**（String）：void方法，根据给定的文件名称，在工作区目录下搜寻指定的文件，生成对应GitObject文件并存入Git仓库后，将GitObject类型、key值和文件名称写入暂存区index文件中

**createBranch**（String）：void方法，根据给定的分支名称，创建新分支，写入本地文件并使Head对象指向新分支

**createCommit**（）：void方法，根据暂存区内容生成根目录对应的tree对象，并根据该对象的key值和当前Commit对象的key值创建新的Commit对象，同时使当前分支指向新生成的Commit对象

**setBranch**（String）：void方法，切换分支，更改Head对象指向分支并写入本地文件

**viewLog**（）：查看commit提交历史

### **Commandgit类：**

用于实现命令行控制

**方法：**

**Commandgit**（File,String,String）：根据指定文件和指令字符串初始化Command对象

**main**（String[]）：主函数

**Command**（String[]）:根据获得指令的内容调用Repository类的不同功能

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
对代码内容进一步进行了优化，取消了firstCommit（生成初次commit）等不必要的功能，同时增加了暂存区，优化了add及生成新commit的功能。

增加了Commandgit类、Test类和CommandGit包:

Commandgit类接收并解析命令行。

Test类用来生成初始test文件夹，其中放入一些测试用的文件，来对每个类的主要方法进行测试。

CommandGit包中含有六个类：init、add、commit、branch、log、reset，分别作为Commandgit类接收并解析命令行后调用的方法所保存的类。