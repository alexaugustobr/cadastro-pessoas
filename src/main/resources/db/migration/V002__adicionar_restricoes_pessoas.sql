ALTER TABLE pessoas MODIFY nome varchar(50) NOT NULL;

ALTER TABLE pessoas MODIFY data_nascimento date NOT NULL;

ALTER TABLE pessoas MODIFY cpf varchar(20) NOT NULL;

ALTER TABLE pessoas ADD CONSTRAINT unique_cpf_pessoa UNIQUE (cpf);