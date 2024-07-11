package br.gov.ufg.utils.bodys;

import java.util.Date;

import br.gov.ufg.entity.Login;
import br.gov.ufg.entity.Pedido;

public class PedidoCliente extends Pedido {
    private Login login;

    public PedidoCliente() {}

    public PedidoCliente(Integer idCliente, Integer idPedido, Date dataPedido, String status, Login login) {
        super(idCliente, idPedido, dataPedido, status);
        this.login = login;
    }

    public Login getLogin() {
        return login;
    }
}