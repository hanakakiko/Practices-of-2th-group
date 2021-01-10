package HashGit;
import java.io.*;
import java.util.Scanner;


public class Tree extends GitObject{
    //根据给定的文件夹生成树
    public Tree(String sourcePath) throws Exception {
        this.type="tree";
        this.sourcePath=sourcePath;
    	genValue();
        //根据value的内容生成对应的key
        genKey();
        //写入本地文件
        this.writeWithChars();
    }
    
    //根据暂存区的内容生成树
    public Tree(File index) throws Exception{
    	this.type="tree";
    	//根据暂存区内容生成value
    	Scanner input=new Scanner(index);
    	while(input.hasNextLine()) {
    		value+=input.nextLine()+"\n";
    	}
    	input.close();
    	//根据value的值生成对应的key
    	genKey();
    	//写入本地文件
    	this.writeWithChars();
    }
    
    //生成value
    public void genValue()throws Exception{
    	File file=new File(this.sourcePath);
    	//根据该目录下的内容生成value
        for(File f:file.listFiles()){
            if(f.isFile()){
                value+=  "blob " + new Blob(sourcePath+"\\"+f.getName()).getKey() + " " + f.getName()+"\n";
            }
            else if(f.isDirectory()){
                value+= "tree " + new Tree(sourcePath+"\\"+f.getName()).getKey() + " " + f.getName()+"\n";
            }
        }
    }

}