-- Limpa os dados da tabela
DELETE FROM COISA;

-- Reinicia o contador de ID da tabela COISA
-- Esta é a sintaxe específica para H2 para reiniciar a sequência de IDs
ALTER TABLE COISA ALTER COLUMN ID RESTART WITH 1;

-- Inserção de 11 registros com dados "fake"

-- Registro 1 (Status ON)
INSERT INTO COISA (date_created, name, description, location, image_url, price, status) VALUES
(TIMESTAMPADD(DAY, -CAST(RAND()*60 AS INT), CURRENT_TIMESTAMP), 'Meia Desparelhada', 'Aquele item misterioso que só aparece um pé.', 'No fundo da máquina de lavar', 'https://picsum.photos/201', 5.99, 'ON');

-- Registro 2 (Status ON)
INSERT INTO COISA (date_created, name, description, location, image_url, price, status) VALUES
(TIMESTAMPADD(DAY, -CAST(RAND()*60 AS INT), CURRENT_TIMESTAMP), 'Grampo de Cabelo Fugitivo', 'Ele estava aqui um segundo atrás!', 'Atrás do sofá', 'https://picsum.photos/198', 1.25, 'ON');

-- Registro 3 (Status OFF)
INSERT INTO COISA (date_created, name, description, location, image_url, price, status) VALUES
(TIMESTAMPADD(DAY, -CAST(RAND()*60 AS INT), CURRENT_TIMESTAMP), 'Caneta Mordida', 'Funciona, mas com personalidade.', 'Na bolsa do universo paralelo', 'https://picsum.photos/205', 0.75, 'OFF');

-- Registro 4 (Status ON)
INSERT INTO COISA (date_created, name, description, location, image_url, price, status) VALUES
(TIMESTAMPADD(DAY, -CAST(RAND()*60 AS INT), CURRENT_TIMESTAMP), 'Moeda Esquecida', 'Tesouro encontrado no bolso de uma calça velha.', 'Entre as almofadas', 'https://picsum.photos/195', 0.50, 'ON');

-- Registro 5 (Status ON)
INSERT INTO COISA (date_created, name, description, location, image_url, price, status) VALUES
(TIMESTAMPADD(DAY, -CAST(RAND()*60 AS INT), CURRENT_TIMESTAMP), 'Carregador Quebrado', 'Aquele que funciona só se você segurar torto.', 'Na gaveta da decepção', 'https://picsum.photos/203', 19.99, 'ON');

-- Registro 6 (Status ON)
INSERT INTO COISA (date_created, name, description, location, image_url, price, status) VALUES
(TIMESTAMPADD(DAY, -CAST(RAND()*60 AS INT), CURRENT_TIMESTAMP), 'Tampa de Tupperware Orfã', 'Sem a sua panela correspondente.', 'No armário da bagunça', 'https://picsum.photos/208', 2.50, 'ON');

-- Registro 7 (Status ON)
INSERT INTO COISA (date_created, name, description, location, image_url, price, status) VALUES
(TIMESTAMPADD(DAY, -CAST(RAND()*60 AS INT), CURRENT_TIMESTAMP), 'Pilhas Fracas', 'Prometem energia, entregam desilusão.', 'Dentro do controle remoto', 'https://picsum.photos/190', 8.00, 'ON');

-- Registro 8 (Status ON)
INSERT INTO COISA (date_created, name, description, location, image_url, price, status) VALUES
(TIMESTAMPADD(DAY, -CAST(RAND()*60 AS INT), CURRENT_TIMESTAMP), 'Manual de Instruções', 'Item misterioso que ninguém lê.', 'Debaixo da montanha de papel', 'https://picsum.photos/210', 0.00, 'ON');

-- Registro 9 (Status OFF)
INSERT INTO COISA (date_created, name, description, location, image_url, price, status) VALUES
(TIMESTAMPADD(DAY, -CAST(RAND()*60 AS INT), CURRENT_TIMESTAMP), 'Borra de Café Antiga', 'Lembrança de uma manhã passada.', 'No fundo da caneca favorita', 'https://picsum.photos/192', 0.01, 'OFF');

-- Registro 10 (Status ON)
INSERT INTO COISA (date_created, name, description, location, image_url, price, status) VALUES
(TIMESTAMPADD(DAY, -CAST(RAND()*60 AS INT), CURRENT_TIMESTAMP), 'Botão Solitário', 'Perdeu sua camisa há eras.', 'Na caixa de costura esquecida', 'https://picsum.photos/206', 0.10, 'ON');

-- Registro 11 (Status ON)
INSERT INTO COISA (date_created, name, description, location, image_url, price, status) VALUES
(TIMESTAMPADD(DAY, -CAST(RAND()*60 AS INT), CURRENT_TIMESTAMP), 'Etiqueta Adesiva Teimosa', 'Resiste a todas as tentativas de remoção.', 'Na sola do sapato novo', 'https://picsum.photos/197', 0.20, 'ON');
