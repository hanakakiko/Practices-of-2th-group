//这个类用来生成初始test文件夹，其中放入一些测试用的文件，来对每个类的主要方法进行测试

import HashGit.Blob;
import HashGit.SHA1CheckSum;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class Test {

    private static String path;
    private static String content1;
    public static void creatTest() {
        File folder = new File(path);
        folder.mkdir();
        content1="test1";
        PrintWriter printWriter = null;
        try {
            printWriter = new PrintWriter(path+"1.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        printWriter.print(content1);//在本项目所在路径下创建一个test文件夹，里面创建一个内容为“test1”的1.txt文件
    }

    public static void testSHA1Checksum() throws Exception {
        assert new SHA1CheckSum(path+"1.txt").getSha1().length() == 40;//测试SHA1CheckSum(File inFile)
        assert new SHA1CheckSum(content1).getSha1().length() == 40;//测试SHA1CheckSum(String value)
    };

    public static void testBlob() throws Exception {
        try {
            Blob a = new Blob(new File(path + "1.txt"));//用1.txt的路径构造blob对象
            a.genValue(path);
            a.genValue(path);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    /*
    public static void testBranch(String master){
        try{

        }catch (Exception e){

        }
    }
     */

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

