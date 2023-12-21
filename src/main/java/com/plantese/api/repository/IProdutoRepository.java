package com.plantese.api.repository;

import com.plantese.api.models.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Repository
public interface IProdutoRepository extends JpaRepository<Produto, Long> {

    public Produto findProdutoByNome(String nome);

    public List<Produto> findProdutosByCategoria(String categoria);
}
