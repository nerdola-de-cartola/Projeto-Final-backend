package br.gov.ufg.entity;

import java.sql.Date;
import java.util.List;

public class Pedido {
    private int idPedido;
    private Date dataPedido;
    private String status;
    private List<Item> itens;
    
    public Pedido(int idPedido, Date dataPedido, String status) {
        this.idPedido = idPedido;
        this.dataPedido = dataPedido;
        this.status = status;
    }

    public double calcularTotal(){
        
        double total = 0;

        for(int i = 0; i < itens.size() ; i++){
            total = total + itens.get(i).calcularSubtotal();
        }
        
        return total;
    }

    public void atualizarStatus(String status){
        this.status = status;
    }

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public Date getDataPedido() {
        return dataPedido;
    }

    public void setDataPedido(Date dataPedido) {
        this.dataPedido = dataPedido;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
