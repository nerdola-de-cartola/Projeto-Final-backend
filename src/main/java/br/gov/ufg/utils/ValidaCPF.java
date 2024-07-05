package br.gov.ufg.utils;

import java.util.regex.Pattern;

public class ValidaCPF {
    private static final String CPF_REGEX = "^(\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}|\\d{11})$";

    public static boolean isValidCPF(String cpf) {
        // Remover caracteres especiais se houver
        cpf = cpf.replaceAll("[^\\d]", "");
        
        if (!Pattern.matches(CPF_REGEX, cpf)) {
            return false;
        }

        // Validar o tamanho do CPF
        if (cpf.length() != 11) {
            return false;
        }

        // Verificar se todos os dígitos são iguais (CPFs inválidos)
        if (cpf.chars().distinct().count() == 1) {
            return false;
        }

        // Calcular e validar os dígitos verificadores
        int[] cpfArray = cpf.chars().map(c -> c - '0').toArray();

        int firstDigit = calculateDigit(cpfArray, 10);
        int secondDigit = calculateDigit(cpfArray, 11);

        return cpfArray[9] == firstDigit && cpfArray[10] == secondDigit;
    }

    private static int calculateDigit(int[] cpf, int weight) {
        int sum = 0;
        for (int i = 0; i < weight - 1; i++) {
            sum += cpf[i] * (weight - i);
        }
        int remainder = sum % 11;
        return remainder < 2 ? 0 : 11 - remainder;
    }

    public static void main(String[] args) {
        String[] testCpfs = {"123.456.789-09", "111.444.777-35", "12345678909", "11144477735"};

        for (String cpf : testCpfs) {
            System.out.println("CPF: " + cpf + " - Válido? " + isValidCPF(cpf));
        }
    }
}
