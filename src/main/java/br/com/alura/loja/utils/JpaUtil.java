package br.com.alura.loja.utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JpaUtil {
    public static final EntityManagerFactory FACTORY = Persistence.createEntityManagerFactory("loja");

    public static EntityManager getEntityManager() {
        return FACTORY.createEntityManager();
    }
}
