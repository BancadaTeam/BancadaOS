package br.com.bancada_os.api.controller;

import br.com.bancada_os.api.dto.DadosTokenJWT;
import br.com.bancada_os.api.dto.UsuarioLoginDTO;
import br.com.bancada_os.api.dto.UsuarioRequestDTO;
import br.com.bancada_os.api.dto.UsuarioResponseDTO;
import br.com.bancada_os.api.model.Usuario;
import br.com.bancada_os.api.service.TokenService;
import br.com.bancada_os.api.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager manager;
    private final TokenService tokenService;
    private final UsuarioService service;

    public AuthController(AuthenticationManager manager, TokenService tokenService, UsuarioService service) {
        this.manager = manager;
        this.tokenService = tokenService;
        this.service = service;
    }

    @PostMapping("/cadastro")
    public ResponseEntity<UsuarioResponseDTO> cadastrar(@RequestBody @Valid UsuarioRequestDTO dados, UriComponentsBuilder uriBuilder) {
        var dto = service.cadastrar(dados);
        var uri = uriBuilder.path("/usuarios/{id}").buildAndExpand(dto.idUsuario()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @PostMapping("/login")
    public ResponseEntity<DadosTokenJWT> login(@RequestBody @Valid UsuarioLoginDTO dados) {
        var authenticationToken = new UsernamePasswordAuthenticationToken(dados.email(), dados.senha());
        var authentication = manager.authenticate(authenticationToken);
        var principal = (Usuario) authentication.getPrincipal();
        if (!(authentication.getPrincipal() instanceof Usuario usuario)) {
            throw new RuntimeException("Erro ao processar usuário autenticado");
        }

        var tokenJWT = tokenService.gerarToken(usuario);
        return ResponseEntity.ok(new DadosTokenJWT(tokenJWT));
    }

    @GetMapping
    public ResponseEntity<List<UsuarioResponseDTO>> listar() {
        return ResponseEntity.ok(service.listar());
    }


}
