package br.gov.ufg.dto;

import br.gov.ufg.entity.Cliente;
import br.gov.ufg.entity.ClientePessoaFisica;
import br.gov.ufg.entity.ClientePessoaJuridica;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
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
    private static final String CAMINHO_DATABASE = "src/main/resources/database/";

    public static List<Cliente> lerClientesDoArquivo() throws IOException, ParseException {
        List<ClientePessoaFisica> listaPessoasFisicas = lerClientesPFDoArquivo();
        List<ClientePessoaJuridica> listaPessoasJuridicas = lerClientesPJDoArquivo();
        List<Cliente> lista = new ArrayList<Cliente>();
        lista.addAll(listaPessoasFisicas);
        lista.addAll(listaPessoasJuridicas);
        return lista;
    }

    public static List<ClientePessoaFisica> lerClientesPFDoArquivo() throws IOException, ParseException {
        List<ClientePessoaFisica> list = new ArrayList<ClientePessoaFisica>();
        File f = new File(CAMINHO_DATABASE + "clientesPF.txt");
        InputStream is = new FileInputStream(f.getAbsolutePath());
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);

        while (true) {
            String linha = br.readLine();
            if (linha == null)
                break;
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

        return new ClientePessoaFisica(idCLiente, nome, email, endereço, telefone, userName, senha, cpf, rg,
                dataDeNascimento);
    }

    public static List<ClientePessoaJuridica> lerClientesPJDoArquivo() throws IOException, ParseException {
        List<ClientePessoaJuridica> list = new ArrayList<ClientePessoaJuridica>();
        File f = new File(CAMINHO_DATABASE + "clientesPJ.txt");
        InputStream is = new FileInputStream(f.getAbsolutePath());
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);

        while (true) {
            String linha = br.readLine();
            if (linha == null)
                break;
            ClientePessoaJuridica c = lerClientePJ(linha);
            list.add(c);
        }

        br.close();
        return list;
    }

    public static ClientePessoaJuridica lerClientePJ(String line) throws ParseException {
        String[] clientString = line.split(",");

        int idCLiente = Integer.parseInt(clientString[0]);
        String nome = clientString[1];
        String endereço = clientString[2];
        String telefone = clientString[3];
        String userName = clientString[4];
        String email = clientString[5];
        String senha = clientString[6];
        String cnpj = clientString[7];
        String razaoSocial = clientString[8];
        String inscricaoEstadual = clientString[9];

        return new ClientePessoaJuridica(idCLiente, nome, email, endereço, telefone, userName, senha, cnpj, razaoSocial,
                inscricaoEstadual);
    }

    public static void salvarCliente(ClientePessoaFisica cliente) throws URISyntaxException, IOException {
        File f = new File(CAMINHO_DATABASE + "clientesPF.txt");

        PrintWriter pw = new PrintWriter(
                new FileOutputStream(new File(f.getAbsolutePath()), true));
        pw.println(cliente.toTxt());
        pw.flush();
        pw.close();
    }

    public static void salvarCliente(ClientePessoaJuridica cliente) throws URISyntaxException, IOException {
        File f = new File(CAMINHO_DATABASE + "clientesPJ.txt");

        PrintWriter pw = new PrintWriter(
                new FileOutputStream(new File(f.getAbsolutePath()), true));
        pw.println(cliente.toTxt());
        pw.flush();
        pw.close();
    }

    public static boolean atualizarCliente(ClientePessoaFisica novoCliente) throws URISyntaxException, IOException, ParseException {
        File inputFile = new File(CAMINHO_DATABASE + "clientesPF.txt");
        File tempFile = new File(CAMINHO_DATABASE + "tmp.txt");

        BufferedReader reader = new BufferedReader(new FileReader(inputFile));
        BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

        String currentLine;
        String novoClienteTxt = novoCliente.toTxt();
        Integer idNovoCLiente = Integer.parseInt(novoClienteTxt.split(",")[0]);
        boolean find = false;
        
        while ((currentLine = reader.readLine()) != null) {
            Integer idCliente = Integer.parseInt(currentLine.split(",")[0]);


            if (idCliente.equals(idNovoCLiente)) {
                writer.write(novoClienteTxt);
                writer.newLine();
                find = true;
                continue;
            }

            writer.write(currentLine);
            writer.newLine();
        }

        reader.close();

        // Delete the original file
        if(!inputFile.delete()) {
            writer.close();
            throw new RuntimeException("Could not delete the original file");
        }

        writer.flush();
        writer.close();

        // Rename the tmp file
        if(!tempFile.renameTo(inputFile)) throw new RuntimeException("Could not rename the temporary file");

        return find;
    }

    public static boolean atualizarCliente(ClientePessoaJuridica novoCliente) throws URISyntaxException, IOException, ParseException {
        File inputFile = new File(CAMINHO_DATABASE + "clientesPJ.txt");
        File tempFile = new File(CAMINHO_DATABASE + "tmp1.txt");

        BufferedReader reader = new BufferedReader(new FileReader(inputFile));
        BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

        String currentLine;
        String novoClienteTxt = novoCliente.toTxt();
        Integer idNovoCLiente = Integer.parseInt(novoClienteTxt.split(",")[0]);
        boolean find = false;
        
        while ((currentLine = reader.readLine()) != null) {
            Integer idCliente = Integer.parseInt(currentLine.split(",")[0]);


            if (idCliente.equals(idNovoCLiente)) {
                writer.write(novoClienteTxt);
                writer.newLine();
                find = true;
                continue;
            }

            writer.write(currentLine);
            writer.newLine();
        }

        reader.close();

        // Delete the original file
        if(!inputFile.delete()) {
            writer.close();
            throw new RuntimeException("Could not delete the original file");
        }

        writer.flush();
        writer.close();

        // Rename the tmp file
        if(!tempFile.renameTo(inputFile)) throw new RuntimeException("Could not rename the temporary file");

        return find;
    }
}
