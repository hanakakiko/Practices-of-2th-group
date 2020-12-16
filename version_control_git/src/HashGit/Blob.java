package HashGit;

import java.io.*;
import java.util.Scanner;

public class Blob extends GitObject {
	//构造函数
    public Blob(File file) throws Exception {
    	//根据文件内容生成value
    	Scanner input=new Scanner(file);
    	while(true) {
    		String temp=input.nextLine();
    		if(temp=="") break;
    		value+=temp;
    	}
    	input.close();
    	
    	//根据value生成对应的key
        genKey();
    }
}
