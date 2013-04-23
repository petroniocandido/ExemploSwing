/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.Teste.DomainModel;

/**
 *
 * @author petronio
 */
public class Produto {
    int id;
    String nome;
    double valor;
    
    public Produto(){
        this.id = 0;
        this.nome = "";
        this.valor = 0;
    }
    
    public Produto(int id_, String nom, double val){
        this.id = id_;
        this.nome = nom;
        this.valor = val;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return nome;
    }
    
    
}
