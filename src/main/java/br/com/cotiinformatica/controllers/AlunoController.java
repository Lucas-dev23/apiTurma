package br.com.cotiinformatica.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cotiinformatica.dtos.AlunoPostRequestDto;
import br.com.cotiinformatica.dtos.AlunoPutRequestDto;
import br.com.cotiinformatica.entities.Aluno;
import br.com.cotiinformatica.repositories.AlunoRepository;

@RestController
@RequestMapping(value = "/api/alunos")
public class AlunoController {
	
	@PostMapping()
	public String post(@RequestBody AlunoPostRequestDto dto){
		try {
			Aluno aluno = new Aluno();
			
			aluno.setId(UUID.randomUUID());
			aluno.setNome(dto.getNome());
			aluno.setMatricula(dto.getMatricula());
			aluno.setCpf(dto.getCpf());
			
			AlunoRepository alunoRepository = new AlunoRepository();
			alunoRepository.insert(aluno);
			
			return "Aluno cadastrado com sucesso.";
		}
		catch(Exception e) {
			return e.getMessage();
		}
	}
	
	@PutMapping()
	public String put(@RequestBody AlunoPutRequestDto dto) {
		try {
			//consultar o aluno no Bd através do ID
			AlunoRepository alunoRepository = new AlunoRepository();
			Aluno aluno = alunoRepository.findById(dto.getId());
			
			//verificando se o aluno não foi encontrado
			if(aluno == null)
				throw new Exception 
				("Aluno não encontrado. Verifique o ID informado.");
			
			aluno.setNome(dto.getNome());
			aluno.setMatricula(dto.getMatricula());
			aluno.setCpf(dto.getCpf());
			
			alunoRepository.update(aluno);
			return "Aluno atualizado com sucesso.";
		}
		catch(Exception e) {
			return e.getMessage();
		}
	}
	
	@DeleteMapping("{id}")
	public String delete(@PathVariable("id") UUID id) {
		try {
			//consultar o aluno no Bd através do ID
			AlunoRepository alunoRepository = new AlunoRepository();
			Aluno aluno = alunoRepository.findById(id);
			
			if(aluno == null)
				throw new Exception
				("Aluno não encontrado. Verifique o ID informado.");
			
			alunoRepository.delete(aluno);
			
			return "Aluno excluído com sucesso.";
		}
		catch(Exception e) {
			return e.getMessage();
		}
	}
	
	@GetMapping()
	public List<Aluno> getAll() throws Exception{
		
		AlunoRepository alunoRepository = new AlunoRepository();
		return alunoRepository.findAll();
	}
	
	@GetMapping("{id}")
	public Aluno getById(@PathVariable("id") UUID id) throws Exception{
		
		AlunoRepository alunoRepository = new AlunoRepository();
		return alunoRepository.findById(id);
	}
}
