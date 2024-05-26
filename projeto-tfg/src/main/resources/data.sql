-- Inserir dados na tabela TipoSituacao
INSERT INTO tipo_situacao (id_tipo_situacao, tipo) VALUES (1, 'Estado');
INSERT INTO tipo_situacao (id_tipo_situacao, tipo) VALUES (2, 'Ação');

-- Inserir dados na tabela Situacao
INSERT INTO situacao (id_situacao, situacao, tipo_situacao_id_tipo_situacao) VALUES (1, 'Logado', 1);
INSERT INTO situacao (id_situacao, situacao, tipo_situacao_id_tipo_situacao) VALUES (2, 'Deslogado', 1);
INSERT INTO situacao (id_situacao, situacao, tipo_situacao_id_tipo_situacao) VALUES (3, 'Ativo', 1);
INSERT INTO situacao (id_situacao, situacao, tipo_situacao_id_tipo_situacao) VALUES (4, 'Desativo', 1);
