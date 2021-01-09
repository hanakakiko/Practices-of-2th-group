package HashGit;
import java.io.*;


public class Tree extends GitObject{

    public Tree(String path) throws Exception {
        genValue(path);
        //����value���������ɶ�Ӧ��key
        genKey();
        //д�뱾���ļ�
        this.writeFile();
    }
    
    //����value
    public void genValue(String path)throws Exception{
    	File file=new File(path);
    	//���ݸ�Ŀ¼�µ���������value
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