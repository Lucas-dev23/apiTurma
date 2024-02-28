package br.com.cotiinformatica.dtos;

import java.util.UUID;

import lombok.Data;

@Data
public class AlunoPutRequestDto {
	
	private UUID id;
	private String nome;
	private String matricula;
	private String cpf;
}
