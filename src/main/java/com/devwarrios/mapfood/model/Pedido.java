package com.devwarrios.mapfood.model;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.Getter;
import lombok.Setter;

@Document(collection = "pedidos")
@Getter
@Setter
public class Pedido {

	@Id
	private String id;

	@Field("pedido_id")
	private String pedidoId;

	@Field("clienteId")
	private String clienteId;

	@Field("estabelecimentoId")
	private String estabelecimentoId;

	@Field("itens")
	private List<ItemPedido> itens;

	@Field("status")
	private PedidoStatus status;

	@Field("entrega")
	private Entrega entrega;

	@Field("criado_em")
	private LocalDate criadoEm;

	@Field("atualizado_em")
	private LocalDate atualizadoEm;

	public Pedido() {
	}

	public Pedido(String pedidoId, String clienteId, String estabelecimentoId, List<ItemPedido> itens,
			LocalDate criadoEm, LocalDate atualizadoEm, PedidoStatus status) {
		this.pedidoId = pedidoId;
		this.clienteId = clienteId;
		this.estabelecimentoId = estabelecimentoId;
		this.itens = itens;
		this.status = status;
		this.criadoEm = criadoEm;
		this.atualizadoEm = atualizadoEm;
	}

	@Override
	public String toString() {
		return "Pedido [id=" + id + ", pedidoId=" + pedidoId + ", clienteId=" + clienteId + ", estabelecimentoId="
				+ estabelecimentoId + ", itens=" + itens + ", status=" + status + ", entrega=" + entrega + ", criadoEm="
				+ criadoEm + ", atualizadoEm=" + atualizadoEm + "]";
	}
}
