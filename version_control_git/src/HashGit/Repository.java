package HashGit;

//Repository类，用于实现本地仓库的创建和文件的增删查改
public class Repository {
   private String repositoryName;
   private String repositoryPath;
   
   //根据给定目录创建本地仓库
   public Repository(String name,String path) {
	   repositoryName=name;
	   repositoryPath=path+"\\"+name;
	   //在指定位置创建目录
	   //File repoDir=new File(repositoryPath);
	   //在目录下创建.git文件夹，用于存放object、head等文件
   }
}
