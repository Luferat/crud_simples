package br.senac.rj.crud.repository; // Ajuste o pacote conforme a sua estrutura de pastas

import br.senac.rj.crud.model.Coisa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoisaRepository extends JpaRepository<Coisa, Long> {
    // Spring Data JPA magic! Métodos CRUD básicos já vêm "de graça" aqui.
}