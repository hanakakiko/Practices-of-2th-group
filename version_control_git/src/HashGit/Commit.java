package HashGit;

public class Commit extends GitObject {

	//���캯��
    public Commit(Commit lastCommit,Tree tree) throws Exception {
    	genValue(lastCommit,tree);
    	genKey();
    	writeFile();
    	writeLog(); 
    }
    
    //��һ��commit�Ĺ��캯��
    public Commit(Tree tree)throws Exception{
    	genValue(tree);
    	genKey();
    	writeFile();
    	writeLog();
    }
    
    public void genValue(Commit lastCommit,Tree tree) {
    	this.value+="tree"+" "+tree.getKey()+"\n";
    	this.value+="last commit"+" "+lastCommit.getKey()+"\n";
    }
    
    public void genValue(Tree tree) {
    	this.value+="tree"+" "+tree.getKey()+"\n";
    }
    
    public void writeLog() {
    	
    }
}
