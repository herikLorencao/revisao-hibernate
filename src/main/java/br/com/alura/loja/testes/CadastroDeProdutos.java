package br.com.alura.loja.testes;

import br.com.alura.loja.dao.CategoriaDao;
import br.com.alura.loja.dao.ProdutoDao;
import br.com.alura.loja.modelo.Categoria;
import br.com.alura.loja.modelo.Produto;
import br.com.alura.loja.utils.JpaUtil;

import javax.persistence.EntityManager;
import java.math.BigDecimal;

public class CadastroDeProdutos {
    public static void main(String[] args) {
        var produto = new Produto();
        produto.setNome("Xiaomi Redmi");
        produto.setDescricao("Celular custo-beneficio");
        produto.setPreco(new BigDecimal(800));
        Categoria celular = new Categoria("Celulares");

        EntityManager entityManager = JpaUtil.getEntityManager();
        ProdutoDao produtoDao = new ProdutoDao(entityManager);
        CategoriaDao categoriaDao = new CategoriaDao(entityManager);

        entityManager.getTransaction().begin();
        categoriaDao.cadastrar(celular);
        produtoDao.cadastrar(produto);

        celular.setNome("Xiaomi 2");

        // Atualiza dados antes do commit
        entityManager.flush();
        // Coloca os objetos como detached
        entityManager.clear();

        // Não atualiza o objeto e sim coloca ele no estado management (o update é automatico nesse estado)
        entityManager.merge(celular);
        celular.setNome("Xiaomi Redmi 5");

        entityManager.getTransaction().commit();
        // Fecha conexao e retorno objetos para detached
        entityManager.close();
    }
}
