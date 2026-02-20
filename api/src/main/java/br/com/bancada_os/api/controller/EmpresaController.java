package br.com.bancada_os.api.controller;

import br.com.bancada_os.api.dto.EmpresaRequestDTO;
import br.com.bancada_os.api.dto.EmpresaResponseDTO;
import br.com.bancada_os.api.service.EmpresaService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/empresa")
public class EmpresaController {

    public EmpresaService empresaService;

    public EmpresaController(EmpresaService empresaService) {
        this.empresaService = empresaService;
    }

    @PostMapping("/nova-empresa")
    public ResponseEntity<EmpresaResponseDTO> cadastrarEmpresa(@RequestBody @Valid EmpresaRequestDTO dados, UriComponentsBuilder uriBuilder) {
        var response = empresaService.criarNovaEmpresa(dados);
        var uri = uriBuilder.path("/empresas/{id}").buildAndExpand(response.idEmpresa()).toUri();
        return ResponseEntity.created(uri).body(response);
    }
}
