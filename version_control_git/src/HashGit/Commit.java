package HashGit;

public class Commit extends GitObject {
    private String parentKey;
    private String treeKey;  
    
	//¹¹Ôìº¯Êý
    public Commit(String parentKey,String treeKey) throws Exception {
    	this.parentKey=parentKey;
    	this.treeKey=treeKey;
    	if(parentKey!="") {
        	this.value+="parent"+" "+this.parentKey+"\n";
    	}
    	this.value+="tree"+" "+this.treeKey+"\n";
    	genKey();
    	writeWithChars();
    }
}
