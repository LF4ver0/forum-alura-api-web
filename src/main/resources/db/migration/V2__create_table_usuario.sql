CREATE TABLE usuario (
  id BIGINT NOT NULL auto_increment,
  nome VARCHAR(50) NOT NULL,
  email VARCHAR(50) NOT NULL,
  PRIMARY KEY (id)
);

INSERT INTO usuario VALUES (1, 'Ana da Silva', 'ana@email.com');