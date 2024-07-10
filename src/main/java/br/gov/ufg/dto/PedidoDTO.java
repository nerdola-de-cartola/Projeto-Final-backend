package br.gov.ufg.dto;

import br.gov.ufg.entity.Cliente;
import br.gov.ufg.entity.Item;
import br.gov.ufg.entity.Pedido;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;

public class PedidoDTO {
    private static final String CAMINHO_DATABASE = "src/main/resources/database/pedidos.txt";

    public static List<Pedido> lerPedidosDoArquivo() throws IOException, ParseException {
        List<Pedido> list = new ArrayList<Pedido>();
        File f = new File(CAMINHO_DATABASE);
        InputStream is = new FileInputStream(f.getAbsolutePath());
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);

        while (true) {
            String linha = br.readLine();
            if (linha == null)
                break;
            Pedido c = lerPedido(linha);
            list.add(c);
        }

        br.close();
        return list;
    }
    
    public static Pedido lerPedido(String line) throws ParseException {
        String[] sep = line.split("\\[|\\]");
        String[] pedidoString = sep[0].split(",");
        String[] itensString = sep[1].split(";");

        Integer idPedido = Integer.parseInt(pedidoString[0]);
        Date dataPedido = new SimpleDateFormat("yyyy-MM-dd").parse(pedidoString[1]);
        String status = pedidoString[2];
        
        List<Item> itens = new ArrayList<Item>();
        for (String itemString : itensString) {
            Item i = lerItem(itemString);
            itens.add(i);
        }
    
        return new Pedido(idPedido, dataPedido, status, itens);
    }

    private static Item lerItem(String line) {
        String[] itemString = line.split(",");

        Integer idProduto = Integer.parseInt(itemString[0]);
        Integer quantidade = Integer.parseInt(itemString[1]);
        double precoUnitario = Double.parseDouble(itemString[2]);

        return new Item(idProduto, quantidade, precoUnitario);
    }

    public static void salvarPedido(Pedido pedido, Cliente cliente) throws URISyntaxException, IOException {
        File f = new File(CAMINHO_DATABASE);

        PrintWriter pw = new PrintWriter(
                new FileOutputStream(new File(f.getAbsolutePath()), true));

        String line = pedido.toTxt() + "," + cliente.getidCliente();

        pw.println(line);
        pw.flush();
        pw.close();
    }
}
