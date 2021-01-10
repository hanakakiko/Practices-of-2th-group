package HashGit;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

//Repository�࣬����ʵ�ֱ��زֿ�Ĵ������ļ�����ɾ���
public class Repository {
   private static String repositoryPath;
   private String gitPath;
   private File log;
   private File index;
   private Head head;
   
   //��òֿ�·��
   public static String getRepositoryPath() {
	   return repositoryPath;
   }
   
   //���ݸ���Ŀ¼�����ֿ����
   public Repository(String givenRepositoryPath) throws Exception {
	   repositoryPath=givenRepositoryPath;
	   this.gitPath=repositoryPath+"\\"+".git";
	   File gitRepo=new File(this.gitPath);
	   if(!gitRepo.exists()) {
		   gitRepo.mkdir();
		   //����objects�ļ���
		   GitObject.setObjectPath(gitPath+"\\"+"objects");
		   File objectDir=new File(gitPath+"\\"+"objects");
		   objectDir.mkdir();
		   //����heads�ļ����Դ��branch�ļ�
		   Branch.setBranchPath(gitPath+"\\"+"heads");
		   File branchDir=new File(gitPath+"\\"+"heads");
		   branchDir.mkdir();
		   //�½�index�ļ�
		   index=new File(this.gitPath,"index");
		   //���ɵ�һ��commit
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
		    //����Ŀ¼�¸��ļ���ֵд��index�ļ�
	    	PrintWriter pw=new PrintWriter(index);
	    	pw.println(temp);
	    	pw.close();
            //����index�ļ�����commit
	    	Tree tree=new Tree(index);
	    	Commit firstCommit=new Commit("",tree.getKey());
	    	//��commit��¼д��log�ļ�
	    	log=new File(this.gitPath+"\\"+"log");
	    	pw=new PrintWriter(log);
	    	pw.println(firstCommit.getKey());
	    	pw.close();
	    	//����ݴ�����¼
	    	pw=new PrintWriter(index);
	    	pw.println("");
	    	pw.close();
	    	//����main��֧
	    	Branch main=new Branch("Main",firstCommit);
	    	//����head���󲢽�main��֧д��head�ļ�
		    Head.setHeadPath(gitPath);
		    head=new Head(main);
	   }
   }
   
   //��index������ļ�
   public void addFile(String fileName) throws Exception {
	   File file=new File(repositoryPath,fileName);
	   if(!file.exists()) {
		   System.out.println("�ļ������ڣ�");
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
   
   //�����µ�commit
   public void createCommit() throws Exception{
	   Tree tree=new Tree(index);
       Commit firstCommit=new Commit("",tree.getKey());
   	   //��commit��¼д��log�ļ�
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
   	   //����ݴ�����¼
   	   pw=new PrintWriter(index);
   	   pw.println("");
   	   pw.close();
   }
   
   //�����µ�branch���л������·�֧
   public void createBranch(String branchName) throws Exception{
	   Branch branch=new Branch(branchName);
   	   //���·�֧д��head�ļ�
	   head.setHead(branch);
   }
   
   //���ķ�֧
   public void setBranch(String branchName)throws Exception {
	   Branch branch=new Branch(branchName,head.getBranch().getCommit());
   	   //���·�֧д��head�ļ�
	   head.setHead(branch);
   }
   
   //�鿴log�ļ�
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
