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

import com.ds.tutorial.Model.Tutorial;
import com.ds.tutorial.Repository.TutorialRepository;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/tutoriais")
@AllArgsConstructor
public class TutorialController {
    
    private TutorialRepository tutorialRepository;


    @GetMapping
    public List<Tutorial> getAllTutorial() {
            return tutorialRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Tutorial> getTutorialById(@PathVariable("id") Long id) {
        return  tutorialRepository.findById(id);
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED) 
    public Tutorial create (@RequestBody Tutorial tutorial) {
        return tutorialRepository.save(tutorial);
    }


    @PutMapping("/{id}")
    Optional<Object> update(@RequestBody Tutorial newTutorial, @PathVariable Long id) {
      
      return tutorialRepository.findById(id).
        map(tutorial -> {
          tutorial.setTitulo(newTutorial.getTitulo());
          tutorial.setDescricao(newTutorial.getDescricao());
          tutorial.setPublicado(newTutorial.getPublicado());
          return tutorialRepository.save(tutorial);
        });
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        tutorialRepository.deleteById(id);
    }
}