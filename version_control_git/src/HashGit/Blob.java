package HashGit;

import java.io.*;
import java.util.Scanner;

public class Blob extends GitObject {
	//构造函数,根据给定路径生成value和对应的key
    public Blob(String path) throws Exception {
    	//根据文件内容生成value
        genValue(path);
    	//根据value生成对应的key
        genKey();
        this.writeFile();
    }
    
    //构造函数，根据给定文件生成value和对应的key
    public Blob(File file) throws Exception{
    	genValue(file);
    	genKey();
    	this.writeFile();
    }
    
    //生成value
    public void genValue(String path)throws Exception{
    	//根据文件内容生成value
    	File file=new File(path);
    	genValue(file);
    }
    
    public void genValue(File file)throws Exception{
    	//根据文件内容生成value
    	Scanner input=new Scanner(file);
    	while(true) {
    		String temp=input.nextLine();
    		if(temp=="") break;
    		value+=temp;
    	}
    	input.close();
    }
}
