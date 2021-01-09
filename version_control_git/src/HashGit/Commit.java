package HashGit;

public class Commit extends GitObject {
	//构造函数
    public Commit(Commit lastCommit,Tree tree) throws Exception {
    	genValue(lastCommit,tree);
    	genKey();
    	writeFile();
    }
    
    //第一次commit的构造函数
    public Commit(Tree tree)throws Exception{
    	genValue(tree);
    	genKey();
    	writeFile();
    }
    
    public void genValue(Commit lastCommit,Tree tree) {
    	this.value+="tree"+" "+tree.getKey()+"\n";
    	this.value+="last commit"+" "+lastCommit.getKey()+"\n";
    }
    
    public void genValue(Tree tree) {
    	this.value+="tree"+" "+tree.getKey()+"\n";
    }
}
