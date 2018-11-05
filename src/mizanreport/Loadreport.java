/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mizanreport;

import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.swing.JRViewer;

/**
 *
 * @author Minami
 */
public class Loadreport implements Runnable {

    Map param;
    JasperReport jr;

    public Loadreport(JasperReport jr, Map param) {
        this.param = param;
        this.jr = jr;
    }

    @Override
    public void run() {
        try {
            JasperPrint jp = JasperFillManager.fillReport(jr, param);
            JRViewer jv = new JRViewer(jp);
            Staticvar.MAIN_PANE.removeAll();
            Staticvar.MAIN_PANE.add(jv);
            Staticvar.MAIN_PANE.revalidate();
            JDialog mainjd = (JDialog) Staticvar.POPUP_PANE.getRootPane().getParent();
            mainjd.dispose();
        } catch (JRException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
