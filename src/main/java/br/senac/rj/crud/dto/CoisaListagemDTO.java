package br.senac.rj.crud.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CoisaListagemDTO {
    private Long id;
    private String name;
    private String imageUrl;
}