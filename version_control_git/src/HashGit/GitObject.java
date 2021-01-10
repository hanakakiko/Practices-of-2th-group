package HashGit;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.PrintWriter;

//�汾������Object��
public abstract class GitObject {
	protected String type="";
    protected String key="";  //���ڴ��object�ļ���key
    protected String value="";  //���ڴ��tree object��commit object��value
    protected File objectFile;  //object�ļ�
    protected File sourceFile;  //object�ļ�����Ӧ��Դ�ļ�
    protected String sourcePath="";
    protected static String objectPath="";
    
    //�趨Object�ļ��洢·��
    public static void setObjectPath(String givenObjectPath) {
    	objectPath=givenObjectPath;
    }
    
    public static String getObjectPath() {
    	return objectPath;
    }
    
    //����value���ļ��������ɶ�Ӧ��key
    public void genKey() throws Exception{
    	if(type=="blob") {
    		SHA1CheckSum s=new SHA1CheckSum(sourceFile);
    		this.key=s.getSha1();
    	}
    	else {
    		SHA1CheckSum s=new SHA1CheckSum(value);
    		this.key=s.getSha1();
    	}
    };
    
    //��object��Ӧ��Դ�ļ�д�뱾��git�ֿ�
    public void writeWithFile() throws Exception{
        try(BufferedInputStream in = new BufferedInputStream(new FileInputStream(this.sourceFile));
            BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(this.objectFile))){
            int r=0;
            while((r=in.read())!=-1){
                out.write((byte)r);
            }
        }
    }
    
    //��tree��commit�е�valueд�뱾��git�ֿ�
    public void writeWithChars()throws Exception{
    	objectFile=new File(objectPath+"\\"+this.key);
    	if(!objectFile.exists()) {
    		PrintWriter pw=new PrintWriter(objectFile);
    		pw.print(this.value);
    		pw.close();
    	}
    }
    
    //��ȡObject��keyֵ
    public String getKey() {
    	return this.key;
    }
    
    //��ȡObject��value
    public String getValue() {
    	return this.value;
    }

}