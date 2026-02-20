package br.com.bancada_os.api.dto;

public record EnderecoDTO(
        String rua,
        String bairro,
        String numero,
        String complemento,
        String cidade,
        String uf,
        String cep

) {
    public String formatarParaBanco() {
        var texto = rua + ", " + numero;

        if (complemento != null && !complemento.isBlank()) {
            texto += " - " + complemento;
        }

        texto += " - " + bairro + ", " + cidade + " - " + uf + cep;

        return texto;
    }
}
