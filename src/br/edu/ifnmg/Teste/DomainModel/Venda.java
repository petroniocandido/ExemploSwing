/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.Teste.DomainModel;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author petronio
 */
public class Venda {
    int id;
    double valorTotal;
    Date data;
    
    List<ItemVenda> itens;
    
    public Venda() {
        id = 0;
        valorTotal = 0;
        data = new Date();
        itens = new LinkedList<ItemVenda>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }   
    
    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public List<ItemVenda> getItens() {
        return itens;
    }

    public void setItens(List<ItemVenda> itens) {
        this.itens = itens;
    }
    
    public void add(ItemVenda i){
        if(!itens.contains(i)){
            itens.add(i);
            valorTotal += i.getProduto().getValor() * i.getQuantidade();
        }
    }
    
    public void remove(ItemVenda i){
        if(itens.contains(i)){
            itens.remove(i);
            valorTotal -= i.getProduto().getValor() * i.getQuantidade();
        }
    }
    
}
