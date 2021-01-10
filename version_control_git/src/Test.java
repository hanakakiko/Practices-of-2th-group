//这个类用来生成初始test文件夹，其中放入一些测试用的文件，来对每个类的主要方法进行测试

import HashGit.Blob;
import HashGit.SHA1CheckSum;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class Test {
//    public static void testName(String filename){
//        File file = new File(filename);
//        try{
//            if(file.isDirectory()) {
//                Tree tree = new Tree(file);
//                System.out.println(tree);
//                assert tree.getKey().length() == 40;
//            }
//            else{
//                Blob blob = new Blob(file);
//                System.out.println(blob);
//                assert blob.getKey().length() == 40;
//            }
//        }
//        catch (Exception ex){
//            ex.printStackTrace();
//        }
//    }
//
//    public static void testContent(String filename){
//        File file = new File(filename);
//        try{
//            if(file.isDirectory()){
//                System.out.println(new Tree(file).getContent());
//            }
//            else{
//                System.out.println(new Blob(file));
//            }
//        }
//        catch (Exception ex){
//            ex.printStackTrace();
//        }
//    }
//
//    public static void testName(File file){
//        try{
//            if(file.isDirectory()){
//                Tree tree = new Tree(file);
//                System.out.println(tree);
//                assert tree.getKey().length() == 40;
//            }
//            else{
//                Blob blob = new Blob(file);
//                System.out.println(blob);
//                assert blob.getKey().length() == 40;
//            }
//        }
//        catch (Exception ex){
//            ex.printStackTrace();
//        }
//    }
//
//    public static void testTree(File file){
//        try{
//            if(file.isDirectory()){
//                System.out.println(new Tree(file).getContent());
//            }
//            else{
//                System.out.println(new Blob(file));
//            }
//        }
//        catch (Exception ex){
//            ex.printStackTrace();
//        }
//    }
//
//    public static void testBranch(String branch) throws Exception{
//        Branch b = new Branch(branch,"");
//        b.write(); // generate a branch
//    }
//
//    public static void testHead(String branch) throws Exception{
//        Head head = new Head(branch);
//        head.write(); // generate HEAD file
//    }
//
//    public static void testCommit(String path) throws Exception{
//        Commit commit1 = new Commit("chenpeng","chenpeng","first commit");
//        System.out.println(commit1.getContent());
//        commit1.write();
//
//        PrintWriter printWriter = new PrintWriter(path+"/a.txt"); // don't forget /
//        printWriter.print("hello world");
//        printWriter.close();
//        Commit commit2 = new Commit("chenpeng","chenpeng","second commit");
//        System.out.println(commit2.getContent());
//        commit2.write();
//
//    }
//
//    public static File genFile(){
//        File root = new File("root");
//        File blob1 = new File(root,"blob1");
//        File tree1 = new File(root,"tree1");
//        File blob2 = new File(tree1,"blob2");
//        return root;
//    }
//
//    public static void testWorkingDir(){
//        System.out.println("Working Directory = " + System.getProperty("user.dir"));
//    }
//
    private static String path;
    private static String content1;
    public static void creatTest() throws FileNotFoundException {
        File folder = new File(path);
        folder.mkdir();
        String content1="test1";
        PrintWriter printWriter = new PrintWriter(path+"1.txt");
        printWriter.print(content1);//在本项目所在路径下创建一个test文件夹，里面创建一个内容为“test1”的1.txt文件
    }

    public static void testSHA1Checksum() throws Exception {
        assert new SHA1CheckSum(path+"1.txt").getSha1().length() == 40;//测试SHA1CheckSum(File inFile)
        assert new SHA1CheckSum(content1).getSha1().length() == 40;//测试SHA1CheckSum(String value)
    };

    public static void testBlob() throws Exception {
        Blob a=new Blob(path+"1.txt");//用1.txt的路径构造blob对象
    }

   public static void main(String[] args){
        try {
            String path = "./test";//在当前项目里产生一个test目录用来存放测试产生的文件
            creatTest();
            testSHA1Checksum();//测试SHA1CheckSum类
            testBlob();
            //testBranch("master");
            //testHead("master");
            //testCommit(path);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        //testWorkingDir();

   }
}
