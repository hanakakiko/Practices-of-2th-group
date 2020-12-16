package HashGit;
import java.io.*;

public class Tree extends GitObject{

    public Tree(File file) throws Exception {
    	//���ݸ�Ŀ¼�µ���������value
        for(File f:file.listFiles()){
            if(f.isFile()){
                value+=  "\n" + "blob " + new Blob(f).getKey() + " " + f.getName();
            }
            else if(f.isDirectory()){
                value+= "\n" + "tree " + new Tree(f).getKey() + " " + f.getName();
            }
        }
        //����value���������ɶ�Ӧ��key
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