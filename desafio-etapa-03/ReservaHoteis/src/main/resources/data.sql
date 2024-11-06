
INSERT INTO hotel(id_hotel, nome) VALUES
(1, 'Central'),
(2, 'Plaza'),
(3, 'Executivo'),
(4, 'Golden');

INSERT INTO quarto (id_quarto, descricao, numero, status, fk_hotel) VALUES
(1, 'Quarto Simples com Vista para o Mar',          '101', 'DISPONIVEL', 1),
(2, 'Quarto Luxuoso com Banheira de Hidromassagem', '102', 'DISPONIVEL', 1),
(3, 'Suíte Elegante com Sala de Estar',             '103', 'DISPONIVEL', 1),

(4, 'Quarto Econômico com Cama de Casal',           '201', 'DISPONIVEL', 2),
(5, 'Quarto Superior com Varanda',                  '202', 'RESERVADO',  2),

(6, 'Quarto Clássico com Cama King Size',           '301', 'RESERVADO',  3);

INSERT INTO reserva (id_reserva, fk_quarto, status, data_inicio, data_prevista_fim, data_fim) VALUES
(1, 4, 'FINALIZADA', '2023-10-24', '2023-10-27', '2023-10-27'),
(2, 5, 'ATIVA',      '2023-10-21', '2023-10-23', null),
(3, 6, 'ATIVA',      '2023-10-20', '2023-10-22', null);
