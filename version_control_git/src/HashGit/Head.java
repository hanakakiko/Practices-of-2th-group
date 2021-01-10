package HashGit;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

//���ڴ�ŵ�ǰ��֧�ļ�
public class Head {
    private static String headPath="";  //���ڼ�¼headָ���֧���ļ�·��
    private Branch currentBranch;
    private File head;
    
    //����head�ļ����·��
    public static void setHeadPath(String givenHeadPath) {
    	headPath=givenHeadPath;
    }
    
    //���캯��
    public Head(Branch currentBranch) throws IOException {
        File head=new File(headPath+"\\"+"HEAD");
    	this.currentBranch=currentBranch;
    	//д���ļ�
    	PrintWriter pw=new PrintWriter(head);
    	pw.println(this.currentBranch.getBranchName());
    	pw.close();
    }
    
    public void setHead(Branch currentBranch) throws IOException {
    	this.currentBranch=currentBranch;
    	//д���ļ�
    	PrintWriter pw=new PrintWriter(head);
    	pw.println(this.currentBranch.getBranchName());
    	pw.close();
    }
    
    public Branch getBranch() {
    	return this.currentBranch;
    }
}
