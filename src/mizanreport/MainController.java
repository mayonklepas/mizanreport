/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mizanreport;

import java.awt.Dialog;
import java.awt.Frame;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JDialog;
import mizanreport.popupview.LaporannamaView;

/**
 *
 * @author Minami
 */
public class MainController {

    Mainview pane = new Mainview();

    public MainController() {
        Staticvar.MAIN_PANE = pane.mainpane;
        pane.setExtendedState(Frame.MAXIMIZED_BOTH);
        pane.setLocationRelativeTo(null);
        pane.setVisible(true);
        pane.pkeuangan.setVisible(true);
        pane.pbukubesar.setVisible(false);
        pane.ppembelian.setVisible(false);
        pane.ppenjualan.setVisible(false);
        pane.ppersediaan.setVisible(false);
        pane.plainnya.setVisible(false);
        hideshowpanel();
        laporannama();
    }

    private void hideshowpanel() {

        pane.paksikeuangan.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                pane.pkeuangan.setVisible(true);
                pane.pbukubesar.setVisible(false);
                pane.ppembelian.setVisible(false);
                pane.ppenjualan.setVisible(false);
                pane.ppersediaan.setVisible(false);
                pane.plainnya.setVisible(false);

            }

        });

        pane.paksibukubesar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                pane.pkeuangan.setVisible(false);
                pane.pbukubesar.setVisible(true);
                pane.ppembelian.setVisible(false);
                pane.ppenjualan.setVisible(false);
                pane.ppersediaan.setVisible(false);
                pane.plainnya.setVisible(false);

            }

        });

        pane.paksipembelian.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                pane.pkeuangan.setVisible(false);
                pane.pbukubesar.setVisible(false);
                pane.ppembelian.setVisible(true);
                pane.ppenjualan.setVisible(false);
                pane.ppersediaan.setVisible(false);
                pane.plainnya.setVisible(false);

            }

        });

        pane.paksipenjualan.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                pane.pkeuangan.setVisible(false);
                pane.pbukubesar.setVisible(false);
                pane.ppembelian.setVisible(false);
                pane.ppenjualan.setVisible(true);
                pane.ppersediaan.setVisible(false);
                pane.plainnya.setVisible(false);

            }

        });

        pane.paksipersediaan.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                pane.pkeuangan.setVisible(false);
                pane.pbukubesar.setVisible(false);
                pane.ppembelian.setVisible(false);
                pane.ppenjualan.setVisible(false);
                pane.ppersediaan.setVisible(true);
                pane.plainnya.setVisible(false);

            }

        });

        pane.paksilainya.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                pane.pkeuangan.setVisible(false);
                pane.pbukubesar.setVisible(false);
                pane.ppembelian.setVisible(false);
                pane.ppenjualan.setVisible(false);
                pane.ppersediaan.setVisible(false);
                pane.plainnya.setVisible(true);

            }

        });

    }

    private void laporannama() {
        pane.llaporannama.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JDialog jd = new JDialog(new Mainview());
                jd.setTitle("Laporan Data Nama");
                jd.add(new LaporannamaView());
                jd.pack();
                jd.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
                jd.setLocationRelativeTo(null);
                jd.setVisible(true);
                jd.toFront();
            }

        });
    }

}
