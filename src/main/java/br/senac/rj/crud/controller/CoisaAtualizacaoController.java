package br.senac.rj.crud.controller;

import br.senac.rj.crud.dto.CoisaRequestDTO;
import br.senac.rj.crud.model.Coisa;
import br.senac.rj.crud.repository.CoisaRepository;
import br.senac.rj.crud.tool.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class CoisaAtualizacaoController {

    @Autowired
    private CoisaRepository coisaRepository;

    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse<Coisa>> atualizarCoisa(@PathVariable Long id, @Valid @RequestBody CoisaRequestDTO coisaRequestDTO) {
        // 1. Busca a Coisa existente pelo ID
        Optional<Coisa> coisaOptional = coisaRepository.findById(id);

        // 2. Verifica se a Coisa existe E se seu status é ON
        if (coisaOptional.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(ApiResponse.error(HttpStatus.NOT_FOUND, "Coisa com o ID " + id + " não encontrada para atualização."));
        }

        Coisa coisaExistente = coisaOptional.get();
        if (coisaExistente.getStatus() == Coisa.Status.OFF) {
            // Se a coisa for encontrada, mas estiver com status OFF, impede a atualização.
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST) // Use BAD_REQUEST ou CONFLICT, pois a requisição está correta, mas a condição do recurso impede a operação.
                    .body(ApiResponse.error(HttpStatus.BAD_REQUEST, "A Coisa com o ID " + id + " está inativa e não pode ser atualizada."));
        }

        // 3. Atualiza os campos permitidos do objeto Coisa existente
        //    Os campos 'id', 'date_created' e 'status' não são atualizados aqui.
        coisaExistente.setName(coisaRequestDTO.getName());
        coisaExistente.setDescription(coisaRequestDTO.getDescription());
        coisaExistente.setLocation(coisaRequestDTO.getLocation());
        coisaExistente.setImageUrl(coisaRequestDTO.getImageUrl());
        coisaExistente.setPrice(coisaRequestDTO.getPrice());

        // 4. Salva a Coisa atualizada no banco de dados
        Coisa coisaAtualizada = coisaRepository.save(coisaExistente);

        // 5. Retorna a resposta de sucesso com a Coisa atualizada
        return ResponseEntity
                .ok(ApiResponse.success(HttpStatus.OK, "Coisa atualizada com sucesso!", coisaAtualizada));
    }
}
