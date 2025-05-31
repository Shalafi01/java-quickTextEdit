/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package easytextedit;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;

/**
 *
 * @author tomas
 */
public class main extends javax.swing.JFrame {

    /**
     * Creates new form main
     */
    public main() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jButtonTimestamps = new javax.swing.JButton();
        jButtonRemoveLines = new javax.swing.JButton();
        jButtonCtrlC = new javax.swing.JButton();
        jButtonUndo = new javax.swing.JButton();
        jCorreggiMaiuscole = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTextArea1.setColumns(20);
        jTextArea1.setLineWrap(true);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        jButtonTimestamps.setText("Rimuovi timestamps");
        jButtonTimestamps.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonTimestampsActionPerformed(evt);
            }
        });

        jButtonRemoveLines.setText("Rimuovi tutti gli \"a capo\"");
        jButtonRemoveLines.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRemoveLinesActionPerformed(evt);
            }
        });

        jButtonCtrlC.setText("Copia testo negli appunti");
        jButtonCtrlC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCtrlCActionPerformed(evt);
            }
        });

        jButtonUndo.setText("Undo");
        jButtonUndo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonUndoActionPerformed(evt);
            }
        });

        jCorreggiMaiuscole.setText("Correggi maiuscole");
        jCorreggiMaiuscole.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCorreggiMaiuscoleActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 540, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButtonCtrlC))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButtonTimestamps)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonUndo))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButtonRemoveLines)
                            .addComponent(jCorreggiMaiuscole))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonTimestamps)
                    .addComponent(jButtonUndo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonRemoveLines)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCorreggiMaiuscole)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                .addComponent(jButtonCtrlC)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    int undo = 0;
    String oldText[] = new String[100];
    
    public void backupText(String testo)
    {
        oldText[undo] = testo;
        undo++;
    }
    
    private void jButtonTimestampsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonTimestampsActionPerformed
        String testo = jTextArea1.getText();
        backupText(testo);
    
        // Dividi il testo in righe
        String[] righe = testo.split("\n");

        // Usa StringBuilder per creare il nuovo testo senza righe che iniziano con un numero
        StringBuilder testoFiltrato = new StringBuilder();

        for (String riga : righe)
            if (!riga.matches("^\\d{1,2}:.*"))  // Controlla se la riga NON inizia con una o due cifre seguite da ":"
                testoFiltrato.append(riga).append("\n");
            
        jTextArea1.setText(testoFiltrato.toString().trim());
    }//GEN-LAST:event_jButtonTimestampsActionPerformed

    private void jButtonRemoveLinesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRemoveLinesActionPerformed
        String testo = jTextArea1.getText();
        backupText(testo);
        testo = testo.replaceAll("\n", " ");
        jTextArea1.setText(testo);
    }//GEN-LAST:event_jButtonRemoveLinesActionPerformed

    private void jButtonCtrlCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCtrlCActionPerformed
        String testo = jTextArea1.getText();
        StringSelection selezione = new StringSelection(testo);

        // Ottieni il clipboard di sistema
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();

        // Copia il testo negli appunti
        clipboard.setContents(selezione, null);
    }//GEN-LAST:event_jButtonCtrlCActionPerformed

    private void jButtonUndoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonUndoActionPerformed
        if(undo>0)
        {
            oldText[undo] = "";
            undo--;
            jTextArea1.setText(oldText[undo]);
        }        
    }//GEN-LAST:event_jButtonUndoActionPerformed

    private void jCorreggiMaiuscoleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCorreggiMaiuscoleActionPerformed
        String testo = jTextArea1.getText();
        backupText(testo);
        StringBuilder result = new StringBuilder(testo);

        // Capitalizza la prima lettera della stringa, se necessario
        if (result.length() > 0 && Character.isLowerCase(result.charAt(0))) {
            result.setCharAt(0, Character.toUpperCase(result.charAt(0)));
        }

        for (int i = 1; i < result.length(); i++) {
            // Se troviamo un punto/esclamativo/interrogativo
            if (result.charAt(i - 1) == '.' || result.charAt(i - 1) == '!' || result.charAt(i - 1) == '?') {
                int j = i;

                // Saltiamo tutti gli spazi e caratteri di nuova riga dopo la punteggiatura
                while (j < result.length() && 
                      (result.charAt(j) == ' ' || result.charAt(j) == '\n' || result.charAt(j) == '\r')) {
                    j++;
                }

                // Se il prossimo carattere valido è una lettera minuscola, la rendiamo maiuscola
                if (j < result.length() && Character.isLowerCase(result.charAt(j))) {
                    result.setCharAt(j, Character.toUpperCase(result.charAt(j)));
                }
            }
        }

        jTextArea1.setText(result.toString());
    }//GEN-LAST:event_jCorreggiMaiuscoleActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new main().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonCtrlC;
    private javax.swing.JButton jButtonRemoveLines;
    private javax.swing.JButton jButtonTimestamps;
    private javax.swing.JButton jButtonUndo;
    private javax.swing.JButton jCorreggiMaiuscole;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    // End of variables declaration//GEN-END:variables
}
