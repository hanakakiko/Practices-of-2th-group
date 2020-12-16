package HashGit;

public class Branch extends GitObject{
    public Branch(String branch,String commit){
        this.path += "refs/heads/";  // prepare the path to write()
        this.key =  branch; // write() destination
        this.value = commit; // branch content is the commit hashcode
    }
}
