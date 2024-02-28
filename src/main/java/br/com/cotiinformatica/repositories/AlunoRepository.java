package br.com.cotiinformatica.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import br.com.cotiinformatica.entities.Aluno;
import br.com.cotiinformatica.factories.ConnectionFactory;

public class AlunoRepository {

	public void insert(Aluno aluno) throws Exception{
		
		Connection connection = ConnectionFactory.getConnection();
		
		PreparedStatement statement = connection.prepareStatement
				("insert into aluno(id_do_aluno, nome_do_aluno, matricula, cpf) values(?, ?, ?, ?)");
		
		statement.setObject(1, aluno.getId());
		statement.setString(2, aluno.getNome());
		statement.setString(3, aluno.getMatricula());
		statement.setString(4, aluno.getCpf());
		statement.execute();
		
		connection.close();
	}
	
	public void update(Aluno aluno) throws Exception{
		
		Connection connection = ConnectionFactory.getConnection();
		
		PreparedStatement statement = connection.prepareStatement
				("update aluno set nome_do_aluno=?, matricula=?, cpf=? where id_do_aluno=?");
		

		statement.setString(1, aluno.getNome());
		statement.setString(2, aluno.getMatricula());
		statement.setString(3, aluno.getCpf());
		statement.setObject(4, aluno.getId());
		statement.execute();
		
		connection.close();		
	}
	
	public void delete(Aluno aluno) throws Exception {
	
		Connection connection = ConnectionFactory.getConnection();
		
		PreparedStatement statement = connection.prepareStatement
				("delete from aluno where id_do_aluno=?");
		
		statement.setObject(1, aluno.getId());
		statement.execute();
		
		connection.close();
	}
	
	public List<Aluno> findAll() throws Exception {
		
		Connection connection = ConnectionFactory.getConnection();
		
		PreparedStatement statement = connection.prepareStatement
				("select * from aluno order by nome_do_aluno");
		
		ResultSet resultSet = statement.executeQuery();
		
		List<Aluno> lista = new ArrayList<Aluno>();
		
		while(resultSet.next()) {
			
			Aluno aluno = new Aluno();
			
			aluno.setId(UUID.fromString(resultSet.getString
					("id_do_aluno")));
			aluno.setNome(resultSet.getString("nome_do_aluno"));
			aluno.setMatricula(resultSet.getString("matricula"));
			aluno.setCpf(resultSet.getString("cpf"));
			
			lista.add(aluno);
		}
		connection.close();
		return lista;
	}
	
	public Aluno findById(UUID id) throws Exception {
		Connection connection = ConnectionFactory.getConnection();
		PreparedStatement statement = connection.prepareStatement
				("select * from aluno where id_do_aluno=?");

		statement.setObject(1, id);
		
		ResultSet resultSet = statement.executeQuery();
		
		Aluno aluno = null;
		
		if (resultSet.next()) {
			aluno = new Aluno();
			aluno.setId(UUID.fromString(resultSet.getString("id_do_aluno")));
			aluno.setNome(resultSet.getString("nome_do_aluno"));
			aluno.setMatricula(resultSet.getString("matricula"));
			aluno.setCpf(resultSet.getString("cpf"));
		}
		connection.close();
		return aluno;	
	}
}
