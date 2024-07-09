package br.gov.ufg.entity;

import br.gov.ufg.utils.ValidaCNPJ;

public class ClientePessoaJuridica extends Cliente {
    private String cnpj;
    private String razaoSocial;
    private String inscricaoEstadual;

    public ClientePessoaJuridica() {
        super();
    }

    public ClientePessoaJuridica(
        int idCLiente,
        String nome,
        String email,
        String endereço,
        String telefone,
        String userName,
        String password,
        String cnpj,
        String razaoSocial,
        String inscricaoEstadual
    ) {
        super(idCLiente,
        nome,
        email,
        endereço,
        telefone,
        userName,
        password);
        this.cnpj = cnpj;
        this.razaoSocial = razaoSocial;
        this.inscricaoEstadual = inscricaoEstadual;
    }

    public boolean validaCNPJ() { 
        return ValidaCNPJ.isValidCNPJ(cnpj);
    }

    public String getCnpj() {
        return cnpj;
    }
    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }
    public String getRazaoSocial() {
        return razaoSocial;
    }
    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }
    public String getInscricaoEstadual() {
        return inscricaoEstadual;
    }
    public void setInscricaoEstadual(String inscricaoEstadual) {
        this.inscricaoEstadual = inscricaoEstadual;
    }

    @Override
    public String toTxt() {
        return
            super.toTxt() + "," +
            cnpj + "," +
            razaoSocial + "," +
            inscricaoEstadual;
    }

    @Override
    public String toString() {
        return
        "ClientePessoaJuridica {" +
        "  getEmail()=" + getEmail() +
        ", getSenha()=" + getSenha() +
        ", getCnpj()=" + getCnpj() +
        ", getRazaoSocial()=" + getRazaoSocial() +
        ", getNome()=" + getNome() +
        ", getInscricaoEstadual()=" + getInscricaoEstadual() +
        ", getidCliente()=" + getidCliente() +
        ", getEndereço()=" + getEndereço() +
        ", getTelefone()=" + getTelefone() +
        ", getUserName()=" + getUserName() +
        "}";
    }
}
