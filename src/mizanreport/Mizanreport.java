/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mizanreport;

import com.sun.java.swing.plaf.windows.WindowsLookAndFeel;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import net.infonode.gui.laf.InfoNodeLookAndFeel;
import org.apache.log4j.BasicConfigurator;

/**
 *
 * @author Minami
 */
public class Mizanreport {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        BasicConfigurator.configure();
        try {
            UIManager.setLookAndFeel(new InfoNodeLookAndFeel());
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(Mizanreport.class.getName()).log(Level.SEVERE, null, ex);
        }
        MainController mc = new MainController();
    }

}
