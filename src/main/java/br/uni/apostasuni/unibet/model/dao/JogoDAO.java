package br.uni.apostasuni.unibet.model.dao;

import br.uni.apostasuni.unibet.model.Jogo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface JogoDAO extends JpaRepository<Jogo, Integer> {

    Object findById();

    List<Jogo> findByDataJogo(LocalDateTime dataJogo);
}
