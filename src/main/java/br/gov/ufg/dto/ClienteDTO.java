package br.gov.ufg.dto;

import br.gov.ufg.api.Main;
import br.gov.ufg.entity.Cliente;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class ClienteDTO {

    private static final String CAMINHO_ARQUIVO = "database/clientes.txt";

    public static List<Cliente> lerClientesDoArquivo() throws IOException, URISyntaxException {

        // Tentar obter o caminho do arquivo como um recurso
        java.net.URL resource = Main.class.getClassLoader().getResource(CAMINHO_ARQUIVO);

        // Converter URL para URI e obter o caminho absoluto
        java.nio.file.Path caminhoArquivoAbsoluto = Paths.get(resource.toURI());

        return Files.lines(caminhoArquivoAbsoluto)
                .map(line -> {
                    String[] clientString = line.split(",");
                    int idCLiente = Integer.parseInt(clientString[0]);
                    String nome = clientString[1];
                    String email = clientString[2];
                    String endereço = clientString[3];
                    String telefone = clientString[4];
                    String userName = clientString[5];
                    String senha = clientString[6];

                    return new Cliente(idCLiente, nome, email, endereço, telefone, userName, senha);
                })
                .collect(Collectors.toList());

    }
}
