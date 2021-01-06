package HashGit;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

//用于存放当前分支文件
public class Head {
    private String path;
    public Head() throws IOException {
    	//创建初始分支
    	this.path="ref:refs/heads/main";
    	//写入本地文件
        writeHead();
    }
    
    public void setHead(String branchName) throws IOException {
    	this.path="ref:/refs/heads/"+branchName;
    	//更改本地文件
    	writeHead();
    }
    
    //写入本地文件
    public void writeHead() throws IOException {
		PrintWriter output=new PrintWriter(new File(path)); 
    	output.println(this.path);
    }
}
