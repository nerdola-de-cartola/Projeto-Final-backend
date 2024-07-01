package br.gov.ufg.entity;

import java.sql.Date;
import br.gov.ufg.utils.RegexUtils;

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
        String regex = "/^[0-9]{3}.?[0-9]{3}.?[0-9]{3}-?[0-9]{2}/";
        return RegexUtils.validaStringPorRegex(regex, cpf);
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
