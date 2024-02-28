package br.com.cotiinformatica.entities;

import java.util.UUID;

import lombok.Data;

@Data
public class Aluno {
	private UUID id;
	private String nome;
	private String matricula;
	private String cpf;
}
