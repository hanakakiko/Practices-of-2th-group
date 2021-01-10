package CommandGit;

import HashGit.Blob;
import HashGit.Head;
import HashGit.Tree;

import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;

public class add {

    private static String repositoryPath;
    private String gitPath;
    private File log;
    private File index;
    private Head head;

    public void add(String fileName) throws Exception {
        File file=new File(repositoryPath,fileName);
        if(!file.exists()) {
            System.out.println("文件不存在！");
            return;
        }
        String temp="";
        Scanner input=new Scanner(index);
        while(true) {
            String t=input.nextLine();
            if(t=="") break;
            temp+=t;
        }
        input.close();
        temp+="\n";
        if(file.isFile()){
            temp+=  "blob " + new Blob(file).getKey() + " " + fileName+"\n";
        }
        else if(file.isDirectory()){
            temp+=  "tree " + new Tree(repositoryPath+"\\"+fileName).getKey() + " " + fileName+"\n";
        }
        PrintWriter pw=new PrintWriter(index);
        pw.println(temp);
        pw.close();
    }
}
