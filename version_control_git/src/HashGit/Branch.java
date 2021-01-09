package HashGit;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Branch {
	private String branchName;
    private String currentCommitKey;
    //��ʼ��branch����
    public Branch(String path,String branchName) throws IOException {
    	this.branchName=branchName;
        File branch=new File(path+"/"+branchName);
        if(branch.exists()) {
        	Scanner input=new Scanner(branch);
        	this.currentCommitKey=input.nextLine();
        	input.close();
        }
        else this.currentCommitKey="";
    }
    
    //����һ���µ�branch
    public void createBranch(String path,String currentCommitKey) throws IOException {
    	this.currentCommitKey=currentCommitKey;
    	//���������ļ�
    	File branch=new File(path+"/"+branchName);
    	branch.createNewFile();
    	PrintWriter output=new PrintWriter(branch);
    	output.print(this.currentCommitKey);
    	output.close();
    }
    
    //��ȡ��֧����
    public String getBranchName() {
    	return this.branchName;
    }
    
    //���ĵ�ǰָ���commit
    public void modifyCommitKey(String path,String currentCommitKey) throws FileNotFoundException {
    	this.currentCommitKey=currentCommitKey;
    	File branch=new File(path+"/"+branchName);
    	PrintWriter output=new PrintWriter(branch);
    	output.print(this.currentCommitKey);
    	output.close();
    }
    
    //soft�ع�
    public void softReset(String path,String commitKey) throws FileNotFoundException {
    	this.currentCommitKey=commitKey;
    	File branch=new File(path+"/.git"+"/heads"+branchName);
    	PrintWriter output=new PrintWriter(branch);
    	output.print(this.currentCommitKey);
    	output.close();
    }
    
    //mixed�ع�
    public void mixedReset(String path,String commitKey)throws IOException{
        softReset(path,commitKey);	
        File index=new File(path+"/.git","index");
        PrintWriter output=new PrintWriter(index);
        String temp=KeyValueStore.searchValue(commitKey);
        output.print(temp);
        output.close();
    }
    
    //hard�ع�
    public void hardReset(String path,String commitKey) throws IOException{
    	mixedReset(path,commitKey);
    	
    }
}
