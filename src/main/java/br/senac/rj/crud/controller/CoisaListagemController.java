package br.senac.rj.crud.controller;

import br.senac.rj.crud.dto.CoisaListagemDTO;
import br.senac.rj.crud.model.Coisa;
import br.senac.rj.crud.repository.CoisaRepository;
import br.senac.rj.crud.tool.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class CoisaListagemController {

    @Autowired
    private CoisaRepository coisaRepository;

    @GetMapping("/read")
    public ResponseEntity<ApiResponse<List<CoisaListagemDTO>>> listarTodasCoisas() {
        // 1. Buscar todas as Coisas ordenadas por 'date' de forma descendente
        //    A coluna no DB é 'date_created' e em JPA usamos o nome do atributo da entidade 'date'
        List<Coisa> todasAsCoisas = coisaRepository.findAll(Sort.by(Sort.Direction.DESC, "date"));

        // 2. Filtrar por status ON e mapear para CoisaListagemDTO
        List<CoisaListagemDTO> coisasFiltradasEDTO = todasAsCoisas.stream()
                .filter(coisa -> coisa.getStatus() == Coisa.Status.ON) // Filtrar por status ON
                .map(coisa -> new CoisaListagemDTO(coisa.getId(), coisa.getName(), coisa.getImageUrl())) // Mapear para DTO
                .collect(Collectors.toList()); // Coletar em uma nova lista

        if (coisasFiltradasEDTO.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(ApiResponse.error(HttpStatus.NOT_FOUND, "Nenhuma 'Coisa' com status ON encontrada."));
        } else {
            return ResponseEntity
                    .ok(ApiResponse.success(HttpStatus.OK, "Lista de 'Coisa's com status ON retornada com sucesso.", coisasFiltradasEDTO));
        }
    }
}
