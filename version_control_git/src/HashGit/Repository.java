package HashGit;

//Repository�࣬����ʵ�ֱ��زֿ�Ĵ������ļ�����ɾ���
public class Repository {
   private String repositoryName;
   private String repositoryPath;
   
   //���ݸ���Ŀ¼�������زֿ�
   public Repository(String name,String path) {
	   repositoryName=name;
	   repositoryPath=path+"\\"+name;
	   //��ָ��λ�ô���Ŀ¼
	   //File repoDir=new File(repositoryPath);
	   //��Ŀ¼�´���.git�ļ��У����ڴ��object��head���ļ�
   }
}
