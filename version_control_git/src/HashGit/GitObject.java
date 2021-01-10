package HashGit;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.PrintWriter;

//版本管理工具Object类
public abstract class GitObject {
	protected String type="";
    protected String key="";  //用于存放object文件的key
    protected String value="";  //用于存放tree object和commit object的value
    protected File objectFile;  //object文件
    protected File sourceFile;  //object文件所对应的源文件
    protected String sourcePath="";
    protected static String objectPath="";
    
    //设定Object文件存储路径
    public static void setObjectPath(String givenObjectPath) {
    	objectPath=givenObjectPath;
    }
    
    public static String getObjectPath() {
    	return objectPath;
    }
    
    //根据value或文件内容生成对应的key
    public void genKey() throws Exception{
    	if(type=="blob") {
    		SHA1CheckSum s=new SHA1CheckSum(sourceFile);
    		this.key=s.getSha1();
    	}
    	else {
    		SHA1CheckSum s=new SHA1CheckSum(value);
    		this.key=s.getSha1();
    	}
    };
    
    //将object对应的源文件写入本地git仓库
    public void writeWithFile() throws Exception{
        try(BufferedInputStream in = new BufferedInputStream(new FileInputStream(this.sourceFile));
            BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(this.objectFile))){
            int r=0;
            while((r=in.read())!=-1){
                out.write((byte)r);
            }
        }
    }
    
    //将tree及commit中的value写入本地git仓库
    public void writeWithChars()throws Exception{
    	objectFile=new File(objectPath+"\\"+this.key);
    	if(!objectFile.exists()) {
    		PrintWriter pw=new PrintWriter(objectFile);
    		pw.print(this.value);
    		pw.close();
    	}
    }
    
    //获取Object的key值
    public String getKey() {
    	return this.key;
    }
    
    //获取Object的value
    public String getValue() {
    	return this.value;
    }

}