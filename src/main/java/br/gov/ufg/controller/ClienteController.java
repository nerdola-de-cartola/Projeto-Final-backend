package br.gov.ufg.controller;

import br.gov.ufg.dto.ClienteDTO;
import br.gov.ufg.entity.Cliente;
import br.gov.ufg.entity.ClientePessoaFisica;
import br.gov.ufg.entity.Login;

import java.lang.reflect.Field;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin()
@RestController
public class ClienteController {

    @PostMapping("cliente/login")
    public ResponseEntity<Cliente> login(@RequestBody Login login) {
        try {
            System.out.println(login);
            
            List<Cliente> clientes = ClienteDTO.lerClientesDoArquivo();
            
            Cliente cliente = clientes.stream()
                .filter(c -> c.login(login.getEmail(), login.getSenha()))
                .findFirst()
                .orElse(null);
            
            System.out.println(cliente);

            if (cliente == null) {
                throw new Exception("Cliente não existe na base de daos");
            }

            return new ResponseEntity<>(cliente, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    @PostMapping("cliente/pessoaFisisca")
    public ResponseEntity<ClientePessoaFisica> cadastrarPessoaFisica(@RequestBody ClientePessoaFisica novoCliente) {
        try {
            System.out.println(novoCliente);

            if (
                novoCliente.getCpf() == null ||
                novoCliente.getDataDeNascimento() == null ||
                novoCliente.getEmail() == null ||
                novoCliente.getEndereço() == null ||
                novoCliente.getNome() == null ||
                novoCliente.getRg() == null ||
                novoCliente.getSenha() == null ||
                novoCliente.getTelefone() == null ||
                novoCliente.getUserName() == null ||
                novoCliente.getidCliente() != null
            ) {
                throw new Exception("Campos inválidos no cadastro do cliente");

            }
            
            if(!novoCliente.validaCPF()) {
                throw new Exception("CPF inválido");
            }

            List<Cliente> clientes = ClienteDTO.lerClientesDoArquivo();
            
            Cliente cliente = clientes.stream()
                .filter(c ->
                    c.getidCliente().equals(novoCliente.getidCliente()) ||
                    c.getEmail().equals(novoCliente.getEmail()) ||
                    (c.getClass() == ClientePessoaFisica.class &&
                    ((ClientePessoaFisica) c).getCpf().equals(novoCliente.getCpf()))
                )
                .findFirst()
                .orElse(null);

            if (cliente != null) {
                throw new Exception("Cliente já cadastrado");
            }

            return new ResponseEntity<>(novoCliente, HttpStatus.CREATED);
        } catch (Exception e) {
            System.err.println(e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}