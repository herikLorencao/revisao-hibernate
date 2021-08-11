package br.com.alura.loja.dao;

import br.com.alura.loja.modelo.Categoria;

import javax.persistence.EntityManager;
import java.util.List;

public class CategoriaDao {
    private EntityManager entityManager;

    public CategoriaDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void cadastrar(Categoria categoria) {
        this.entityManager.persist(categoria);
    }

    public void atualizar(Categoria categoria) {
        this.entityManager.merge(categoria);
    }

    public void remover(Categoria categoria) {
        // Precisa reatribuir o merge para pegar o objeto no estado management
        categoria = this.entityManager.merge(categoria);
        this.entityManager.remove(categoria);
    }

    public Categoria buscarPorId(Long id) {
        return this.entityManager.find(Categoria.class, id);
    }

    public List<Categoria> buscarTodos() {
        String jqpl = "SELECT c FROM Categoria";
        return this.entityManager.createQuery(jqpl, Categoria.class).getResultList();
    }
}
