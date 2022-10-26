package com.ds.tutorial.Repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ds.tutorial.Model.Tutorial;

public interface TutorialRepository extends JpaRepository<Tutorial, Long>{
    List<Tutorial> findByPublicado(Boolean publicado);
    List<Tutorial> findByTituloContaining(String title);
}

