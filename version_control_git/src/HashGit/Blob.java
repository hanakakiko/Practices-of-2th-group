package HashGit;

import java.io.*;
import java.util.Scanner;

public class Blob extends GitObject {
	//���캯��,���ݸ���·������value�Ͷ�Ӧ��key
    public Blob(String path) throws Exception {
    	//�����ļ���������value
        genValue(path);
    	//����value���ɶ�Ӧ��key
        genKey();
        this.writeFile();
    }
    
    //���캯�������ݸ����ļ�����value�Ͷ�Ӧ��key
    public Blob(File file) throws Exception{
    	genValue(file);
    	genKey();
    	this.writeFile();
    }
    
    //����value
    public void genValue(String path)throws Exception{
    	//�����ļ���������value
    	File file=new File(path);
    	genValue(file);
    }
    
    public void genValue(File file)throws Exception{
    	//�����ļ���������value
    	Scanner input=new Scanner(file);
    	while(true) {
    		String temp=input.nextLine();
    		if(temp=="") break;
    		value+=temp;
    	}
    	input.close();
    }
}
