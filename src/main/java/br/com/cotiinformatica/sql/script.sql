create table aluno(
	id_do_aluno    		uuid 			primary key,
	nome_do_aluno		varchar(25)		not null,
	matricula			varchar(15)		not null,
	cpf					varchar(35)		not null
)

select * from aluno;