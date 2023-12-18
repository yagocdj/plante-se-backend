package com.plantese.api.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "tb_pedido")
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY,
        cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Cliente cliente;
    @OneToMany(fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "fk_id_pedido")
    private List<Produto> produtos;
    private Double valorTotal;

    public Pedido() { }

    public Pedido(
        Cliente cliente,
        List<Produto> produtos,
        Double valorTotal
    ) {
        this.cliente = cliente;
        this.produtos = produtos;
        this.valorTotal = valorTotal;
    }

    public void adicionarProduto(Produto produto) {
        this.produtos.add(produto);
    }
}
