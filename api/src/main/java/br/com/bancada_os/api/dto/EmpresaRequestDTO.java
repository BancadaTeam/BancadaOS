package br.com.bancada_os.api.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record EmpresaRequestDTO(
        @NotBlank(message = "A empresa deve ter um nome!")
        String nome,
        @NotBlank(message = "A empresa deve ter um CNPJ!")
        String cnpj,
        @NotBlank(message = "A empresa deve ter um endereço!")
        @Valid
        EnderecoDTO endereco,
        @NotBlank(message = "A empresa deve ter um telefone!")
        String telefone,
        @NotBlank(message = "A empresa deve ter um email!")
        @Email(message = "O email deve ser válido!")
        String email
) {
}
