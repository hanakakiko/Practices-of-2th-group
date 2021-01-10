package HashGit;

import java.io.*;
import java.util.Scanner;

public class Blob extends GitObject {
	//构造函数,根据给定路径生成value和对应的key
    public Blob(String sourcePath) throws Exception {
    	this.type="blob";
    	this.sourcePath=sourcePath;
    	File sourceFile=new File(this.sourcePath);
    	this.sourceFile=sourceFile;
        //根据文件内容生成对应的key
        genKey();
        this.objectFile=new File(objectPath+"\\"+this.key);
        this.writeWithFile();
    }
    
    //构造函数，根据给定文件生成value和对应的key
    public Blob(File file) throws Exception{
    	genValue(file);
    	genKey();
    	this.writeWithFile();
    }
    
    //生成value
    public void genValue(String path)throws Exception{
    	//根据文件内容生成value
    	File file=new File(path);
    	genValue(file);
    }
    //
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
