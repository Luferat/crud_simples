package br.senac.rj.crud.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CoisaRequestDTO {

    @NotBlank(message = "O nome da coisa é obrigatório e não pode ser vazio.")
    @Size(max = 255, message = "O nome não pode ter mais de 255 caracteres.")
    private String name;

    @NotBlank(message = "A descrição é obrigatória e não pode ser vazia.")
    private String description;

    private String location;

    private String imageUrl;

    @NotNull(message = "O preço é obrigatório.")
    private BigDecimal price;
}
