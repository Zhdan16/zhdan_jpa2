package zhdans.jpa2;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import zhdans.jpa2.entity.Category;
import zhdans.jpa2.entity.Product;
import zhdans.jpa2.entity.Value;


public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("main");
        EntityManager manager = factory.createEntityManager();

        Product product = manager.find(Product.class, 1);
        System.out.println("Категория: " + product.getCategory().getName());
        System.out.println("Название: " + product.getName());
        System.out.println("Стоимость: " + product.getPrice());

        try {
            manager.getTransaction().begin();

            Category category = new Category();
            category.setName("Химия");
            manager.persist(category);

//            Category category = manager.find(Category.class, 5);
//            category.setName("Взрослые");
//
//            Category category = manager.find(Category.class, 5);
//            manager.remove(category);

            manager.getTransaction().commit();
        }catch (Exception e){
            manager.getTransaction().rollback();
        }

        manager.close();
        factory.close();
    }
}
