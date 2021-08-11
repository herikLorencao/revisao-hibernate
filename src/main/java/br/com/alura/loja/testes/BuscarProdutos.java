package br.com.alura.loja.testes;

import br.com.alura.loja.dao.CategoriaDao;
import br.com.alura.loja.dao.ProdutoDao;
import br.com.alura.loja.modelo.Categoria;
import br.com.alura.loja.modelo.Produto;
import br.com.alura.loja.utils.JpaUtil;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

public class BuscarProdutos {
    public static void main(String[] args) {
        EntityManager entityManager = JpaUtil.getEntityManager();
        ProdutoDao produtoDao = new ProdutoDao(entityManager);
        CategoriaDao categoriaDao = new CategoriaDao(entityManager);

        Categoria categoria = new Categoria("Celular");
        Produto produto = new Produto("Xiaomi Redmi", "Celular barato", new BigDecimal(800), categoria);

        entityManager.getTransaction().begin();
        categoriaDao.cadastrar(categoria);
        produtoDao.cadastrar(produto);
        entityManager.getTransaction().commit();

        List<Produto> produtos = produtoDao.buscarPorNome("Xiaomi Redmi");
        produtos.forEach(p -> System.out.println(p.getNome()));

        List<Produto> produtosCategoria = produtoDao.buscarPorNomeCategoria("Celular");
        produtosCategoria.forEach(p -> System.out.println(p.getNome()));

        BigDecimal preco = produtoDao.buscarPrecoProdutoPorNome("Xiaomi Redmi");
        System.out.println("Preco do produto: " + preco);
    }
}
