package HashGit;

import java.io.*;

//版本管理工具Object类
public abstract class GitObject {
    protected String key;
    protected String value="";
    protected File file;
    
    //根据value生成对应的key
    public void genKey() throws Exception{
        SHA1CheckSum s = new SHA1CheckSum(this.value);
        this.key = s.getSha1();
    }
    
    public void write() throws Exception{
        FileInputStream fileInputStream = new FileInputStream(this.file);
        FileOutputStream output = new FileOutputStream(this.key);
        byte[] buffer = new byte[1024];
        int numRead = 0;
        do {
            numRead = fileInputStream.read(buffer);
            if(numRead > 0){
                output.write(buffer);
            }
        }while(numRead!=-1);
        fileInputStream.close();
        output.close();
    };
    
    //获取Object的key值
    public String getKey() {
    	return this.key;
    }
    
    //获取Object的value
    public String getValue() {
    	return this.value;
    }
}