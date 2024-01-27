package zhdans.jpa2;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import zhdans.jpa2.entity.Category;
import zhdans.jpa2.entity.Option;
import zhdans.jpa2.entity.Product;
import zhdans.jpa2.entity.Value;

import java.util.Scanner;

public class CreateProduct {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите id категории: ");
        Integer cat_id = scanner.nextInt();

        System.out.print("Введите название товара: ");
        String prod_name = scanner.next();

        System.out.print("Введите цену товара: ");
        Integer prod_price = scanner.nextInt();

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("main");
        EntityManager manager = factory.createEntityManager();

        Product product = new Product();
        try {
            manager.getTransaction().begin();

            Category category = manager.find(Category.class, cat_id);

            product.setCategory(category);
            product.setName(prod_name);
            product.setPrice(prod_price);

            manager.persist(product);

            for (Option a: category.getOptions()){
                System.out.print("Введите значение (" + a.getName() + ") : ");
                String op = scanner.next();

                Value value = new Value();
                value.setOption(a);
                value.setProduct(product);
                value.setValue(op);

                manager.persist(value);
            }

            manager.getTransaction().commit();
        }catch (Exception e){
            manager.getTransaction().rollback();
        }

        manager.close();
        factory.close();
    }
}
