package HashGit;

import java.io.*;
import java.util.Scanner;

public class Blob extends GitObject {
	//���캯��
    public Blob(File file) throws Exception {
    	//�����ļ���������value
    	Scanner input=new Scanner(file);
    	while(true) {
    		String temp=input.nextLine();
    		if(temp=="") break;
    		value+=temp;
    	}
    	input.close();
    	
    	//����value���ɶ�Ӧ��key
        genKey();
    }
}
