package com.br.socialbookapi.repository;

import com.br.socialbookapi.domain.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface LivrosRepository extends JpaRepository<Livro, Long> {

}
