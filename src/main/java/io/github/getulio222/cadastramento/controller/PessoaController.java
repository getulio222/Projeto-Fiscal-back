package io.github.getulio222.cadastramento.controller;

import io.github.getulio222.cadastramento.dto.PessoaDTORequest;
import io.github.getulio222.cadastramento.dto.PessoaDTOResponse;
import io.github.getulio222.cadastramento.service.PessoaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/pessoas")
public class PessoaController {

    private final PessoaService pessoaService;

    public PessoaController(PessoaService pessoaService) {
        this.pessoaService = pessoaService;
    }


    @PostMapping
    public ResponseEntity<PessoaDTOResponse> salvar(@RequestBody @Valid PessoaDTORequest dto) {
        PessoaDTOResponse response = pessoaService.salvar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


    @GetMapping
    public ResponseEntity<List<PessoaDTOResponse>> listar() {
        return ResponseEntity.ok(pessoaService.listar());
    }


    @GetMapping("/{id}")
    public ResponseEntity<PessoaDTOResponse> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(pessoaService.buscarPorId(id));
    }


    @GetMapping("/cpf/{cpf}")
    public ResponseEntity<PessoaDTOResponse> buscarPorCpf(@PathVariable String cpf) {
        return ResponseEntity.ok(pessoaService.buscarPorCpf(cpf));
    }


    @GetMapping("/nome")
    public ResponseEntity<List<PessoaDTOResponse>> buscarPorNome(@RequestParam String valor) {
        return ResponseEntity.ok(pessoaService.buscarPorNome(valor));
    }


    @PutMapping("/{id}")
    public ResponseEntity<PessoaDTOResponse> atualizar(
            @PathVariable Long id,
            @RequestBody @Valid PessoaDTORequest dto) {
        return ResponseEntity.ok(pessoaService.atualizar(id, dto));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        pessoaService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}