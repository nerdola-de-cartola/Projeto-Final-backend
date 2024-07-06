package br.gov.ufg.dto;

import br.gov.ufg.entity.ClientePessoaFisica;
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


public class ClienteDTO {
    private static final String CAMINHO_ARQUIVO = "src/main/resources/database/clientesPF.txt";

    public static List<ClientePessoaFisica> lerClientesPFDoArquivo() throws IOException, ParseException {
        List<ClientePessoaFisica> list = new ArrayList<ClientePessoaFisica>();
        File f = new File(CAMINHO_ARQUIVO);
        InputStream is = new FileInputStream(f.getAbsolutePath());
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);
        
        while (true) {
            String linha = br.readLine();
            if (linha == null) break;
            ClientePessoaFisica c = lerClientePF(linha);
            list.add(c);
        }

        br.close();
        return list;
    }

    public static ClientePessoaFisica lerClientePF(String line) throws ParseException {
        String[] clientString = line.split(",");
        int idCLiente = Integer.parseInt(clientString[0]);
        String nome = clientString[1];
        String endereço = clientString[2];
        String telefone = clientString[3];
        String userName = clientString[4];
        String email = clientString[5];
        String senha = clientString[6];
        String cpf = clientString[7];
        String rg = clientString[8];
        Date dataDeNascimento = new SimpleDateFormat("yyyy-MM-dd").parse(clientString[9]);
   

        return new ClientePessoaFisica(idCLiente, nome, email, endereço, telefone, userName, senha, cpf, rg, dataDeNascimento);
    }

    public static void salvarCliente(ClientePessoaFisica cliente) throws URISyntaxException, IOException {
        File f = new File(CAMINHO_ARQUIVO);

        PrintWriter pw = new PrintWriter(
            new FileOutputStream(new File(f.getAbsolutePath()), true)
        ); 
        pw.println(cliente.toTxt());
        pw.flush();
        pw.close();
    }
}
