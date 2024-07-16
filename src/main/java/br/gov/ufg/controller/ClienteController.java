package br.gov.ufg.controller;

import br.gov.ufg.dto.ClienteDTO;
import br.gov.ufg.entity.Cliente;
import br.gov.ufg.entity.ClientePessoaFisica;
import br.gov.ufg.entity.ClientePessoaJuridica;
import br.gov.ufg.entity.Login;
import br.gov.ufg.utils.HttpException;

import java.io.IOException;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin()
@RestController
public class ClienteController {

    @PostMapping("cliente/login")
    public ResponseEntity<Object> login(@RequestBody Login login) { 
        List<Cliente> clientes = null;
        
        try {
            clientes = ClienteDTO.lerClientesDoArquivo();
        } catch (Exception e) {
            return HttpException.handleException(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        Cliente cliente = clientes.stream()
                .filter(c -> c.login(login.getEmail(), login.getSenha()))
                .findFirst()
                .orElse(null);

        if (cliente == null) {
            return HttpException.handleException("Cliente não existe na base de dados", HttpStatus.FORBIDDEN);
        }

        return new ResponseEntity<>(cliente, HttpStatus.ACCEPTED);
    }

    @PostMapping("cliente/pessoaFisica")
    public ResponseEntity<Object> cadastrarPessoaFisica(@RequestBody ClientePessoaFisica novoCliente) {
        if (
            novoCliente.getCpf() == null ||
            novoCliente.getDataDeNascimento() == null ||
            novoCliente.getEmail() == null ||
            novoCliente.getEndereco() == null ||
            novoCliente.getNome() == null ||
            novoCliente.getRg() == null ||
            novoCliente.getSenha() == null ||
            novoCliente.getTelefone() == null ||
            novoCliente.getUserName() == null ||
            novoCliente.getidCliente() != null
        ) {
            return HttpException.handleException("Campos inválidos no cadastro do cliente", HttpStatus.BAD_REQUEST);
        }

        if (!novoCliente.validaCPF()) {
            return HttpException.handleException("CPF inválido", HttpStatus.BAD_REQUEST);
        }

        List<Cliente> clientes = null;
        try {
            clientes = ClienteDTO.lerClientesDoArquivo();
        } catch (Exception e) {
            return HttpException.handleException(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        Cliente cliente = clientes.stream()
                .filter(c -> {
                    if(c.getEmail().equals(novoCliente.getEmail())) {
                        return true;
                    }
    
                    if(c.getClass() == ClientePessoaFisica.class){
                        return ((ClientePessoaFisica) c).getCpf().equals(novoCliente.getCpf());
                    } 
    
                    return false;
                })
                .findFirst()
                .orElse(null);

        if (cliente != null) {
            return HttpException.handleException("Cliente já cadastrado", HttpStatus.CONFLICT);
        }
        
        novoCliente.setidCliente(clientes.size());

        try {
            ClienteDTO.salvarCliente(novoCliente);
        } catch (Exception e) {
            return HttpException.handleException(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }


        return new ResponseEntity<>(novoCliente, HttpStatus.CREATED);
    }

    @PutMapping("cliente/pessoaFisica")
    public ResponseEntity<Object> atualizarClientePessoaFisisca(@RequestBody ClientePessoaFisica novoCliente) {
        try {
            boolean resultado = ClienteDTO.atualizarCliente(novoCliente);
            if(!resultado) return HttpException.handleException("Cliente não existe", HttpStatus.NOT_FOUND);
        } catch (URISyntaxException | IOException | ParseException e) {
            return HttpException.handleException(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
        return new ResponseEntity<>(novoCliente, HttpStatus.OK);
    }

    @PostMapping("cliente/pessoaJuridica")
    public ResponseEntity<Object> cadastrarPessoaJuridica(@RequestBody ClientePessoaJuridica novoCliente) {
        if (
            novoCliente.getCnpj() == null ||
            novoCliente.getInscricaoEstadual() == null ||
            novoCliente.getEmail() == null ||
            novoCliente.getEndereco() == null ||
            novoCliente.getNome() == null ||
            novoCliente.getRazaoSocial() == null ||
            novoCliente.getSenha() == null ||
            novoCliente.getTelefone() == null ||
            novoCliente.getUserName() == null ||
            novoCliente.getidCliente() != null
        ) {
            return HttpException.handleException("Campos inválidos no cadastro do cliente", HttpStatus.BAD_REQUEST);
        }

        if (!novoCliente.validaCNPJ()) {
            return HttpException.handleException("CNPJ inválido", HttpStatus.BAD_REQUEST);
        }

        List<Cliente> clientes = null;
        try {
            clientes = ClienteDTO.lerClientesDoArquivo();
        } catch (Exception e) {
            return HttpException.handleException(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        Cliente cliente = clientes.stream()
            .filter(c -> {
                if(c.getEmail().equals(novoCliente.getEmail())) {
                    return true;
                }

                if(c.getClass() == ClientePessoaJuridica.class){
                    return ((ClientePessoaJuridica) c).getCnpj().equals(novoCliente.getCnpj());
                } 

                return false;
            })
            .findFirst()
            .orElse(null);

        if (cliente != null) {
            return HttpException.handleException("Cliente já cadastrado", HttpStatus.CONFLICT);
        }
        
        novoCliente.setidCliente(clientes.size());

        try {
            ClienteDTO.salvarCliente(novoCliente);
        } catch (Exception e) {
            return HttpException.handleException(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }


        return new ResponseEntity<>(novoCliente, HttpStatus.CREATED);
    }

    @PutMapping("cliente/pessoaJuridica")
    public ResponseEntity<Object> atualizarClientePessoaJuridica(@RequestBody ClientePessoaJuridica novoCliente) {
        try {
            boolean resultado = ClienteDTO.atualizarCliente(novoCliente);
            if(!resultado) return HttpException.handleException("Cliente não existe", HttpStatus.NOT_FOUND);
        } catch (URISyntaxException | IOException | ParseException e) {
            return HttpException.handleException(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
        return new ResponseEntity<>(novoCliente, HttpStatus.OK);
    }
}