package HashGit;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

//用于存放当前分支文件
public class Head {
    private String path;  //用于记录head指向分支的文件路径
    //无参构造函数
    public Head() {}
    
    //构造函数
    public Head(String branchPath,String headPath,String branchName) throws IOException {
    	this.path=branchPath+"/"+branchName;
    	writeHead(headPath);
    }
    
    public void setHead(String branchPath,String headPath,String branchName) throws IOException {
    	this.path=branchPath+"/"+branchName;
    	//更改本地文件
    	writeHead(headPath);
    }
    
    //写入本地文件
    public void writeHead(String headPath) throws IOException {
		PrintWriter output=new PrintWriter(new File(headPath)); 
    	output.println(this.path);
    }
}
