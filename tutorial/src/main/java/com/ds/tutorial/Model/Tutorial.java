package com.ds.tutorial.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Tutorial {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tutorial_generator")
  private Long id;

  @Column(length = 100, nullable = false)
  private String titulo;

  @Column(length = 250, nullable = false)
  private String descricao;

  private Boolean publicado;

}

/*
Observações:
A anotação @GeneratedValue é usada para definir a 
estratégia de geração da chave primária. 
GenerationType.SEQUENCE significa usar a sequência de 
banco de dados para gerar valores exclusivos.
Também indicamos o nome do gerador de chave primária. 
Se você não der o nome, o valor do id será gerado 
com a tabela hibernate_sequence 
(fornecida pelo provedor de persistência, para todas as entidades) por padrão.
*/