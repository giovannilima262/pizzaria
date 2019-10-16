
-- Observação; os dados estão sendo inseridos aqui, no entanto é importante lembrar
-- que o current_timestamp diferencia conforme o time zone, sendo assim é importante
-- inserir os dados pela aplicação ou setar o time zone correto.

-- Tamanhos
insert into tamanho (nome, valor, tempo_minuto, data_criacao)
values
('Pequena', 20, 15, current_timestamp);

insert into tamanho (nome, valor, tempo_minuto, data_criacao)
values
('Média', 30, 20, current_timestamp);

insert into tamanho (nome, valor, tempo_minuto, data_criacao)
values
('Grande', 40, 25, current_timestamp);


-- Sabores
insert into sabor (nome, tempo_minuto, data_criacao)
values
('Calabresa', 0, current_timestamp);

insert into sabor (nome, tempo_minuto, data_criacao)
values
('Marguerita', 0, current_timestamp);

insert into sabor (nome, tempo_minuto, data_criacao)
values
('portuguesa', 5, current_timestamp);


-- Adicionais
insert into adicional (nome, valor, tempo_minuto, data_criacao)
values
('Extra bacon', 3, 0, current_timestamp);

insert into adicional (nome, valor, tempo_minuto, data_criacao)
values
('Sem cebola', 0, 0, current_timestamp);

insert into adicional (nome, valor, tempo_minuto, data_criacao)
values
('Borda recheada', 5, 5, current_timestamp);

