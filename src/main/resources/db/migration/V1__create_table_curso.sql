CREATE TABLE curso (
  id BIGINT NOT NULL auto_increment,
  nome VARCHAR(50) NOT NULL,
  categoria VARCHAR(50) NOT NULL,
  PRIMARY KEY (id)
);

INSERT INTO curso VALUES (1, 'Kotlin', 'Programacao');