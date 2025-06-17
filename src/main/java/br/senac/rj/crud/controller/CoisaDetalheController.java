package br.senac.rj.crud.controller;

import br.senac.rj.crud.model.Coisa;
import br.senac.rj.crud.repository.CoisaRepository;
import br.senac.rj.crud.tool.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api")
public class CoisaDetalheController {

    @Autowired
    private CoisaRepository coisaRepository;

    @GetMapping("/read/{id}")
    public ResponseEntity<ApiResponse<Coisa>> buscarCoisaPorId(@PathVariable Long id) {
        // 1. Buscar a Coisa pelo ID
        Optional<Coisa> coisaOptional = coisaRepository.findById(id);

        // 2. Verificar se a Coisa existe e se seu status é ON
        if (coisaOptional.isPresent()) {
            Coisa coisa = coisaOptional.get();
            if (coisa.getStatus() == Coisa.Status.ON) {
                // Coisa encontrada e com status ON, retorna a Coisa completa
                return ResponseEntity
                        .ok(ApiResponse.success(HttpStatus.OK, "Coisa encontrada com sucesso.", coisa));
            } else {
                // Coisa encontrada, mas com status OFF
                return ResponseEntity
                        .status(HttpStatus.NOT_FOUND) // Ou HttpStatus.FORBIDDEN se quiser indicar acesso proibido para OFF
                        .body(ApiResponse.error(HttpStatus.NOT_FOUND, "Coisa com o ID " + id + " não encontrada ou está inativa."));
            }
        } else {
            // Coisa não encontrada para o ID informado
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(ApiResponse.error(HttpStatus.NOT_FOUND, "Coisa com o ID " + id + " não encontrada."));
        }
    }
}
