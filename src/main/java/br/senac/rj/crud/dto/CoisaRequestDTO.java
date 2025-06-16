package br.senac.rj.crud.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import jakarta.validation.constraints.NotBlank; // <-- Mudança aqui!
import jakarta.validation.constraints.NotNull;  // <-- Mudança aqui!
import jakarta.validation.constraints.Size;    // <-- Mudança aqui!
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

    private String location; // Pode ser nulo

    private String imageUrl; // Pode ser nulo

    @NotNull(message = "O preço é obrigatório.")
    private BigDecimal price;
}