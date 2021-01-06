package HashGit;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.util.Scanner;

//KeyValueStore�࣬����ʵ��key-value�Ĵ洢
public class KeyValueStore {
	public static String objectPath=""; //���ڴ��object�ļ��洢·��
	
	//����object���·��
	public static void setPath(String path) {
	    objectPath=path;	
	}
	
	//����Blob�����Ӧ���ļ���������keyֵ
    public static String genKey(File inFile) throws Exception{
        FileInputStream input = new FileInputStream(inFile);
        byte[] sha1 = Sha1Checksum(input);
        String result="";
        for(int i=0;i<sha1.length;i++) {
        	result+=Integer.toString(sha1[i]&0xFF,16);
        }
        return result;
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
    
    //����value�ַ�������keyֵ
    public static String genKey(String value)throws Exception{
        MessageDigest complete = MessageDigest.getInstance("SHA-1");
        complete.update(value.getBytes());
        byte[] sha1=complete.digest();    
        String result="";
        for(int i=0;i<sha1.length;i++) {
        	result+=Integer.toString(sha1[i]&0xFF,16);
        }
        return result;
    }
    
    //ʵ��key-value�洢
    public static void keyValueStore(GitObject object) throws IOException {
    	try {
    		String filename=KeyValueStore.objectPath+"/"+object.getKey();
    		DataOutputStream output=new DataOutputStream(new FileOutputStream(filename,true));
    		output.writeChars(object.getValue());
    		output.close();
    	}
    	catch(IOException e) {
    		e.printStackTrace();
    	}
    }
    
    //����keyֵ�ҵ���Ӧ��value
    public static String searchValue(String key)throws IOException {
    	String value="";
    	Scanner input=new Scanner(new File(KeyValueStore.objectPath+"/"+key));
    	while(true) {
    		String temp=input.nextLine();
    		if(temp=="") break;
    		value+=temp;
    	}
    	input.close();
    	/*try (DataInputStream input=
    			new DataInputStream(new FileInputStream(KeyValueStore.objectPath+"/"+key))){
    		while(true){
    			value+=input.readUTF();
    		}
    	}
    	catch(EOFException e1) {
    		System.out.println("All data were read!");
    	}
    	catch(IOException e2) {
    		e2.printStackTrace();
    	}*/
    	return value;
    }
}
    
