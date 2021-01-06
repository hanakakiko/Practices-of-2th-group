package HashGit;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

//���ڴ�ŵ�ǰ��֧�ļ�
public class Head {
    private String path;
    public Head() throws IOException {
    	//������ʼ��֧
    	this.path="ref:refs/heads/main";
    	//д�뱾���ļ�
        writeHead();
    }
    
    public void setHead(String branchName) throws IOException {
    	this.path="ref:/refs/heads/"+branchName;
    	//���ı����ļ�
    	writeHead();
    }
    
    //д�뱾���ļ�
    public void writeHead() throws IOException {
		PrintWriter output=new PrintWriter(new File(path)); 
    	output.println(this.path);
    }
}
