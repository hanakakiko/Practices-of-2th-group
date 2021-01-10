package HashGit;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

//Repository类，用于实现本地仓库的创建和文件的增删查改
public class Repository {
   private static String repositoryPath;
   private String gitPath;
   private File log;
   private File index;
   private Head head;
   
   //获得仓库路径
   public static String getRepositoryPath() {
	   return repositoryPath;
   }
   
   //根据给定目录创建仓库对象
   public Repository(String givenRepositoryPath) throws Exception {
	   repositoryPath=givenRepositoryPath;
	   this.gitPath=repositoryPath+"\\"+".git";
	   File gitRepo=new File(this.gitPath);
	   if(!gitRepo.exists()) {
		   gitRepo.mkdir();
		   //创建objects文件夹
		   GitObject.setObjectPath(gitPath+"\\"+"objects");
		   File objectDir=new File(gitPath+"\\"+"objects");
		   objectDir.mkdir();
		   //创建heads文件夹以存放branch文件
		   Branch.setBranchPath(gitPath+"\\"+"heads");
		   File branchDir=new File(gitPath+"\\"+"heads");
		   branchDir.mkdir();
		   //新建index文件
		   index=new File(this.gitPath,"index");
		   //生成第一次commit
		   File repo=new File(repositoryPath);
		   String temp="";
		   for(File f:repo.listFiles()){
			    if(f.getName().equals(".git")) continue;
	            if(f.isFile()){
	            	temp+=  "blob " + new Blob(repositoryPath+"\\"+f.getName()).getKey() + " " + f.getName()+"\n";
	            }
	            else if(f.isDirectory()){
	            	temp+=  "tree " + new Tree(repositoryPath+"\\"+f.getName()).getKey() + " " + f.getName()+"\n";
	            }
	        }
		    //将根目录下各文件的值写入index文件
	    	PrintWriter pw=new PrintWriter(index);
	    	pw.println(temp);
	    	pw.close();
            //根据index文件生成commit
	    	Tree tree=new Tree(index);
	    	Commit firstCommit=new Commit("",tree.getKey());
	    	//将commit记录写入log文件
	    	log=new File(this.gitPath+"\\"+"log");
	    	pw=new PrintWriter(log);
	    	pw.println(firstCommit.getKey());
	    	pw.close();
	    	//清空暂存区记录
	    	pw=new PrintWriter(index);
	    	pw.println("");
	    	pw.close();
	    	//生成main分支
	    	Branch main=new Branch("Main",firstCommit);
	    	//创建head对象并将main分支写入head文件
		    Head.setHeadPath(gitPath);
		    head=new Head(main);
	   }
   }
   
   //向index区添加文件
   public void addFile(String fileName) throws Exception {
	   File file=new File(repositoryPath,fileName);
	   if(!file.exists()) {
		   System.out.println("文件不存在！");
		   return;
	   }
	   String temp="";
	   Scanner input=new Scanner(index);
	   while(true) {
   		  String t=input.nextLine();
   		  if(t=="") break;
   		  temp+=t;
   	   }
	   input.close();
	   temp+="\n";
       if(file.isFile()){
       	temp+=  "blob " + new Blob(file).getKey() + " " + fileName+"\n";
       }
       else if(file.isDirectory()){
       	temp+=  "tree " + new Tree(repositoryPath+"\\"+fileName).getKey() + " " + fileName+"\n";
       }
       PrintWriter pw=new PrintWriter(index);
   	   pw.println(temp);
   	   pw.close();
   }
   
   //生成新的commit
   public void createCommit() throws Exception{
	   Tree tree=new Tree(index);
       Commit firstCommit=new Commit("",tree.getKey());
   	   //将commit记录写入log文件
       String temp="";
       Scanner input=new Scanner(index);
	   while(true) {
   		  String t=input.nextLine();
   		  if(t=="") break;
   		  temp+=t;
   	   }
	   input.close();
	   temp+="\n";
   	   PrintWriter pw=new PrintWriter(log);
   	   pw.println(temp+firstCommit.getKey());
   	   pw.close();
   	   //清空暂存区记录
   	   pw=new PrintWriter(index);
   	   pw.println("");
   	   pw.close();
   }
   
   //生成新的branch并切换到最新分支
   public void createBranch(String branchName) throws Exception{
	   Branch branch=new Branch(branchName);
   	   //将新分支写入head文件
	   head.setHead(branch);
   }
   
   //更改分支
   public void setBranch(String branchName)throws Exception {
	   Branch branch=new Branch(branchName,head.getBranch().getCommit());
   	   //将新分支写入head文件
	   head.setHead(branch);
   }
   
   //查看log文件
   public void viewLog() throws FileNotFoundException {
	   Scanner input=new Scanner(log);
	   while(true) {
		   String temp=input.nextLine();
		   if(temp.equals("")) break;
		   System.out.println(temp);
	   }
	   input.close();
   }
}
