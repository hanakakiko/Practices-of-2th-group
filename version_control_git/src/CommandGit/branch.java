package CommandGit;

import HashGit.Blob;
import HashGit.Branch;
import HashGit.Head;
import HashGit.Tree;

import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;

public class branch {

    private static String repositoryPath;
    private String gitPath;
    private File log;
    private File index;
    private Head head;

    //切换分支
    public void checkout(String branchName) throws Exception{
        Branch branch=new Branch(branchName);
        //将新分支写入head文件
        head.setHead(branch);
    }

    //创建分支
    public void branch(String branchName)throws Exception {
        Branch branch=new Branch(branchName,head.getBranch().getCommit());
    }
}
