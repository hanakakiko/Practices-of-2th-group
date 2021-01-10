package HashGit;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Branch {
	private static String branchPath="";
	private File branchFile;
	private String branchName="";
    private Commit currentCommit;
    private String currentCommitKey="";
    
    //设置branch对象存放路径
    public static void setBranchPath(String givenBranchPath) {
    	branchPath=givenBranchPath;
    }
    
    //根据本地文件创建branch对象
    public Branch(String branchName)throws IOException{
        this.branchName=branchName;
    	branchFile=new File(branchPath+"\\"+this.branchName);
    	if(!branchFile.exists()) {
    		System.out.println("该分支不存在！");
    	    return;
    	}
    	else {
    		Scanner input=new Scanner(branchFile);
    		this.currentCommitKey=input.nextLine();
    		input.close();
    	}
    }
    
    //根据commit创建新branch对象
    public Branch(String branchName,Commit currentCommit) throws IOException {
    	this.branchName=branchName;
    	this.currentCommit=currentCommit;
    	this.currentCommitKey=this.currentCommit.getKey();
        branchFile=new File(branchPath+"/"+branchName);
        //写入本地文件
        branchFile.createNewFile();
    	PrintWriter pw=new PrintWriter(branchFile);
    	pw.print(this.currentCommitKey);
    	pw.close();
    }
    
    //获取分支名称
    public String getBranchName() {
    	return this.branchName;
    }
    
    //获取当前commit
    public Commit getCommit() {
    	return this.currentCommit;
    }
    
    //更改当前指向的commit
    public void modifyCommitKey(String currentCommitKey) throws FileNotFoundException {
    	this.currentCommitKey=currentCommitKey;
        //覆盖本地文件中指向commit的key值
    	PrintWriter pw=new PrintWriter(branchFile);
    	pw.print(this.currentCommitKey);
    	pw.close();
    }
    
    //soft回滚，使得branch当前所指分支为上一次commit
    public void softReset() throws FileNotFoundException {
    	File currentCommitFile=new File(GitObject.getObjectPath()+"\\"+this.currentCommitKey);
    	if(!currentCommitFile.exists()) {
    		System.out.println("回滚失败！");
    		return;
    	}
    	Scanner input=new Scanner(currentCommitFile);
    	String firstLine=input.nextLine();
    	String[] parent=firstLine.split(" ");
    	if(!parent[0].equals("parent")) {
    		System.out.println("该提交已经是第一次提交，不可回滚！");
    		input.close();
    		return;
    	}
        this.modifyCommitKey(parent[1]);
        input.close();
    }
    
    //mixed回滚,不仅更改branch指向，还更改index内容
    public void mixedReset()throws IOException{
    	File currentCommitFile=new File(GitObject.getObjectPath()+"\\"+this.currentCommitKey);
    	if(!currentCommitFile.exists()) {
    		System.out.println("回滚失败！");
    		return;
    	}
    	Scanner input=new Scanner(currentCommitFile);
    	String firstLine=input.nextLine();
    	String[] parent=firstLine.split(" ");
    	if(!parent[0].equals("parent")) {
    		System.out.println("该提交已经是第一次提交，不可回滚！");
    		input.close();
    		return;
    	}
        this.modifyCommitKey(parent[1]);
        String secondLine=input.nextLine();
        String[] tree=secondLine.split(" ");
        File srcTree=new File(GitObject.getObjectPath()+"\\"+tree[1]);
        input.close();
        File index=new File(Repository.getRepositoryPath());
        try(BufferedInputStream in = new BufferedInputStream(new FileInputStream(srcTree));
                BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(index))){
                int r=0;
                while((r=in.read())!=-1){
                    out.write((byte)r);
                }
        }
    }
    
    //hard回滚
    public void hardReset() throws Exception{
    	File currentCommitFile=new File(GitObject.getObjectPath()+"\\"+this.currentCommitKey);
    	if(!currentCommitFile.exists()) {
    		System.out.println("回滚失败！");
    		return;
    	}
    	Scanner input=new Scanner(currentCommitFile);
    	String firstLine=input.nextLine();
    	String[] parent=firstLine.split(" ");
    	if(!parent[0].equals("parent")) {
    		System.out.println("该提交已经是第一次提交，不可回滚！");
    		input.close();
    		return;
    	}
        this.modifyCommitKey(parent[1]);
        String secondLine=input.nextLine();
        String[] tree=secondLine.split(" ");
        File srcTree=new File(GitObject.getObjectPath()+"\\"+tree[1]);
        input.close();
        File index=new File(Repository.getRepositoryPath());
        try(BufferedInputStream in = new BufferedInputStream(new FileInputStream(srcTree));
                BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(index))){
                int r=0;
                while((r=in.read())!=-1){
                    out.write((byte)r);
                }
        }
        //将git仓库中的文件还原到工作区
        File src=new File(Repository.getRepositoryPath());
        resetTree(srcTree,src);
    }
    
    //将git仓库中的树还原到工作区
    public void resetTree(File srcTree, File src) throws Exception{
        try(Scanner in = new Scanner(srcTree)){
            while(in.hasNext()){
                String line = in.nextLine();
                String[] lineSplited = line.split(" ");
                String type = lineSplited[0];
                String hashcode = lineSplited[1];
                String fileName = lineSplited[2];
                if(type.equals("blob")){
                    resetBlob(new File(srcTree.getParent(),hashcode),new File(src,fileName));
                }
                else if(type.equals("tree")){
                    File newDes = new File(src,fileName);
                    resetTree(new File(srcTree.getParent(), hashcode),newDes);
                }
            }
        }
    }
    
    //将git仓库中的blob还原到工作区
    public void resetBlob(File blob, File src) throws Exception{
        try(BufferedInputStream in = new BufferedInputStream(new FileInputStream(blob));
            BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(src))){
            int r=0;
            while((r=in.read())!=-1){
                out.write((byte)r);
            }
        }
    }
}
