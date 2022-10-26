package com.ds.tutorial.Repository;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ds.tutorial.Model.Comentario;

public interface ComentarioRepository extends JpaRepository<Comentario, Long> {
    List<Comentario> findByTutorialId(Long tutorialId);
    
    @Transactional
    void deleteByTutorialId(Long tutorialId);
}

