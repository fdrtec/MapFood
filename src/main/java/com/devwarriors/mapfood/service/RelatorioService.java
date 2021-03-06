package com.devwarriors.mapfood.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devwarriors.mapfood.model.Pedido;
import com.devwarriors.mapfood.model.PedidoStatus;
import com.devwarriors.mapfood.model.Relatorio;
import com.devwarriors.mapfood.repository.PedidoRepository;

import java.time.LocalDate;
import java.util.List;

@Service
public class RelatorioService {

	@Autowired
	private PedidoRepository pedidoRepository;

	public Relatorio geraRelatorio(String id, LocalDate dataInicial, LocalDate dataFinal) {

		Long totalEntregaEfetivada = buscaTotalEntregaEfetivada(id, dataInicial, dataFinal);
		Double totalQuilometragem = buscaTotalQuilometragemPercorrida(id, dataInicial, dataFinal);
		Double totalDuracaoEntrega = buscaTotalDuracaoEntrega(id, dataInicial, dataFinal);

		return new Relatorio(totalEntregaEfetivada, totalQuilometragem, totalDuracaoEntrega);
	}

	private Double buscaTotalDuracaoEntrega(String id, LocalDate dataInicial, LocalDate dataFinal) {
		List<Pedido> pedidos = pedidoRepository.findAllByEstabelecimentoIdAndCriadoEmBetween(id, dataInicial,
				dataFinal);

		return pedidos.stream().mapToDouble(p -> p.getEntrega().getDuracaoEmHoras()).sum();
	}

	private Double buscaTotalQuilometragemPercorrida(String id, LocalDate dataInicial, LocalDate dataFinal) {

		List<Pedido> pedidos = pedidoRepository.findAllByEstabelecimentoIdAndCriadoEmBetween(id, dataInicial,
				dataFinal);

		return pedidos.stream().mapToDouble(p -> p.getEntrega().getDistanciaPercorrida()).sum();
	}

	private Long buscaTotalEntregaEfetivada(String id, LocalDate dataInicial, LocalDate dataFinal) {
		return pedidoRepository.countByEstabelecimentoIdAndCriadoEmBetweenAndStatus(id, dataInicial, dataFinal,
				PedidoStatus.ENTREGUE);
	}

}
