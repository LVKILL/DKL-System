INSERT INTO reserva(
	id, fim_da_locacao, cliente_id, ativa, inicio_da_locacao, ambiente_id)
	VALUES (1001,'2019-11-06 14:50:00',1001,true, '2019-11-06 14:10:00', 1001);

INSERT INTO arma_locada(id, quantidade, reserva_id, arma_id)
	VALUES (1001, 1, 1001, 1001);