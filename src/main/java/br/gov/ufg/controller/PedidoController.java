package br.gov.ufg.controller;

import br.gov.ufg.dto.ClienteDTO;
import br.gov.ufg.dto.PedidoDTO;
import br.gov.ufg.dto.ProdutoDTO;
import br.gov.ufg.entity.Cliente;
import br.gov.ufg.entity.Item;
import br.gov.ufg.entity.Pedido;
import br.gov.ufg.entity.Produto;
import br.gov.ufg.utils.HttpException;
import br.gov.ufg.utils.bodys.PedidoCliente;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@CrossOrigin
@RestController
public class PedidoController {
    @PostMapping("/pedido")
    public ResponseEntity<Object> cadastrarPedido(@RequestBody PedidoCliente novoPedido) {
        System.out.println(novoPedido.toTxt());
        System.out.println(novoPedido.getLogin().getEmail());
        System.out.println(novoPedido.getLogin().getSenha());

        if (
            novoPedido.getIdPedido() != null ||
            novoPedido.getDataPedido() == null ||
            novoPedido.getStatus() == null ||
            novoPedido.getItens() == null
        ) {
            return HttpException.handleException("Campos inválidos no cadastro do pedido", HttpStatus.BAD_REQUEST);
        }

        List<Cliente> clientes = null;
        List<Pedido> pedidos = null;
        List<Produto> produtos = null;
        try {
            clientes = ClienteDTO.lerClientesDoArquivo();
            pedidos = PedidoDTO.lerPedidosDoArquivo();
            produtos = ProdutoDTO.lerProdutosDoArquivo();
        } catch (Exception e) {
            System.err.println(e.getStackTrace().toString());
            return HttpException.handleException(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        Cliente cliente = clientes.stream()
            .filter(c -> {
                return novoPedido.getLogin().login(c.getEmail(), c.getSenha());
            })
            .findFirst()
            .orElse(null);

        if (cliente == null) {
            return HttpException.handleException("Cliente não autorizado", HttpStatus.UNAUTHORIZED);
        }

        Produto produtoReferente = null;
        for (Item item : novoPedido.getItens()) {
            produtoReferente = null;

            for (Produto produto : produtos) {
                if (item.getIdProduto().equals(produto.getidProduto())) {
                    produtoReferente = produto;
                    break;
                }
            }

            if(produtoReferente == null) break;
        }

        if(produtoReferente == null) {
            return HttpException.handleException("Um ou mais itens não possui produto valido", HttpStatus.BAD_REQUEST);
        }

        novoPedido.setIdPedido(pedidos.size());

        try {
            PedidoDTO.salvarPedido(novoPedido, cliente);
        } catch (Exception e) {
            return HttpException.handleException(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(novoPedido, HttpStatus.CREATED);
    }
}