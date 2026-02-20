package br.com.bancada_os.api.service;

import br.com.bancada_os.api.dto.EmpresaRequestDTO;
import br.com.bancada_os.api.dto.EmpresaResponseDTO;
import br.com.bancada_os.api.model.Empresa;
import br.com.bancada_os.api.repository.EmpresaRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class EmpresaService {

    private final EmpresaRepository repository;

    public EmpresaService(EmpresaRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public EmpresaResponseDTO criarNovaEmpresa(EmpresaRequestDTO dados) {

        String enderecoPronto = dados.endereco().formatarParaBanco();
        var empresa = new Empresa(
                dados.nome(),
                dados.cnpj(),
                enderecoPronto,
                dados.telefone(),
                dados.email()
        );

        repository.save(empresa);
        return new EmpresaResponseDTO(empresa);
    }
}
