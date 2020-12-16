package HashGit;

public class Head extends GitObject{
    public Head(String branch){
        this.key = "HEAD";
        this.value = "refs/heads/" + branch;
    }
}

