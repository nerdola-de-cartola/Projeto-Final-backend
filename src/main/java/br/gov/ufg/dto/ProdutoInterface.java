package br.gov.ufg.dto;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import br.gov.ufg.entity.Produto;

public interface ProdutoInterface {
        public List<Produto> lerProdutosDoArquivo() throws IOException, URISyntaxException;

}
