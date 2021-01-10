package HashGit;
import java.io.*;
import java.util.Scanner;


public class Tree extends GitObject{
    //���ݸ������ļ���������
    public Tree(String sourcePath) throws Exception {
        this.type="tree";
        this.sourcePath=sourcePath;
    	genValue();
        //����value���������ɶ�Ӧ��key
        genKey();
        //д�뱾���ļ�
        this.writeWithChars();
    }
    
    //�����ݴ���������������
    public Tree(File index) throws Exception{
    	this.type="tree";
    	//�����ݴ�����������value
    	Scanner input=new Scanner(index);
    	while(input.hasNextLine()) {
    		value+=input.nextLine()+"\n";
    	}
    	input.close();
    	//����value��ֵ���ɶ�Ӧ��key
    	genKey();
    	//д�뱾���ļ�
    	this.writeWithChars();
    }
    
    //����value
    public void genValue()throws Exception{
    	File file=new File(this.sourcePath);
    	//���ݸ�Ŀ¼�µ���������value
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