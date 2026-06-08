package io.github.getulio222.projetofiscal.cliente.api;

import io.github.getulio222.projetofiscal.cliente.api.dto.ClienteDTORequest;
import io.github.getulio222.projetofiscal.cliente.api.dto.ClienteDTOResponse;
import io.github.getulio222.projetofiscal.cliente.application.ClienteService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @PostMapping
    public ResponseEntity<ClienteDTOResponse> salvar(@RequestBody @Valid ClienteDTORequest dto) {
        ClienteDTOResponse response = clienteService.salvar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<ClienteDTOResponse>> listar() {
        return ResponseEntity.ok(clienteService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteDTOResponse> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(clienteService.buscarPorId(id));
    }

    @GetMapping("/cpf-cnpj/{cpfCnpj}")
    public ResponseEntity<ClienteDTOResponse> buscarPorCpfCnpj(@PathVariable String cpfCnpj) {
        return ResponseEntity.ok(clienteService.buscarPorCpfCnpj(cpfCnpj));
    }

    @GetMapping("/nome")
    public ResponseEntity<List<ClienteDTOResponse>> buscarPorNome(@RequestParam String valor) {
        return ResponseEntity.ok(clienteService.buscarPorNome(valor));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteDTOResponse> atualizar(
            @PathVariable Long id,
            @RequestBody @Valid ClienteDTORequest dto) {

        return ResponseEntity.ok(clienteService.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        clienteService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}