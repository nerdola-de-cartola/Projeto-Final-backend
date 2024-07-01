package br.gov.ufg.entity;

import java.util.ArrayList;
import java.util.List;

public abstract class Cliente {
    private int idCLiente;
    private String nome;
    private String email;
    private String endereço;
    private String telefone;
    private String userName;
    private String password;
    private List<Pedido> pedidos;

    public Cliente() {}

    public Cliente(int idCLiente, String nome, String email, String endereço, String telefone, String userName,
            String password) {
        this.idCLiente = idCLiente;
        this.nome = nome;
        this.email = email;
        this.endereço = endereço;
        this.telefone = telefone;
        this.userName = userName;
        this.password = password;
        this.pedidos = new ArrayList<Pedido>();
    }

    public boolean login(String userName, String password) {
        return userName == this.userName && password == this.password;
    }

    public void atualizaDados(Cliente novoCliente) {
        this.idCLiente = novoCliente.getIdCLiente();
        this.nome = novoCliente.getNome();
        this.endereço = novoCliente.getEndereço();
        this.telefone = novoCliente.getTelefone();
        this.userName = novoCliente.getUserName();
        this.password = novoCliente.getPassword();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdCLiente() {
        return idCLiente;
    }

    public void setIdCLiente(int idCLiente) {
        this.idCLiente = idCLiente;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEndereço() {
        return endereço;
    }

    public void setEndereço(String endereço) {
        this.endereço = endereço;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public List<Pedido> getPedidos() {
        return pedidos;
    }


    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }
}
