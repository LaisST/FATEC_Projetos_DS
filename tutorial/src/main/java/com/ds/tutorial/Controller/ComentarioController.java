package com.ds.tutorial.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ds.tutorial.Model.Comentario;
import com.ds.tutorial.Repository.ComentarioRepository;
import com.ds.tutorial.Repository.TutorialRepository;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class ComentarioController {
    
    private TutorialRepository tutorialRepository;
    
    private ComentarioRepository comentarioRepository;
    

    @GetMapping("tutoriais/{tutorialId}/comentarios")
    public List<Comentario> getAllCommentsByTutorialId(@PathVariable(value = "tutorialId") Long tutorialId) {
            return comentarioRepository.findByTutorialId(tutorialId);
    }

    @GetMapping("/comentarios/{id}")
    public Optional<Comentario> getComentarioById(@PathVariable("id") Long id) {
        return  comentarioRepository.findById(id);
    }

    @PostMapping("/tutoriais/{tutorialId}/comentarios")
    @ResponseStatus(code = HttpStatus.CREATED) 
    public Comentario createComment(@PathVariable(value = "tutorialId") Long tutorialId, @RequestBody Comentario comentarioRequest) {
        Optional<Object> comentario = tutorialRepository.findById(tutorialId).map(tutorial -> {
            comentarioRequest.setTutorial(tutorial);
            return comentarioRepository.save(comentarioRequest);
        });
        return comentarioRepository.save(comentarioRequest);
    }

    @PutMapping("/comentarios/{id}")
    public Optional<Object> updateComment(@PathVariable("id") long id, @RequestBody Comentario newComentario) {
        return comentarioRepository.findById(id).
        map(comentario -> {
          comentario.setConteudo(newComentario.getConteudo());
          return comentarioRepository.save(comentario);
        });
    }

    @DeleteMapping("/comentarios/{id}")
    public void delete(@PathVariable Long id) {
        comentarioRepository.deleteById(id);
    }

    @DeleteMapping("/tutoriais/{tutorialId}/comentarios")
    public void deleteAllCommentsOfTutorial(@PathVariable(value = "tutorialId") Long tutorialId) {
       comentarioRepository.deleteByTutorialId(tutorialId);
    }

}


