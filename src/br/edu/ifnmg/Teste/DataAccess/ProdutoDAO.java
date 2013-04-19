/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.Teste.DataAccess;

import br.edu.ifnmg.Teste.DomainModel.Produto;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author petronio
 */
public class ProdutoDAO {
    private BD bd;
    
    public ProdutoDAO() {
        bd = new BD();
    }
    
    public boolean Salvar(Produto obj){
        try {
            Statement comando = bd.getConexao().createStatement();
            comando.executeUpdate("insert into produtos(nome,valor) values('"+obj.getNome()
                    +"','"+obj.getValor()+"')");
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public Produto Abrir (int id) {
        try {
            Produto produto = new Produto(0, "", 0);
            
            Statement comando = bd.getConexao().createStatement();
            ResultSet resultado = comando.executeQuery("select * from produtos where id = " + id);
            
            resultado.first();
            
            produto.setId(resultado.getInt("id"));
            produto.setNome(resultado.getString("nome"));
            produto.setValor(resultado.getDouble("valor"));
            
            return produto;
            
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
}
