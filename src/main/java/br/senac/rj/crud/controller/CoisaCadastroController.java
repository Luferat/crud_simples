package br.senac.rj.crud.controller;

import br.senac.rj.crud.dto.CoisaRequestDTO;
import br.senac.rj.crud.model.Coisa;
import br.senac.rj.crud.repository.CoisaRepository;
import br.senac.rj.crud.tool.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/api")
public class CoisaCadastroController {

    @Autowired
    private CoisaRepository coisaRepository;

    @PostMapping("/create")
    public ResponseEntity<ApiResponse<Coisa>> cadastrarNovaCoisa(@Valid @RequestBody CoisaRequestDTO coisaRequestDTO) {
        // Cria uma nova instância de Coisa a partir do DTO de requisição
        Coisa novaCoisa = new Coisa();
        novaCoisa.setName(coisaRequestDTO.getName());
        novaCoisa.setDescription(coisaRequestDTO.getDescription());
        novaCoisa.setLocation(coisaRequestDTO.getLocation());
        novaCoisa.setImageUrl(coisaRequestDTO.getImageUrl());
        novaCoisa.setPrice(coisaRequestDTO.getPrice());

        // Define os campos automáticos
        novaCoisa.setDate(LocalDateTime.now()); // Data e hora atuais
        novaCoisa.setStatus(Coisa.Status.ON);  // Status padrão como ON

        // Salva a nova Coisa no banco de dados
        Coisa coisaSalva = coisaRepository.save(novaCoisa);

        // Retorna a resposta de sucesso com a Coisa recém-criada
        return ResponseEntity
                .status(HttpStatus.CREATED) // Código HTTP 201 (Created)
                .body(ApiResponse.success(HttpStatus.CREATED, "Coisa cadastrada com sucesso!", coisaSalva));
    }
}
