package HashGit;
import java.io.*;


public class Tree extends GitObject{

    public Tree(String path) throws Exception {
        genValue(path);
        //根据value的内容生成对应的key
        genKey();
        //写入本地文件
        this.writeFile();
    }
    
    //生成value
    public void genValue(String path)throws Exception{
    	File file=new File(path);
    	//根据该目录下的内容生成value
        for(File f:file.listFiles()){
            if(f.isFile()){
                value+=  "blob " + new Blob(f).getKey() + " " + f.getName()+"\n";
            }
            else if(f.isDirectory()){
                value+= "tree " + new Tree(f.getName()).getKey() + " " + f.getName()+"\n";
            }
        }
    }

}