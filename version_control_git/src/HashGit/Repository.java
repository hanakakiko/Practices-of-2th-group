package HashGit;

import java.io.File;
import java.io.IOException;

//Repository类，用于实现本地仓库的创建和文件的增删查改
public class Repository {
   private String repositoryName;
   private String repositoryPath;
   private Head head;
   
   //根据给定目录创建仓库对象
   public Repository(String name,String path) {
	   repositoryName=name;
	   repositoryPath=path+"/"+repositoryName;
	   KeyValueStore.objectPath=repositoryPath+"/.git"+"/objects";
	   head=new Head();
   }
   
   //给定目录为空时，根据给定目录创建空仓库
   public void createRepository() throws IOException {
	   //在指定位置创建目录
	   File repoDir=new File(repositoryPath);
	   repoDir.mkdir();
	   //在目录下创建.git文件夹，用于存放object、head等文件
	   File gitDir=new File(repositoryPath+"/.git");
	   gitDir.mkdir();
	   //创建objects文件夹以存放GitObject文件
	   File objectsDir=new File(repositoryPath+"/.git"+"/objects");
	   objectsDir.mkdir();
	   //创建heads文件夹以存放branch
	   File headsDir=new File(repositoryPath+"/.git"+"/heads");
	   headsDir.mkdir();
	   //设置src文件夹以存放本地仓库文件
	   File srcDir=new File(repositoryPath+"/src");
	   srcDir.mkdir();
	   //生成log文件
	   File log=new File(repositoryPath+"/.git/"+"log");
	   log.createNewFile();
   }
   
   //生成第一次Commit
   public void initCommit() throws Exception {
	   Tree tree=new Tree(repositoryPath+"/src");
	   Commit commit=new Commit(tree);
	   //创建main分支并储存当前信息
	   Branch main=new Branch(repositoryPath+"/.git"+"/heads","main");
	   main.createBranch(repositoryPath+"/.git"+"/heads", commit.getKey());
	   //创建head对象并写入当前分支信息
	   head.setHead(repositoryPath+"/.git"+"/heads",repositoryPath+"/HEAD",main.getBranchName());
   }
   
   //添加文件
   public void addFile(String fileName) throws IOException {
	   File file=new File(repositoryPath+"/src",fileName);
	   file.createNewFile();
   }
   
   //删除文件
   public void deleteFile() {}
   
   //更改文件(需要用到Myers diff算法，现在不要求)
   public void modifyFile() {}
   
   //生成新的commit
   public void createCommit() throws Exception{
	   
   }
}
