package br.gov.ufg.controller;

import br.gov.ufg.entity.Cliente;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin()
@RestController
public class ClienteController {
    
    @PostMapping("cadastrarCliente")
    public ResponseEntity<Cliente> add(@RequestBody Cliente novoCliente) {
        return new ResponseEntity<>(novoCliente, HttpStatus.CREATED);
    }

}