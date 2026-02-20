package br.com.bancada_os.api.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Endereco {
    private String rua;
    private String bairro;
    private String numero;
    private String complemento;
    private String cidade;
    private String estado;
    private String cep;
}
