//这个类是实现读取命令行输入的命令并解析的，解析到相应命令会调用Commandgit里的相应操作类完成操作

import CommandGit.*;

public class Commandgit {

        public static void main(String args[]){
            try {
                String cmd = args[0];

            if(cmd.equals("add")){
                new add().add(args[1]);
            }

            else if(cmd.equals("branch")){
                if(args.length==1) {
                    new branch().branch(null);
                    return;
                }
                new branch().branch(args[1]);
            }
            else if(cmd.equals("checkout")){
                new branch().checkout(args[1]);
            }
            else if(cmd.equals("commit")) {
                new commit().commit();
            }
                //else
                if(cmd.equals("init")){
                    new init().init(System.getProperty("user.dir"));
                }
            else if(cmd.equals("log")){
                new log().log();
            }
            else if(cmd.equals("mReset")){
                new reset().mReset();
            }
            else if(cmd.equals("hReset")){
                new reset().hReset();
            }
            /*
            else if(cmd.equals("diff")){
                diff();
            }
            */

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        /*public static void init(String path){
            try {
                new init().init(path);
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }*/
}
