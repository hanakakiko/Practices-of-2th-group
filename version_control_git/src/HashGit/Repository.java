package HashGit;

import java.io.File;
import java.io.IOException;

//Repository�࣬����ʵ�ֱ��زֿ�Ĵ������ļ�����ɾ���
public class Repository {
   private String repositoryName;
   private String repositoryPath;
   private Head head;
   
   //���ݸ���Ŀ¼�����ֿ����
   public Repository(String name,String path) {
	   repositoryName=name;
	   repositoryPath=path+"/"+repositoryName;
	   KeyValueStore.objectPath=repositoryPath+"/.git"+"/objects";
	   head=new Head();
   }
   
   //����Ŀ¼Ϊ��ʱ�����ݸ���Ŀ¼�����ղֿ�
   public void createRepository() throws IOException {
	   //��ָ��λ�ô���Ŀ¼
	   File repoDir=new File(repositoryPath);
	   repoDir.mkdir();
	   //��Ŀ¼�´���.git�ļ��У����ڴ��object��head���ļ�
	   File gitDir=new File(repositoryPath+"/.git");
	   gitDir.mkdir();
	   //����objects�ļ����Դ��GitObject�ļ�
	   File objectsDir=new File(repositoryPath+"/.git"+"/objects");
	   objectsDir.mkdir();
	   //����heads�ļ����Դ��branch
	   File headsDir=new File(repositoryPath+"/.git"+"/heads");
	   headsDir.mkdir();
	   //����src�ļ����Դ�ű��زֿ��ļ�
	   File srcDir=new File(repositoryPath+"/src");
	   srcDir.mkdir();
	   //����log�ļ�
	   File log=new File(repositoryPath+"/.git/"+"log");
	   log.createNewFile();
   }
   
   //���ɵ�һ��Commit
   public void initCommit() throws Exception {
	   Tree tree=new Tree(repositoryPath+"/src");
	   Commit commit=new Commit(tree);
	   //����main��֧�����浱ǰ��Ϣ
	   Branch main=new Branch(repositoryPath+"/.git"+"/heads","main");
	   main.createBranch(repositoryPath+"/.git"+"/heads", commit.getKey());
	   //����head����д�뵱ǰ��֧��Ϣ
	   head.setHead(repositoryPath+"/.git"+"/heads",repositoryPath+"/HEAD",main.getBranchName());
   }
   
   //����ļ�
   public void addFile(String fileName) throws IOException {
	   File file=new File(repositoryPath+"/src",fileName);
	   file.createNewFile();
   }
   
   //ɾ���ļ�
   public void deleteFile() {}
   
   //�����ļ�(��Ҫ�õ�Myers diff�㷨�����ڲ�Ҫ��)
   public void modifyFile() {}
   
   //�����µ�commit
   public void createCommit() throws Exception{
	   
   }
}
