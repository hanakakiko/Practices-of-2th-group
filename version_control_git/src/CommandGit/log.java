package CommandGit;

import HashGit.Blob;
import HashGit.Head;
import HashGit.Tree;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class log {

    private static String repositoryPath;
    private String gitPath;
    private File log;
    private File index;
    private Head head;

    //查看log文件
    public void log() throws FileNotFoundException {
        Scanner input=new Scanner(log);
        while(true) {
            String temp=input.nextLine();
            if(temp.equals("")) break;
            System.out.println(temp);
        }
        input.close();
    }
}
