package io.github.holkex;

public final class DocumentValidator {

  private DocumentValidator() {}

  public static boolean isCpfValid(String cpf) {
    if (cpf == null) return false;

    cpf = cpf.replaceAll("\\D", "");

    if (!cpf.matches("\\d{11}")) return false;
    if (cpf.chars().distinct().count() == 1) return false;

    int d1 = calculateCpfDigit(cpf.substring(0, 9));
    int d2 = calculateCpfDigit(cpf.substring(0, 9) + d1);

    return cpf.equals(cpf.substring(0, 9) + d1 + d2);
  }

  public static boolean isCnpjValid(String cnpj) {
    if (cnpj == null) return false;

    // Remove caracteres especiais, mantendo letras e números
    cnpj = cnpj.replaceAll("[^A-Za-z0-9]", "").toUpperCase();

    if (!cnpj.matches("[A-Z0-9]{12}\\d{2}")) return false;
    if (cnpj.chars().distinct().count() == 1) return false;

    int d1 = calculateCnpjDigit(cnpj.substring(0, 12));
    int d2 = calculateCnpjDigit(cnpj.substring(0, 12) + d1);

    return cnpj.equals(cnpj.substring(0, 12) + d1 + d2);
  }

  public static String whatsDocumentIs(String documento){
   if( documento == null ) return "Document is null";
   String clean = documento.replaceAll("[^A-Za-z0-9]", "");
   
   if( isCpfValid(clean))
      return "CPF";

   if( isCnpjValid(clean))
      return "CNPJ";
    
    return "Document is not CNPJ or CPF";
  }

  /**
   * Formata um CPF (XXX.XXX.XXX-XX).
   * @param cpf CPF sujo ou limpo
   * @return CPF formatado ou o valor original se não tiver 11 dígitos
   */
  public static String formatCpf(String cpf) {
    if (cpf == null) return null;
    String clean = cpf.replaceAll("[^A-Za-z0-9]", "").toUpperCase();
    if (clean.length() != 11) return cpf;
    return String.format("%s.%s.%s-%s",
        clean.substring(0, 3),
        clean.substring(3, 6),
        clean.substring(6, 9),
        clean.substring(9));
  }

  /**
   * Formata um CNPJ (XX.XXX.XXX/XXXX-XX).
   * @param cnpj CNPJ sujo ou limpo
   * @return CNPJ formatado ou o valor original se não tiver 14 dígitos
   */
  public static String formatCnpj(String cnpj) {
    if (cnpj == null) return null;
    String clean = cnpj.replaceAll("[^A-Za-z0-9]", "").toUpperCase();
    if (clean.length() != 14) return cnpj;
    return String.format("%s.%s.%s/%s-%s",
        clean.substring(0, 2),
        clean.substring(2, 5),
        clean.substring(5, 8),
        clean.substring(8, 12),
        clean.substring(12));
  }

  private static int calculateCnpjDigit(String base) {
    int soma = 0;
    int peso = 2;

    for (int i = base.length() - 1; i >= 0; i--) {
      soma += (base.charAt(i) - '0') * peso;
      peso = (peso == 9) ? 2 : peso + 1;
    }

    int resto = soma % 11;
    return (resto < 2) ? 0 : 11 - resto;
  }

  private static int calculateCpfDigit(String base) {
    int soma = 0;
    int peso = 2;

    for (int i = base.length() - 1; i >= 0; i--) {
      soma += (base.charAt(i) - '0') * peso;
      peso++;
    }

    int resto = soma % 11;
    return (resto < 2) ? 0 : 11 - resto;
  }
}