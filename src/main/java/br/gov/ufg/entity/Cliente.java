package br.gov.ufg.entity;

import java.util.ArrayList;
import java.util.List;

public abstract class Cliente extends Login {
    private Integer idCliente;
    private String nome;
    private String endereco;
    private String telefone;
    private String userName;
    private List<Pedido> pedidos;

    public Cliente() {
        this.pedidos = new ArrayList<Pedido>();
    }

    public Cliente(
        Integer idCliente,
        String nome,
        String email,
        String endereco,
        String telefone,
        String userName,
        String senha)
    {
        super(email, senha);
        this.idCliente = idCliente;
        this.nome = nome;
        this.endereco = endereco;
        this.telefone = telefone;
        this.userName = userName;
        this.pedidos = new ArrayList<Pedido>();
    }

    public void atualizaDados(Cliente novoCliente) {
        this.idCliente = novoCliente.getidCliente();
        this.nome = novoCliente.getNome();
        this.endereco = novoCliente.getEndereco();
        this.telefone = novoCliente.getTelefone();
        this.userName = novoCliente.getUserName();
        this.setSenha(novoCliente.getSenha());
        this.setEmail(novoCliente.getEmail());
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getidCliente() {
        return idCliente;
    }

    public void setidCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    @Override
    public String toString() {
        return "Cliente [idCliente=" + idCliente + ", nome=" + nome + ", endereco=" + endereco + ", telefone="
                + telefone + ", userName=" + userName + ", pedidos=" + pedidos + ", getEmail()=" + getEmail()
                + ", getSenha()=" + getSenha() + "]";
    }

    public String toTxt() {
        return
            idCliente.toString() + "," +
            nome + "," +
            endereco + "," +
            telefone + "," +
            userName + "," +
            super.toTxt();
    }
}
