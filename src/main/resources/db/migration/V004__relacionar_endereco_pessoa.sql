ALTER TABLE pessoas ADD id_endereco INT;
ALTER TABLE pessoas ADD CONSTRAINT fk_pessoa_endereco FOREIGN KEY (id_endereco) REFERENCES endereco (id);