/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mizanreport.popupcontroller;

import com.jaspersoft.webservice.data.WebServiceDataAdapterService;
import com.jaspersoft.webservice.data.enumerations.LanguageType;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;
import mizanreport.CrudHelper;
import mizanreport.Loadreport;
import mizanreport.MainController;
import mizanreport.Mainview;
import mizanreport.Popupcari;
import mizanreport.Staticvar;
import mizanreport.popupview.LaporannamaView;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.swing.JRViewer;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 *
 * @author Minami
 */
public class LaporannamaController {

    LaporannamaView pane;
    //JDialog jd = new JDialog(new Mainview());
    String valklasifikasi = "", tipe = "-1", golongan = "";
    ArrayList<golonganentity> lsgolongan = new ArrayList<>();
    CrudHelper ch = new CrudHelper();

    public LaporannamaController(LaporannamaView pane) {
        this.pane = pane;
        /*jd.setUndecorated(true);
        jd.add(new Progressform());
        jd.pack();
        jd.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        jd.setLocation((int) d.getWidth() / 2 - (int) Staticvar.MAIN_PANE.getPreferredSize().getWidth() / 2,
                (int) d.getHeight() / 2 - (int) Staticvar.MAIN_PANE.getPreferredSize().getHeight() / 2);*/
        //pane.cmbtipe.setEditable(true);
        //pane.cmbgolongan.setEditable(true);
        pane.edklasifikasi.setText("-- Semua --");
        pane.cmbtipe.setFocusable(false);
        loadgolongan("0");
        cmbcontrol();
        cariklasifikasi();
        generate();
    }

    private void cariklasifikasi() {
        pane.bcariklasifikasi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Staticvar.sfilter = "";
                Staticvar.preid = valklasifikasi;
                Staticvar.prelabel = pane.edklasifikasi.getText();
                JDialog jd = new JDialog(new Mainview());
                jd.add(new Popupcari("daftarklasifikasinama", "popupdaftarklasifikasinama", "Daftar Klasifikasi"));
                jd.pack();
                jd.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
                jd.setLocationRelativeTo(null);
                jd.setVisible(true);
                jd.toFront();
                valklasifikasi = Staticvar.resid;
                pane.edklasifikasi.setText(Staticvar.reslabel);
            }
        });
    }

    private void loadgolongan(String tipe) {
        pane.cmbgolongan.removeAllItems();
        lsgolongan.clear();
        try {
            JSONParser jpdata = new JSONParser();
            Object rawobjdata = jpdata.parse(ch.getdatas("dm/daftargolongan?tipe=" + tipe));
            System.out.println(rawobjdata);
            JSONArray ja = (JSONArray) rawobjdata;
            lsgolongan.add(new golonganentity("-1", "-- Semua --"));
            for (int i = 0; i < ja.size(); i++) {
                JSONObject jo = (JSONObject) ja.get(i);
                lsgolongan.add(new golonganentity(String.valueOf(jo.get("id")),
                        String.valueOf(jo.get("nama"))));

            }

            for (int i = 0; i < lsgolongan.size(); i++) {
                pane.cmbgolongan.addItem(lsgolongan.get(i).getNama_golongan());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void cmbcontrol() {
        pane.cmbtipe.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JComboBox jc = (JComboBox) e.getSource();
                int indexcmb = jc.getSelectedIndex();
                if (indexcmb == 0) {
                    tipe = "-1";
                } else if (indexcmb == 1) {
                    tipe = "0";
                } else if (indexcmb == 2) {
                    tipe = "1";
                } else if (indexcmb == 3) {
                    tipe = "2";
                }
                loadgolongan(tipe);
            }
        });

    }

    private void generate() {
        pane.btgen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    pane.btgen.setText("Sedang Memuat Data...");
                    pane.btgen.setEnabled(false);
                    //JDialog mainjd = (JDialog) pane.getRootPane().getParent();
                    //mainjd.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
                    JasperReport jr = (JasperReport) JRLoader.loadObject(new File("laporan/Kasmasuksatu.jasper"));
                    Map<String, Object> param = new HashMap<>();
                    param.put(WebServiceDataAdapterService.URI_KEY, Staticvar.MAIN_URL);
                    param.put(WebServiceDataAdapterService.VERB_KEY, "get");
                    param.put(WebServiceDataAdapterService.LANGUAGE_KEY, LanguageType.JSONQL);
                    param.put(WebServiceDataAdapterService.AUTH_TYPE_KEY, "none");
                    param.put("tipe", tipe);
                    param.put("golongan", lsgolongan.get(pane.cmbgolongan.getSelectedIndex()).getId_golongan());
                    if (pane.edklasifikasi.getText().equals("-- Semua --")) {
                        valklasifikasi = "-1";
                    }
                    param.put("klasifikasi", valklasifikasi);
                    Staticvar.POPUP_PANE = pane;
                    new Thread(new Loadreport(jr, param)).start();
                } catch (JRException ex) {
                    Logger.getLogger(LaporannamaController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    public class golonganentity {

        String id_golongan, nama_golongan;

        public golonganentity(String id_golongan, String nama_golongan) {
            this.id_golongan = id_golongan;
            this.nama_golongan = nama_golongan;
        }

        public String getId_golongan() {
            return id_golongan;
        }

        public void setId_golongan(String id_golongan) {
            this.id_golongan = id_golongan;
        }

        public String getNama_golongan() {
            return nama_golongan;
        }

        public void setNama_golongan(String nama_golongan) {
            this.nama_golongan = nama_golongan;
        }

    }
}
