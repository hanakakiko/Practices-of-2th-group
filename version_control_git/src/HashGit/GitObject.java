package HashGit;

//版本管理工具Object类
public class GitObject {
    protected String key="";
    protected String value="";
    //protected File file;
    //protected String path="./test/";
    
    //生成value
    //public abstract void genValue(String path)throws Exception;
    
    //根据value生成对应的key
    public void genKey() throws Exception{
    	KeyValueStore.genKey(this.value);
    };
    
    //写入本地文件
    public void writeFile() throws Exception{
    	KeyValueStore.keyValueStore(this);
    }
        /*FileInputStream fileInputStream = new FileInputStream(this.file);
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
    }*/
    
    //获取Object的key值
    public String getKey() {
    	return this.key;
    }
    
    //获取Object的value
    public String getValue() {
    	return this.value;
    }
}