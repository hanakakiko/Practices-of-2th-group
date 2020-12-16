package HashGit;

import java.io.*;

//�汾������Object��
public abstract class GitObject {
    protected String key;
    protected String value="";
    protected File file;
    
    //����value���ɶ�Ӧ��key
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
    
    //��ȡObject��keyֵ
    public String getKey() {
    	return this.key;
    }
    
    //��ȡObject��value
    public String getValue() {
    	return this.value;
    }
}