package br.senac.rj.crud.tool;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL) // Inclui apenas campos não nulos no JSON
public class ApiResponse<T> { // Usamos um tipo genérico <T> para o campo 'data'

    private String status;
    private int code;
    private String message;
    private T data; // Onde os dados (JSON com dados) serão colocados

    // Construtor para sucesso sem dados
    public ApiResponse(HttpStatus httpStatus, String message) {
        this.status = "success";
        this.code = httpStatus.value();
        this.message = message;
        this.data = null; // Explicitamente nulo para não aparecer no JSON
    }

    // Construtor para sucesso com dados
    public ApiResponse(HttpStatus httpStatus, String message, T data) {
        this.status = "success";
        this.code = httpStatus.value();
        this.message = message;
        this.data = data;
    }

    // Construtor para falha sem dados
    public ApiResponse(HttpStatus httpStatus, String message, boolean isError) {
        this.status = "error";
        this.code = httpStatus.value();
        this.message = message;
        this.data = null; // Explicitamente nulo
    }

    // Construtor para falha com dados
    public ApiResponse(HttpStatus httpStatus, String message, T data, boolean isError) {
        this.status = "error";
        this.code = httpStatus.value();
        this.message = message;
        this.data = data;
    }

    // --- Métodos estáticos para facilitar a criação das respostas ---

    // Sucesso sem dados
    public static <T> ApiResponse<T> success(HttpStatus httpStatus, String message) {
        return new ApiResponse<>(httpStatus, message);
    }

    // Sucesso com dados
    public static <T> ApiResponse<T> success(HttpStatus httpStatus, String message, T data) {
        return new ApiResponse<>(httpStatus, message, data);
    }

    // Falha sem dados
    public static <T> ApiResponse<T> error(HttpStatus httpStatus, String message) {
        return new ApiResponse<>(httpStatus, message, true); // O 'true' indica que é um erro
    }

    // Falha com dados
    public static <T> ApiResponse<T> error(HttpStatus httpStatus, String message, T data) {
        return new ApiResponse<>(httpStatus, message, data, true); // O 'true' indica que é um erro
    }
}