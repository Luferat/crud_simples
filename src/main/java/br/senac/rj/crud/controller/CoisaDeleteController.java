package br.senac.rj.crud.controller;

import br.senac.rj.crud.model.Coisa;
import br.senac.rj.crud.repository.CoisaRepository;
import br.senac.rj.crud.tool.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api")
public class CoisaDeleteController {

    @Autowired
    private CoisaRepository coisaRepository;

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse<Void>> deletarCoisa(@PathVariable Long id) {
        // 1. Busca a Coisa existente pelo ID
        Optional<Coisa> coisaOptional = coisaRepository.findById(id);

        // 2. Verifica se a Coisa existe
        if (coisaOptional.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(ApiResponse.error(HttpStatus.NOT_FOUND, "Coisa com o ID " + id + " não encontrada."));
        }

        Coisa coisaParaDeletar = coisaOptional.get();

        // 3. Verifica se a Coisa já está com status OFF
        if (coisaParaDeletar.getStatus() == Coisa.Status.OFF) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(ApiResponse.error(HttpStatus.BAD_REQUEST, "A Coisa com o ID " + id + " já está inativa (apagada logicamente)."));
        }

        // 4. Atualiza o status para OFF (soft delete)
        coisaParaDeletar.setStatus(Coisa.Status.OFF);
        coisaRepository.save(coisaParaDeletar); // Salva a alteração de status

        // 5. Retorna a resposta de sucesso (sem dados no corpo, por isso <Void>)
        return ResponseEntity
                .ok(ApiResponse.success(HttpStatus.OK, "Coisa com o ID " + id + " marcada como inativa (apagada logicamente) com sucesso."));
    }
}