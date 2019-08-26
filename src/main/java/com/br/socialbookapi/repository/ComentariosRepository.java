package com.br.socialbookapi.repository;

import com.br.socialbookapi.domain.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComentariosRepository extends JpaRepository<Comentario, Long> {
}
