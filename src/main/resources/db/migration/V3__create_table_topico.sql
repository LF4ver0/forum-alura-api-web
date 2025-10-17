CREATE TABLE topico (
  id BIGINT NOT NULL auto_increment,
  titulo VARCHAR(50) NOT NULL,
  mensagem VARCHAR(300) NOT NULL,
  data_criacao TIMESTAMP NOT NULL,
  status VARCHAR(20) NOT NULL,
  curso_id BIGINT NOT NULL,
  autor_id BIGINT NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (curso_id) REFERENCES curso(id),
  FOREIGN KEY (autor_id) REFERENCES usuario(id)
);