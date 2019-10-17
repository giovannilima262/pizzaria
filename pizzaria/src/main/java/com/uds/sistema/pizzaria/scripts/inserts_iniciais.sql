-- Tamanhos
insert into tamanho (nome, valor, tempo_minuto, data_criacao)
values
('PEQUENA', 20, 15, TO_TIMESTAMP('2019-10-17 00:21:20','YYYY-MM-DD HH24:MI:SS'));

insert into tamanho (nome, valor, tempo_minuto, data_criacao)
values
('MEDIA', 30, 20, TO_TIMESTAMP('2019-10-17 00:21:20','YYYY-MM-DD HH24:MI:SS'));

insert into tamanho (nome, valor, tempo_minuto, data_criacao)
values
('GRANDE', 40, 25, TO_TIMESTAMP('2019-10-17 00:21:20','YYYY-MM-DD HH24:MI:SS'));


-- Sabores
insert into sabor (nome, tempo_minuto, data_criacao)
values
('CALABRESA', 0, TO_TIMESTAMP('2019-10-17 00:21:20','YYYY-MM-DD HH24:MI:SS'));

insert into sabor (nome, tempo_minuto, data_criacao)
values
('MARGUERITA', 0, TO_TIMESTAMP('2019-10-17 00:21:20','YYYY-MM-DD HH24:MI:SS'));

insert into sabor (nome, tempo_minuto, data_criacao)
values
('PORTUGUESA', 5, TO_TIMESTAMP('2019-10-17 00:21:20','YYYY-MM-DD HH24:MI:SS'));


-- Adicionais
insert into adicional (nome, valor, tempo_minuto, data_criacao)
values
('EXTRA_BACON', 3, 0, TO_TIMESTAMP('2019-10-17 00:21:20','YYYY-MM-DD HH24:MI:SS'));

insert into adicional (nome, valor, tempo_minuto, data_criacao)
values
('SEM_CEBOLA', 0, 0, TO_TIMESTAMP('2019-10-17 00:21:20','YYYY-MM-DD HH24:MI:SS'));

insert into adicional (nome, valor, tempo_minuto, data_criacao)
values
('BORDA_RECHEADA', 5, 5, TO_TIMESTAMP('2019-10-17 00:21:20','YYYY-MM-DD HH24:MI:SS'));
