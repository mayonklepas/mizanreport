/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mizanreport;

import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author Minami
 */
public class PopupcariController {

    CrudHelper ch = new CrudHelper();
    ArrayList<String> idlist = new ArrayList<>();
    ArrayList<String> lsdata = new ArrayList();
    ArrayList<Integer> lssize = new ArrayList();
    DefaultTableModel dtm = new DefaultTableModel();
    Popupcari pane;

    public PopupcariController(Popupcari pane, String tipe, String page, String header) {
        Staticvar.isupdate = false;
        this.pane = pane;
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JDialog jdin = (JDialog) pane.getRootPane().getParent();
                jdin.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent e) {
                        pane.btutup.doClick();
                    }

                });
            }
        });
        pane.lheader.setText(header);
        loadheader(tipe);
        if (!Staticvar.sfilter.equals("")) {
            loadwithval(page);
        } else {
            loaddata(page);
        }
        loaddatadetail(page);
        selectdata();
        oncarienter();
        //selectid();
        onfocusbykey();
        oke();
        tutup();
        callokebyenter();
    }

    private void loadwithval(String page) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                pane.tcari.requestFocus();
                pane.tcari.setText(Staticvar.sfilter);
                loaddatadetailraw(page);
            }
        });
    }

    private void onfocusbykey() {
        pane.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        pane.getInputMap().put(KeyStroke.getKeyStroke("UP"), "up");
        pane.getActionMap().put("up", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pane.tabledata.requestFocus();
                pane.tabledata.changeSelection(0, 0, false, false);
                enablebutton();
            }
        });

        pane.tcari.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        pane.tcari.getInputMap().put(KeyStroke.getKeyStroke("UP"), "up");
        pane.tcari.getActionMap().put("up", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pane.tabledata.requestFocus();
                pane.tabledata.changeSelection(0, 0, false, false);
                enablebutton();
            }
        });

        pane.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        pane.getInputMap().put(KeyStroke.getKeyStroke("DOWN"), "down");
        pane.getActionMap().put("down", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pane.tabledata.requestFocus();
                pane.tabledata.changeSelection(0, 0, false, false);
                enablebutton();
            }
        });

        pane.tcari.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        pane.tcari.getInputMap().put(KeyStroke.getKeyStroke("DOWN"), "down");
        pane.tcari.getActionMap().put("down", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pane.tabledata.requestFocus();
                pane.tabledata.changeSelection(0, 0, false, false);
                enablebutton();
            }
        });

        KeyAdapter ka = new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {

                pane.tcari.setText(String.valueOf(e.getKeyChar()));
            }

        };
        //pane.tabledata.addKeyListener(ka);

        String keyholdnumeric[] = {"1", "2", "3", "4", "5", "6", "7", "8", "9",
            "0", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p",
            "q", "r", "s", "t", "u", "p", "w", "x", "y", "z", "A", "B", "C", "D",
            "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R",
            "S", "T", "U", "P", "W", "X", "Y", "Z", ",", "."};
        for (int i = 0; i < keyholdnumeric.length; i++) {
            int j = i;
            pane.tabledata.getInputMap().put(KeyStroke.getKeyStroke(keyholdnumeric[i]), "fokus");
            pane.tabledata.getActionMap().put("fokus", new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    pane.tcari.setText(e.getActionCommand());
                    pane.tcari.requestFocus();
                }
            });
        }

        pane.tabledata.getInputMap().put(KeyStroke.getKeyStroke("BACK_SPACE"), "fokusaja");
        pane.tabledata.getActionMap().put("fokusaja", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pane.tcari.requestFocus();
            }
        });

    }

    private void loadheader(String tipe) {
        try {
            pane.tabledata.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
            pane.tabledata.setModel(dtm);
            TableColumnModel tcm = pane.tabledata.getColumnModel();
            pane.tabledata.setDefaultEditor(Object.class, null);
            String dataheader = ch.getheaderpopups();
            JSONParser jpheader = new JSONParser();
            Object objheader = jpheader.parse(dataheader);
            JSONObject joheader = (JSONObject) objheader;
            JSONArray jaheader = (JSONArray) joheader.get(tipe);
            //perulangan mengambil header
            for (int i = 0; i < jaheader.size(); i++) {
                JSONObject jodata = (JSONObject) jaheader.get(i);
                JSONArray jaaray = (JSONArray) jodata.get("key");
                if (jaaray.get(2).equals("1")) {
                    dtm.addColumn(jaaray.get(1));
                    lsdata.add(String.valueOf(jaaray.get(0)));
                    lssize.add(Integer.parseInt(String.valueOf(jaaray.get(3))));
                }
            }

            for (int i = 0; i < lssize.size(); i++) {
                Double wd = d.getWidth() - 344;
                int wi = (lssize.get(i) * wd.intValue()) / 100;
                tcm.getColumn(i).setMinWidth(wi);
                tcm.getColumn(i).setMaxWidth(wi);
            }
        } catch (ParseException ex) {
            Logger.getLogger(PopupcariController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void loaddata(String page) {
        cleardata();
        disablebutton();
        dtm.getDataVector().removeAllElements();
        dtm.fireTableDataChanged();
        SwingWorker worker = new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {
                //pane.indi.setVisible(true);
                JSONParser jpdata = new JSONParser();
                Object objdata = jpdata.parse(ch.getdatas(page));
                System.out.println(objdata);
                JSONArray jadata = (JSONArray) objdata;
                dtm.setRowCount(0);
                for (int i = 0; i < jadata.size(); i++) {
                    JSONObject joindata = (JSONObject) jadata.get(i);
                    Object[] objindata = new Object[lsdata.size()];
                    idlist.add(String.valueOf(joindata.get("id")));
                    for (int j = 0; j < objindata.length; j++) {
                        objindata[j] = joindata.get(lsdata.get(j));
                    }
                    dtm.addRow(objindata);
                }
                return null;
            }

            @Override
            protected void done() {
                //pane.indi.setVisible(false);
                pane.tabledata.setModel(dtm);
                disablebutton();
                pane.tcari.setText("");
                pane.tcari.requestFocus();
            }

        };
        worker.execute();

    }

    private void loaddatadetailraw(String page) {
        cleardata();
        disablebutton();
        dtm.getDataVector().removeAllElements();
        dtm.fireTableDataChanged();
        SwingWorker worker = new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {
                //pane.indi.setVisible(true);
                JSONParser jpdata = new JSONParser();
                String param = String.format("cari=%s", pane.tcari.getText());
                Object objdata = jpdata.parse(ch.getdatadetails(page.replace("daftar", "cari"), param));
                JSONArray jadata = (JSONArray) objdata;
                dtm.setRowCount(0);
                for (int i = 0; i < jadata.size(); i++) {
                    JSONObject joindata = (JSONObject) jadata.get(i);
                    Object[] objindata = new Object[lsdata.size()];
                    idlist.add(String.valueOf(joindata.get("id")));
                    for (int j = 0; j < objindata.length; j++) {
                        objindata[j] = joindata.get(lsdata.get(j));
                    }
                    dtm.addRow(objindata);
                }
                return null;
            }

            @Override
            protected void done() {
                //pane.indi.setVisible(false);
                pane.tabledata.setModel(dtm);
                disablebutton();

            }

        };
        worker.execute();

    }

    private void loaddatadetail(String page) {
        pane.tcari.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    loaddatadetailraw(page);
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
    }

    private void selectdata() {
        pane.tabledata.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (e.getValueIsAdjusting()) {
                    enablebutton();
                    //System.out.println(id);
                }

            }
        });
    }

    private void cleardata() {
        idlist.clear();
        Staticvar.ids = "";
    }

    private void oncarienter() {
        pane.tcari.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                //pane.tcari.setText("");
            }

            @Override
            public void focusLost(FocusEvent e) {
                //pane.tcari.setText("Cari Data");
            }
        });
    }

    private void disablebutton() {
        pane.bedit.setEnabled(false);
        pane.bhapus.setEnabled(false);
    }

    private void enablebutton() {
        pane.bedit.setEnabled(true);
        pane.bhapus.setEnabled(true);
    }

    private void selectid() {
        pane.tabledata.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int i = pane.tabledata.getSelectedRow();
                if (i >= 0) {
                    Staticvar.resid = idlist.get(i);
                    Staticvar.reslabel = String.valueOf(pane.tabledata.getValueAt(i, 1));
                }
            }

        });
    }

    private void callokebyenter() {
        pane.tabledata.getInputMap().put(KeyStroke.getKeyStroke("ENTER"), "enter");
        pane.tabledata.getActionMap().put("enter", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pane.bok.doClick();
            }
        });
    }

    private void oke() {
        pane.bok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Staticvar.isupdate = true;
                int i = pane.tabledata.getSelectedRow();
                Staticvar.resid = idlist.get(i);
                Staticvar.resvalue = String.valueOf(pane.tabledata.getValueAt(i, 0));
                Staticvar.reslabel = String.valueOf(pane.tabledata.getValueAt(i, 1));
                if (pane.tabledata.getColumnCount() >= 3) {
                    Staticvar.resvalueextended = String.valueOf(pane.tabledata.getValueAt(i, 2));
                }
                Staticvar.sfilter = "";
                JDialog jd = (JDialog) pane.getRootPane().getParent();
                jd.dispose();
            }
        });

        MouseAdapter madap = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    pane.bok.doClick();
                }
            }

        };
        pane.tabledata.addMouseListener(madap);
    }

    private void tutup() {

        pane.btutup.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Staticvar.resid = Staticvar.preid;
                Staticvar.reslabel = Staticvar.prelabel;
                Staticvar.resvalue = Staticvar.prevalue;
                if (pane.tabledata.getColumnCount() >= 3) {
                    Staticvar.resvalueextended = Staticvar.prevalueextended;
                }
                Staticvar.sfilter = "";
                JDialog jd = (JDialog) pane.getRootPane().getParent();
                jd.dispose();
            }
        });

        pane.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        pane.getInputMap().put(KeyStroke.getKeyStroke("ESCAPE"), "esc");
        pane.getActionMap().put("esc", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pane.btutup.doClick();
            }
        });

        pane.tabledata.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        pane.tabledata.getInputMap().put(KeyStroke.getKeyStroke("ESCAPE"), "esc");
        pane.tabledata.getActionMap().put("esc", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pane.btutup.doClick();
            }
        });

        pane.tcari.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        pane.tcari.getInputMap().put(KeyStroke.getKeyStroke("ESCAPE"), "esc");
        pane.tcari.getActionMap().put("esc", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pane.btutup.doClick();
            }
        });

        /*JDialog jd = (JDialog) pane.getRootPane().getParent();
        WindowAdapter wadap = new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                pane.btutup.doClick();
            }

            @Override
            public void windowClosing(WindowEvent e) {
                pane.btutup.doClick();
            }

        };
        jd.addWindowListener(wadap);*/
    }

}
