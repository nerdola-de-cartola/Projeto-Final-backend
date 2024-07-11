package br.gov.ufg.entity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Pedido {
    private Integer idCliente;
    private Integer idPedido;
    private Date dataPedido;
    private String status;
    private List<Item> itens;
    
    public Pedido(Integer idCliente,Integer idPedido, Date dataPedido, String status, List<Item> itens) {
        this.idCliente = idCliente;
        this.idPedido = idPedido;
        this.dataPedido = dataPedido;
        this.status = status;
        this.itens = itens;
    }

    public Pedido() {}

    public Pedido(Integer idCliente, Integer idPedido, Date dataPedido, String status) {
        this.idCliente = idCliente;
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

    public Integer getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(Integer idPedido) {
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

    public String toTxt() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String itensToTxt = new String();

        for (Item i: itens) {
            itensToTxt += i.toTxt() + ";";
        }

        return
            idCliente + "," +
            idPedido + "," +
            simpleDateFormat.format(dataPedido) + "," +
            status + "," +
            "[" + itensToTxt + "]";
    }

    public List<Item> getItens() {
        return itens;
    }

    public void setItens(List<Item> itens) {
        this.itens = itens;
    }

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }
}
