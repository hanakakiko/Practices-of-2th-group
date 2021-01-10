//这个文档用来生成一些初始的文件，并自动测试每个类里的主要函数功能是否正常


package CommandGit;

import HashGit.Blob;
import HashGit.Commit;
import HashGit.Head;
import HashGit.Tree;

import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;

public class commit {

    private static String repositoryPath;
    private String gitPath;
    private File log;
    private File index;
    private Head head;

    //生成新的commit
    public void commit() throws Exception{
        Tree tree=new Tree(index);
        Commit firstCommit=new Commit("",tree.getKey());
        //将commit记录写入log文件
        String temp="";
        Scanner input=new Scanner(index);
        while(true) {
            String t=input.nextLine();
            if(t=="") break;
            temp+=t;
        }
        input.close();
        temp+="\n";
        PrintWriter pw=new PrintWriter(log);
        pw.println(temp+firstCommit.getKey());
        pw.close();
        //清空暂存区记录
        pw=new PrintWriter(index);
        pw.println("");
        pw.close();
    }
}
