package HashGit;

import java.io.IOException;
import java.util.Scanner;

public class Command {
    public static void main(String[] args) throws Exception {
    	Repository repository=new Repository("repo","C:\\Users\\DoNotFly\\Documents\\test");
    	repository.initCommit();
    }
}

/*import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedHashMap;

public class gitCommand extends KeyValue{
    private final String filePath;
    private VersionManagement folder;

    private gitCommand(File inputFile, String storagePath, String Path) throws IOException {
        super(inputFile, storagePath);
        filePath = Path;
    }

    private void commandLine(String[] args) throws Exception {
        if (args[0].equals("init")){
            init();
        }
        else if (args[0].equals("commit")){
            // �ύһ��commit
            folder = new VersionManagement(filePath);
            folder.commit();
            System.out.println("Commit ���");
        }
        else if (args[0].equals("log")){
            // �鿴�ύ��¼
            folder = new VersionManagement(filePath);
            LinkedHashMap commitLog = folder.commit();
            System.out.println("Commit ID��" + commitLog.keySet());
        }
        else if (args[0].equals("rollback")){
            rollBack rb = new rollBack(filePath);
        }
        else if (args[0].equals("branch")){
            if (args.length==1){
                Branch();
            }
            else{
                createBranch(args[1]);
            }
        }
        else if (args[0].equals("checkout")){
            if (checkoutBranch(args[1])){
                System.out.println("�л���֧�ɹ�");
            }else{
                System.out.println("�л���֧ʧ��");
            }
        }else{
            System.out.println("��������ȷ��ָ�");
        }
    }

    private void init() throws Exception {
        folder = new VersionManagement(filePath);
        System.out.println("��ʼ���ɹ���");
    }

    public static void main(String[] args) throws Exception {
        String currentPath = new java.io.File( "." ).getCanonicalPath();
        String vmPath = currentPath + "\\.versionManagement";
        gitCommand task = new gitCommand(new File(currentPath), vmPath, currentPath);

        if (args.length == 0)
            System.out.println("������ָ�");
        else
            task.commandLine(args);
    }
}*/
