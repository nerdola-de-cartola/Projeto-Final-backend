package br.gov.ufg.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

import br.gov.ufg.utils.ValidaCPF;

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

    public boolean validaCPF() {
        return ValidaCPF.isValidCPF(this.cpf);
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

    @Override
    public String toString() {
        return 
            "ClientePessoaFisica {" +
            "  getEmail()=" + getEmail() +
            ", getSenha()=" + getSenha() +
            ", getCpf()=" + getCpf() +
            ", getRg()=" + getRg() +
            ", getNome()=" + getNome() +
            ", getDataDeNascimento()=" + getDataDeNascimento() +
            ", getidCliente()=" + getidCliente() +
            ", getEndereço()=" + getEndereço() +
            ", getTelefone()=" + getTelefone() +
            ", getUserName()=" + getUserName() +
            "}";
    }

    public String toTxt() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return super.toTxt() + "," + cpf + "," + rg + "," + simpleDateFormat.format(dataDeNascimento);
    }
}
