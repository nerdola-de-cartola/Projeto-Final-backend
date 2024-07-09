package br.gov.ufg.utils;

public class ValidaCNPJ {

    public static boolean isValidCNPJ(String cnpj) {
        // Remove any non-numeric characters using regex
        cnpj = cnpj.replaceAll("\\D", "");

        // Check if the CNPJ has 14 digits
        if (cnpj.length() != 14) {
            return false;
        }

        // Check for known invalid CNPJs using regex
        if (cnpj.matches("(\\d)\\1{13}")) {
            return false;
        }

        // Calculate the verification digits
        int[] weight1 = {5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};
        int[] weight2 = {6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};

        int sum = 0;
        for (int i = 0; i < 12; i++) {
            sum += Character.getNumericValue(cnpj.charAt(i)) * weight1[i];
        }

        int firstDigit = sum % 11 < 2 ? 0 : 11 - (sum % 11);

        sum = 0;
        for (int i = 0; i < 12; i++) {
            sum += Character.getNumericValue(cnpj.charAt(i)) * weight2[i];
        }
        sum += firstDigit * weight2[12];

        int secondDigit = sum % 11 < 2 ? 0 : 11 - (sum % 11);

        // Check if the calculated digits match the actual digits
        return cnpj.charAt(12) == Character.forDigit(firstDigit, 10) &&
               cnpj.charAt(13) == Character.forDigit(secondDigit, 10);
    }

    public static void main(String[] args) {
        String cnpj = "11.222.333/0001-81";
        System.out.println("CNPJ " + cnpj + " is valid: " + isValidCNPJ(cnpj));
    }
}