//这个类是实现读取命令行输入的命令并解析的，解析到相应命令会调用Commandgit里的相应操作类完成操作

import CommandGit.*;
import org.apache.commons.cli.*;

import java.util.Arrays;

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
            else if(cmd.equals("reset")){
                String[] newArgs2 = Arrays.copyOfRange(args, 1, args.length);
                reset(newArgs2);
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
        public static void reset(String[] args) throws Exception {
            Options options = new Options();
            CommandLineParser parser = new DefaultParser();
            options.addOption(new Option("s", false, "soft"));
            options.addOption(new Option("m", false, "mixed"));
            options.addOption(new Option("h", false, "hard"));
            CommandLine cmd = parser.parse(options, args);
            if (cmd.hasOption("s")) {
                new reset().sReset();
            } else if (cmd.hasOption("h")) {
                new reset().hReset();
            } else {
                new reset().mReset();
            }
        }
}
