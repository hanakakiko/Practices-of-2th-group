package CommandGit;

import java.io.File;
import java.io.PrintWriter;

import HashGit.*;
import org.apache.commons.cli.Options;

public class init {
    private static String repositoryPath;

    //获得仓库路径
    public static String getRepositoryPath() {
        return repositoryPath;
    }

    //根据给定目录创建仓库对象
    public void init(String givenRepositoryPath) throws Exception {
        System.out.println("init succeed!!");
        repositoryPath=givenRepositoryPath;
        String gitPath = repositoryPath + "\\" + ".git";
        File gitRepo=new File(gitPath);
        if(!gitRepo.exists()) {
            gitRepo.mkdir();
            //创建objects文件夹
            GitObject.setObjectPath(gitPath +"\\"+"objects");
            File objectDir=new File(gitPath +"\\"+"objects");
            objectDir.mkdir();
            //创建heads文件夹以存放branch文件
            Branch.setBranchPath(gitPath +"\\"+"heads");
            File branchDir=new File(gitPath +"\\"+"heads");
            branchDir.mkdir();
            //新建index文件
            File index = new File(gitPath, "index");
            //生成第一次commit
            File repo=new File(repositoryPath);
            String temp="";
            for(File f:repo.listFiles()){
                if(f.getName().equals(".git")) continue;
                if(f.isFile()){
                    temp+=  "blob " + new Blob(repositoryPath+"\\"+f.getName()).getKey() + " " + f.getName()+"\n";
                }
                else if(f.isDirectory()){
                    temp+=  "tree " + new Tree(repositoryPath+"\\"+f.getName()).getKey() + " " + f.getName()+"\n";
                }
            }
            //将根目录下各文件的值写入index文件
            PrintWriter pw=new PrintWriter(index);
            pw.println(temp);
            pw.close();
            //根据index文件生成commit
            Tree tree=new Tree(index);
            Commit firstCommit=new Commit("",tree.getKey());
            //将commit记录写入log文件
            File log = new File(gitPath + "\\" + "log");
            pw=new PrintWriter(log);
            pw.println(firstCommit.getKey());
            pw.close();
            //清空暂存区记录
            pw=new PrintWriter(index);
            pw.println("");
            pw.close();
            //生成main分支
            Branch main=new Branch("Main",firstCommit);
            //创建head对象并将main分支写入head文件
            Head.setHeadPath(gitPath);
            Head head = new Head(main);
        }
    }
    public static void main(String args[]){
        try {
            new init().init(args[0]);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
