/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mizanreport;

/**
 *
 * @author Minami
 */
public class Popupcari extends javax.swing.JPanel {

    /**
     * Creates new form Popupcari
     */
    public Popupcari(String tipe, String page, String header) {
        initComponents();
        new PopupcariController(this, tipe, page, header);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabledata = new Tablestyle(0);
        bbaru = new javax.swing.JButton();
        btutup = new javax.swing.JButton();
        bedit = new javax.swing.JButton();
        bhapus = new javax.swing.JButton();
        bok = new javax.swing.JButton();
        tcari = new javax.swing.JTextField();
        lheader = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();

        jLabel1.setText("jLabel1");

        tabledata.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tabledata);

        bbaru.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        bbaru.setText("Baru");

        btutup.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        btutup.setText("Tutup");

        bedit.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        bedit.setText("Edit");

        bhapus.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        bhapus.setText("Hapus");

        bok.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        bok.setText("Ok");

        tcari.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        tcari.setText("Cari Data");

        lheader.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        lheader.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lheader.setText("Daftar");

        jPanel1.setBackground(new java.awt.Color(153, 153, 153));
        jPanel1.setForeground(new java.awt.Color(153, 153, 153));
        jPanel1.setPreferredSize(new java.awt.Dimension(0, 1));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lheader, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 470, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 470, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(bbaru)
                        .addGap(2, 2, 2)
                        .addComponent(bedit)
                        .addGap(2, 2, 2)
                        .addComponent(bhapus)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tcari)
                        .addGap(18, 18, 18)
                        .addComponent(btutup)
                        .addGap(2, 2, 2)
                        .addComponent(bok)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lheader)
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 342, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bbaru)
                    .addComponent(btutup)
                    .addComponent(bedit)
                    .addComponent(bhapus)
                    .addComponent(bok)
                    .addComponent(tcari, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton bbaru;
    public javax.swing.JButton bedit;
    public javax.swing.JButton bhapus;
    public javax.swing.JButton bok;
    public javax.swing.JButton btutup;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JLabel lheader;
    public javax.swing.JTable tabledata;
    public javax.swing.JTextField tcari;
    // End of variables declaration//GEN-END:variables
}
