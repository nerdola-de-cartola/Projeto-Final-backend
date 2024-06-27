package br.gov.ufg.entity;

public class Produto {
    private Integer idProduto;
    private String nome;
    private String descricao;
    private double preco;
    private int estoque;

    private String imagem;

    public Produto(Integer idProduto, String nome, String descricao, double preco, int estoque, String imagem) {
        this.idProduto = idProduto;
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.estoque = estoque;
        this.imagem = imagem;
    }

    public void atualizaEstoque(int quantidade){
        this.estoque = quantidade;
    }

    public void atualizaPreco(double preco){
        this.preco = preco;
    }

    public Integer getidProduto() {
        return idProduto;
    }

    public void setidProduto(Integer idProduto) {
        this.idProduto = idProduto;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public int getEstoque() {
        return estoque;
    }

    public void setEstoque(int estoque) {
        this.estoque = estoque;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    @Override
    public String toString() {
        return "Produto{" +
                "id=" + idProduto +
                ", nome='" + nome + '\'' +
                '}';
    }
}
