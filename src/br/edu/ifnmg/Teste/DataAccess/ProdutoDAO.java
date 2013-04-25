/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.Teste.DataAccess;

import br.edu.ifnmg.Teste.DomainModel.Produto;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
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

    public boolean Salvar(Produto obj) {
        try {
            if (obj.getId() == 0) {
                PreparedStatement comando = bd.getConexao().prepareStatement("insert into produtos(nome,valor) values(?,?)");
                comando.setString(0, obj.getNome());
                comando.setDouble(1, obj.getValor());
                comando.executeUpdate();
            } else {
                PreparedStatement comando = bd.getConexao().prepareStatement("update produtos set nome = ?,valor = ? where id = ?");
                comando.setString(0, obj.getNome());
                comando.setDouble(1, obj.getValor());
                comando.setDouble(2, obj.getId());
                comando.executeUpdate();
            }
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public Produto Abrir(int id) {
        try {
            Produto produto = new Produto(0, "", 0);

            PreparedStatement comando = bd.getConexao().prepareStatement("select * from produtos where id = ?");
            comando.setInt(0, id);
            ResultSet resultado = comando.executeQuery();

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

    public boolean Apagar(Produto obj) {
        try {
            PreparedStatement comando = bd.getConexao().prepareStatement("delete from produtos where id = ?");
            comando.setInt(0, obj.getId());
            comando.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public List<Produto> listarTodos() {
        try {
            PreparedStatement comando = bd.getConexao().prepareStatement("select * from produtos ");
            ResultSet resultado = comando.executeQuery();
            // Cria uma lista de produtos vazia
            List<Produto> produtos = new LinkedList<>();
            while(resultado.next()){
                // Inicializa um objeto de produto vazio
                Produto tmp = new Produto();
                // Pega os valores do retorno da consulta e coloca no objeto
                tmp.setId(resultado.getInt("id"));
                tmp.setNome(resultado.getString("nome"));
                tmp.setValor(resultado.getDouble("valor"));
                // Pega o objeto e coloca na lista
                produtos.add(tmp);                
            }
            return produtos;
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public List<Produto> buscar(Produto filtro) {
        try {
            PreparedStatement comando = bd.getConexao().prepareStatement("select * from produtos where nome like '%?%' or valor = ? or id = ? ");
            comando.setString(0, filtro.getNome());
            comando.setDouble(1, filtro.getValor());
            comando.setInt(2, filtro.getId());
            ResultSet resultado = comando.executeQuery();
            // Cria uma lista de produtos vazia
            List<Produto> produtos = new LinkedList<>();
            while(resultado.next()){
                // Inicializa um objeto de produto vazio
                Produto tmp = new Produto();
                // Pega os valores do retorno da consulta e coloca no objeto
                tmp.setId(resultado.getInt("id"));
                tmp.setNome(resultado.getString("nome"));
                tmp.setValor(resultado.getDouble("valor"));
                // Pega o objeto e coloca na lista
                produtos.add(tmp);                
            }
            return produtos;
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}
