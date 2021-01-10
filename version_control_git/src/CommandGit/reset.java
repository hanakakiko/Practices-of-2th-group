package CommandGit;

import HashGit.*;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class reset {

    private static String repositoryPath;
    private String gitPath;
    private File log;
    private File index;
    private Head head;

    public void mReset()throws IOException {
        this.head.getBranch().mixedReset();
    }

    public void hReset() throws Exception {
        this.head.getBranch().hardReset();
    }
}
