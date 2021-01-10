package HashGit;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

//用于存放当前分支文件
public class Head {
    private static String headPath="";  //用于记录head指向分支的文件路径
    private Branch currentBranch;
    private File head;
    
    //设置head文件存放路径
    public static void setHeadPath(String givenHeadPath) {
    	headPath=givenHeadPath;
    }
    
    //构造函数
    public Head(Branch currentBranch) throws IOException {
        File head=new File(headPath+"\\"+"HEAD");
    	this.currentBranch=currentBranch;
    	//写入文件
    	PrintWriter pw=new PrintWriter(head);
    	pw.println(this.currentBranch.getBranchName());
    	pw.close();
    }
    
    public void setHead(Branch currentBranch) throws IOException {
    	this.currentBranch=currentBranch;
    	//写入文件
    	PrintWriter pw=new PrintWriter(head);
    	pw.println(this.currentBranch.getBranchName());
    	pw.close();
    }
    
    public Branch getBranch() {
    	return this.currentBranch;
    }
}
