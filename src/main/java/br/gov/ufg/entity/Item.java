package br.gov.ufg.entity;

public class Item {
    private Integer idProduto;
    private Integer quantidade;
    private double precoUnitario;
    
    public Item(Integer idProduto, Integer quantidade, double precoUnitario) {
        this.idProduto = idProduto;
        this.quantidade = quantidade;
        this.precoUnitario = precoUnitario;
    }

    public double calcularSubtotal(){
        return precoUnitario*quantidade;
    }

    public Integer getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(Integer idProduto) {
        this.idProduto = idProduto;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public double getPrecoUnitario() {
        return precoUnitario;
    }

    public void setPrecoUnitario(double precoUnitario) {
        this.precoUnitario = precoUnitario;
    }

    public String toTxt() {
        return
            idProduto  + "," +
            quantidade + "," +
            Double.toString(precoUnitario);
    }
}
