package br.com.bancada_os.api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UsuarioLoginDTO(
        @NotBlank @Email String email,
        @NotBlank String senha
) {
}
