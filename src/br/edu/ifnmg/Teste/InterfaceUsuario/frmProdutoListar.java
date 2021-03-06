/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.Teste.InterfaceUsuario;

import br.edu.ifnmg.Teste.DataAccess.ProdutoDAO;
import br.edu.ifnmg.Teste.DomainModel.Produto;
import java.util.List;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

/**
 *
 * @author petronio
 */
public class frmProdutoListar extends javax.swing.JInternalFrame {
    
    ProdutoDAO dao;

    /**
     * Creates new form frmProdutoListar
     */
    public frmProdutoListar() {
        initComponents();
        dao = new ProdutoDAO();
        
        List<Produto> produtos = dao.listarTodos();
        
        preencheTabela(produtos);
    }
    
    private void preencheTabela(List<Produto> lista) {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Id");
        model.addColumn("Nome");
        model.addColumn("Valor");
        
        for (Produto p : lista) {
            Vector valores = new Vector();
            valores.add(0,p.getId());
            valores.add(1,p.getNome());
            valores.add(2,p.getValor());
            model.addRow(valores);
        }
        tblListagem.setModel(model);
        tblListagem.repaint();
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblListagem = new javax.swing.JTable();
        txtFiltro = new javax.swing.JTextField();
        btnFiltrar = new javax.swing.JButton();

        setClosable(true);
        setForeground(java.awt.Color.lightGray);
        setMaximizable(true);
        setResizable(true);
        setTitle("Listagem de Produtos");

        tblListagem.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tblListagem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblListagemMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblListagem);

        btnFiltrar.setText("Filtrar");
        btnFiltrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFiltrarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, 406, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnFiltrar, javax.swing.GroupLayout.DEFAULT_SIZE, 141, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnFiltrar))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 389, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnFiltrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFiltrarActionPerformed
        Produto p = new Produto();
        p.setNome(txtFiltro.getText());
        
        List<Produto> lista = dao.buscar(p);
        
        preencheTabela(lista);
    }//GEN-LAST:event_btnFiltrarActionPerformed

    private void tblListagemMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblListagemMouseClicked
        Object valor = tblListagem.getValueAt( tblListagem.getSelectedRow(), 0);
        Produto p = dao.Abrir((int)valor);
        frmProdutoEditar janela = new frmProdutoEditar(p, dao);
        this.getParent().add(janela);
        janela.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_tblListagemMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnFiltrar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblListagem;
    private javax.swing.JTextField txtFiltro;
    // End of variables declaration//GEN-END:variables
}
