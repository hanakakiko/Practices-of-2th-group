package hash;
//任务一
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class hash_1_1 {
	public static void SHA1Checksum(File fl) throws Exception {
		FileInputStream is = new FileInputStream(fl);
		byte[] buffer = new byte[1024];
		MessageDigest complete = MessageDigest.getInstance("Sha-1");
		int numRead = 0;
		do {
			numRead = is.read(buffer);
			if(numRead >0)
				complete.update(buffer,0,numRead);
		}while(numRead != -1);
		is.close();
		byte s[] = complete.digest();
		String result = "";
		for(int i=0;i<s.length;i++) {
			result+=Integer.toString(s[i]&0xFF,16);
		}
		
		//创建文件，文件名为object-key，文件内容为value
		BufferedWriter object = new BufferedWriter(new FileWriter(fl.getParentFile().getAbsolutePath()+File.separator+"objects-"+result+".txt"));
		//BufferedWriter object = new BufferedWriter(new FileWriter(fl.getParentFile().getAbsolutePath()+File.separator+result));
		BufferedReader bufferedReader = new BufferedReader(new FileReader(fl));
        String strLine = null;
        int lineCount = 1;
        while((strLine = bufferedReader.readLine())!=null){
            //System.out.println(strLine);
            lineCount++;
            object.write(strLine+"\r\n");
        }
        bufferedReader.close();
		object.close();
	}
	
	//给定key值，找value
	public static String Searchvalue(String path,String key) throws Exception {
		File dir = new File(path);
		File[] fs = dir.listFiles();
		String name = "objects-"+key+".txt";
		//String name = key;
		String value;
		for(int i=0;i<fs.length;i++) {
			if(fs[i].getName().toString().equals(name)) {
				//System.out.println(fs[i].getName());
				if(fs[i].isFile()) {
					//System.out.println(fs[i].getName());
					//File file = new File(fs[i].getName());
					BufferedReader bfRead = new BufferedReader(new FileReader(fs[i]));
			        String strLine = null;
			        int lineCount = 1;
			        StringBuilder sb = new StringBuilder();
			        while((strLine = bfRead.readLine())!=null){
			        	sb.append(strLine+"\r\n");
			            lineCount++;
			        }
			        value = sb.toString();
			        //System.out.println(value);
			        bfRead.close();
			        return value;
				}
			}
		}
		String warn = "key不存在";
		return warn;
	}

	public static void main(String[] args) throws Exception {
		System.out.println("请输入文件：");
		Scanner input = new Scanner(System.in);
		String path = input.next();
		File fl = new File(path);
		SHA1Checksum(fl);
		System.out.println("请输入路径：");
		System.out.println("请输入key值：");
		String path1 = input.next();
		String key1 = input.next();
		String value = Searchvalue(path1,key1);
		System.out.println(value);
	}
}
