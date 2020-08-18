CREATE TABLE pessoas (
    id int primary key auto_increment,
    nome varchar(50),
    sexo varchar(10),
    email varchar(50),
    data_nascimento date,
    naturalidade varchar(20),
    nacionalidade varchar(20),
    cpf varchar(20),
    data_cadastro timestamp,
    data_alteracao timestamp default now()
);