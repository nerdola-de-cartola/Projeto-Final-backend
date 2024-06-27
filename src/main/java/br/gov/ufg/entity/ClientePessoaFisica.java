package br.gov.ufg.entity;

import java.sql.Date;

public class ClientePessoaFisica extends Cliente {
    private String cpf;
    private String rg;
    private Date dataDeNascimento;

    public ClientePessoaFisica() {
        super();
    }

    public ClientePessoaFisica(
        int idCLiente,
        String nome,
        String email,
        String endereço,
        String telefone,
        String userName,
        String password,
        String cpf,
        String rg,
        Date dataDeNascimento
    ) {
        super(idCLiente, nome, email, endereço, telefone, userName, password);
        this.cpf = cpf;
        this.rg = rg;
        this.dataDeNascimento = dataDeNascimento;
    }

    public boolean validaCPF(String cpf) {
        //TODO validar CPF
        System.err.println("TODO validar cpf");
        return true;
    }

    public String getCpf() {
        return cpf;
    }
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    public String getRg() {
        return rg;
    }
    public void setRg(String rg) {
        this.rg = rg;
    }
    public Date getDataDeNascimento() {
        return dataDeNascimento;
    }
    public void setDataDeNascimento(Date dataDeNascimento) {
        this.dataDeNascimento = dataDeNascimento;
    }
}
