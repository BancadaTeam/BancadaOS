package br.com.bancada_os.api.dto;

import br.com.bancada_os.api.model.Empresa;

public record EmpresaResponseDTO(
        Long idEmpresa,
        String nome,
        String cnpj,
        String telefone,
        String email,
        String endereco
) {
    public EmpresaResponseDTO(Empresa empresa) {
        this(empresa.getIdEmpresa(), empresa.getNome(), empresa.getCnpj(), empresa.getTelefone(), empresa.getEmail(), empresa.getEndereco());
    }
}
