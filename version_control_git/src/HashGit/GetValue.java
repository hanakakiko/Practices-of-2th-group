//根据key找value
package HashGit;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class GetValue {
	private String value;
	public GetValue(String path,String key) throws Exception {
		this.value = Searchvalue(path,key);
	}
	public static String Searchvalue(String path,String key) throws Exception {
		File dir = new File(path);
		File[] fs = dir.listFiles();
		String name = key;
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
}
