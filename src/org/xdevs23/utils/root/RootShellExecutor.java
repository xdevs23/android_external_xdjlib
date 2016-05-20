package org.xdevs23.utils.root;

import java.io.DataOutputStream;
import java.io.IOException;
import java.lang.Process;
import java.lang.Runtime;

public class RootShellExecutor {
    
    private RootShellExecutor() {
        // Empty private constructor
    }
    
    /**
     * Execute a command using SuperUser permissions
     */
    public static void execSu(String cmd)
        throws InterruptedException, IOException {
        execSuMultiple(cmd);
    }
    
    /**
     * Execute a command using SuperUser permissions and catch exceptions.
     * Return true if success, false if not.
     */ 
    public static boolean execSuSafe(String... cmds) {
        try {
            execSuMultiple(cmds);
            return true;
        } catch(Exception ex) {
            return false;
        }
    }
    
    /** 
     * Execute multiple commands using SuperUser permissions and catch exceptions.
     * Return true if success, false if not.
     */
    public static void execSuMultiple(String... cmds)
            throws InterruptedException, IOException {
        Process su = Runtime.getRuntime().exec("su");
        DataOutputStream os = new DataOutputStream(su.getOutputStream());
        for ( String s : cmds) {
            os.writeBytes(s + "\n");
            os.flush();
        }
        os.writeBytes("exit\n");
        os.flush();
        su.waitFor();
    }
    
}
