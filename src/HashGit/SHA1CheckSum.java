package HashGit;
import java.io.*;
import java.security.MessageDigest;

public class SHA1CheckSum {
    private byte[] sha1;
    private String outFile;
    
    //根据文件生成哈希值
    public SHA1CheckSum(File inFile) throws Exception{
        FileInputStream input = new FileInputStream(inFile);
        this.sha1 = Sha1Checksum(input);
    }
    //
    public static byte[] Sha1Checksum(InputStream is) throws Exception{
        byte[] buffer = new byte[1024];
        MessageDigest complete = MessageDigest.getInstance("SHA-1");
        int numRead = 0;
        do {
            numRead = is.read(buffer);
            if(numRead>0){
                complete.update(buffer, 0 , numRead);
            }
        }while(numRead!=-1);
        is.close();
        return complete.digest();
    }
    
    //根据字符串生成哈希值
    public SHA1CheckSum(String value)throws Exception{
        this.sha1 = Sha1Checksum(value);
    }
    //
    public static byte[] Sha1Checksum(String value) throws Exception{
        MessageDigest complete = MessageDigest.getInstance("SHA-1");
        complete.update(value.getBytes());
        return complete.digest();
    }

    public String getSha1(){
        outFile = "";
        int n = sha1.length;
        for(int i = 0;i< n;i++) {
            String append = Integer.toString(sha1[i] & 0xFF, 16);
            if (append.length()<2) {
                outFile = outFile + "0" + append; 
            }
            else {
                outFile += append;
            }
        }
        return outFile;
    }
}