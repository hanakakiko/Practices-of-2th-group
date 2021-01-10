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
    
    //����branch������·��
    public static void setBranchPath(String givenBranchPath) {
    	branchPath=givenBranchPath;
    }
    
    //���ݱ����ļ�����branch����
    public Branch(String branchName)throws IOException{
        this.branchName=branchName;
    	branchFile=new File(branchPath+"\\"+this.branchName);
    	if(!branchFile.exists()) {
    		System.out.println("�÷�֧�����ڣ�");
    	    return;
    	}
    	else {
    		Scanner input=new Scanner(branchFile);
    		this.currentCommitKey=input.nextLine();
    		input.close();
    	}
    }
    
    //����commit������branch����
    public Branch(String branchName,Commit currentCommit) throws IOException {
    	this.branchName=branchName;
    	this.currentCommit=currentCommit;
    	this.currentCommitKey=this.currentCommit.getKey();
        branchFile=new File(branchPath+"/"+branchName);
        //д�뱾���ļ�
        branchFile.createNewFile();
    	PrintWriter pw=new PrintWriter(branchFile);
    	pw.print(this.currentCommitKey);
    	pw.close();
    }
    
    //��ȡ��֧����
    public String getBranchName() {
    	return this.branchName;
    }
    
    //��ȡ��ǰcommit
    public Commit getCommit() {
    	return this.currentCommit;
    }
    
    //���ĵ�ǰָ���commit
    public void modifyCommitKey(String currentCommitKey) throws FileNotFoundException {
    	this.currentCommitKey=currentCommitKey;
        //���Ǳ����ļ���ָ��commit��keyֵ
    	PrintWriter pw=new PrintWriter(branchFile);
    	pw.print(this.currentCommitKey);
    	pw.close();
    }
    
    //soft�ع���ʹ��branch��ǰ��ָ��֧Ϊ��һ��commit
    public void softReset() throws FileNotFoundException {
    	File currentCommitFile=new File(GitObject.getObjectPath()+"\\"+this.currentCommitKey);
    	if(!currentCommitFile.exists()) {
    		System.out.println("�ع�ʧ�ܣ�");
    		return;
    	}
    	Scanner input=new Scanner(currentCommitFile);
    	String firstLine=input.nextLine();
    	String[] parent=firstLine.split(" ");
    	if(!parent[0].equals("parent")) {
    		System.out.println("���ύ�Ѿ��ǵ�һ���ύ�����ɻع���");
    		input.close();
    		return;
    	}
        this.modifyCommitKey(parent[1]);
        input.close();
    }
    
    //mixed�ع�,��������branchָ�򣬻�����index����
    public void mixedReset()throws IOException{
    	File currentCommitFile=new File(GitObject.getObjectPath()+"\\"+this.currentCommitKey);
    	if(!currentCommitFile.exists()) {
    		System.out.println("�ع�ʧ�ܣ�");
    		return;
    	}
    	Scanner input=new Scanner(currentCommitFile);
    	String firstLine=input.nextLine();
    	String[] parent=firstLine.split(" ");
    	if(!parent[0].equals("parent")) {
    		System.out.println("���ύ�Ѿ��ǵ�һ���ύ�����ɻع���");
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
    
    //hard�ع�
    public void hardReset() throws Exception{
    	File currentCommitFile=new File(GitObject.getObjectPath()+"\\"+this.currentCommitKey);
    	if(!currentCommitFile.exists()) {
    		System.out.println("�ع�ʧ�ܣ�");
    		return;
    	}
    	Scanner input=new Scanner(currentCommitFile);
    	String firstLine=input.nextLine();
    	String[] parent=firstLine.split(" ");
    	if(!parent[0].equals("parent")) {
    		System.out.println("���ύ�Ѿ��ǵ�һ���ύ�����ɻع���");
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
        //��git�ֿ��е��ļ���ԭ��������
        File src=new File(Repository.getRepositoryPath());
        resetTree(srcTree,src);
    }
    
    //��git�ֿ��е�����ԭ��������
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
    
    //��git�ֿ��е�blob��ԭ��������
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
