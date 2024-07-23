package br.gov.ufg.controller;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import br.gov.ufg.dto.ProdutoDTO;
import br.gov.ufg.dto.ProdutoInterface;
import br.gov.ufg.entity.Produto;

public class ProdutoProxy implements ProdutoInterface {
    ProdutoInterface dto = null;
    List<Produto> cache = null;

    @Override
    public List<Produto> lerProdutosDoArquivo() throws IOException, URISyntaxException {
        if (dto == null) {
            dto = new ProdutoDTO();
        }
        
        if(cache == null) {
            System.out.println("cache miss");
            cache = dto.lerProdutosDoArquivo();
        } else {
            System.out.println("cache hit");
        }

        return cache;
    }
}
