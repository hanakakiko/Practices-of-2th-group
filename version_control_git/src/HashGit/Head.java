package HashGit;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

//���ڴ�ŵ�ǰ��֧�ļ�
public class Head {
    private String path;  //���ڼ�¼headָ���֧���ļ�·��
    //�޲ι��캯��
    public Head() {}
    
    //���캯��
    public Head(String branchPath,String headPath,String branchName) throws IOException {
    	this.path=branchPath+"/"+branchName;
    	writeHead(headPath);
    }
    
    public void setHead(String branchPath,String headPath,String branchName) throws IOException {
    	this.path=branchPath+"/"+branchName;
    	//���ı����ļ�
    	writeHead(headPath);
    }
    
    //д�뱾���ļ�
    public void writeHead(String headPath) throws IOException {
		PrintWriter output=new PrintWriter(new File(headPath)); 
    	output.println(this.path);
    }
}
