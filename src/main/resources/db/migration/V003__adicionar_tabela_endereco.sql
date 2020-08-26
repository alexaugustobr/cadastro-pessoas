CREATE TABLE endereco (
	id int primary key auto_increment,
	cep varchar(20),
	logradouro varchar(100),
	bairro varchar(100),
	cidade varchar(50),
	estado varchar(20)
);