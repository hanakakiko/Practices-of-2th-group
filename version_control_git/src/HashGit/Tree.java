package HashGit;
import java.io.*;

public class Tree extends GitObject{

    public Tree(File file) throws Exception {
    	//根据该目录下的内容生成value
        for(File f:file.listFiles()){
            if(f.isFile()){
                value+=  "\n" + "blob " + new Blob(f).getKey() + " " + f.getName();
            }
            else if(f.isDirectory()){
                value+= "\n" + "tree " + new Tree(f).getKey() + " " + f.getName();
            }
        }
        //根据value的内容生成对应的key
        genKey();
    }

    @Override
    public void write() throws Exception {
        PrintWriter pw = new PrintWriter(this.key);
        pw.print(value);
        pw.close();
    }

    public String getValue(){
        return this.value;
    }
}