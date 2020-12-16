package HashGit;

import java.io.*;

//�汾������Object��
public abstract class GitObject {
    protected String key;
    protected String value="";
    protected File file;
    protected String path="./test/";
    
    //����value���ɶ�Ӧ��key
    public void genKey() throws Exception{
        SHA1CheckSum s = new SHA1CheckSum(this.value);
        this.key = s.getSha1();
    }
    
    //��ȡObject��keyֵ
    public String getKey() {
    	return this.key;
    }

    public void writeFile() throws Exception{
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

    public void write() throws Exception{
        File f = new File(path);
        if(!f.exists())f.mkdirs();
        PrintWriter p = new PrintWriter(this.path + this.key);
        p.print(this.value);
        p.close();
    }
    
    //��ȡObject��value
    public String getValue() {
    	return this.value;
    }
}