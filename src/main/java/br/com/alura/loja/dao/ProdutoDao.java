package br.com.alura.loja.dao;

import br.com.alura.loja.modelo.Produto;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

public class ProdutoDao {
    private EntityManager entityManager;

    public ProdutoDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void cadastrar(Produto produto) {
        this.entityManager.persist(produto);
    }

    public void atualizar(Produto produto) {
        // Coloca o objeto no estado management (não é ele que realmente atualiza)
        // Qualquer objeto management se atualiza automaticamente
        this.entityManager.merge(produto);
    }

    public void remover(Produto produto) {
        // Precisa reatribuir o merge para pegar o objeto no estado management
        produto = this.entityManager.merge(produto);
        this.entityManager.remove(produto);
    }

    public Produto buscarPorId(Long id) {
        return this.entityManager.find(Produto.class, id);
    }

    public List<Produto> buscarTodos() {
        String jpql = "SELECT p FROM Produto p";
        return this.entityManager.createQuery(jpql, Produto.class).getResultList();
    }

    public List<Produto> buscarPorNome(String nome) {
        // :nome pode ser substituido por ?1 , ?2 ... (no setParameter passa um int com a posicao)
        String jpql = "SELECT p FROM Produto p WHERE p.nome = :nome";
        return this.entityManager.createQuery(jpql, Produto.class)
                .setParameter("nome", nome)
                .getResultList();
    }

    public List<Produto> buscarPorNomeCategoria(String categoriaNome) {
        // O join pode ficar implicito no JPQL
        String jpql = "SELECT p FROM Produto p WHERE p.categoria.nome = :nome";
        return this.entityManager.createQuery(jpql, Produto.class)
                .setParameter("nome", categoriaNome)
                .getResultList();
    }

    public BigDecimal buscarPrecoProdutoPorNome(String nome) {
        String jpql = "SELECT p.preco FROM Produto p WHERE p.nome = :nome";
        return this.entityManager.createQuery(jpql, BigDecimal.class)
                .setParameter("nome", nome)
                .getSingleResult();
    }
}
