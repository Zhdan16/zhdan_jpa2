package zhdans.jpa2;

import jakarta.persistence.*;
import zhdans.jpa2.entity.Product;

import java.util.Scanner;

public class IncreasePriceByCategory {

    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("main");
        EntityManager manager = factory.createEntityManager();
        // Введите название категории: Смартфоны
        // Введите процент увеличения стоимости: 7
        TypedQuery<Product> all = manager.createQuery(
                "select p from Product p where p.category.name = ?1", Product.class
        );
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите название категории: ");
        String cat = scanner.nextLine();

        all.setParameter(1, cat);

        System.out.print("Введите процент увеличения стоимости: ");
        int increase = Integer.parseInt(scanner.nextLine());

        try {
            manager.getTransaction().begin();

            Query increaseProdPrice = manager.createQuery(
                    "update Product p set p.price = p.price + (p.price * ?1 / 100) where p.category.id = ?2"
            );

            increaseProdPrice.setParameter(1, increase);
            increaseProdPrice.setParameter(2, cat);
            increaseProdPrice.executeUpdate();


            manager.getTransaction().commit();
        }catch (Exception e){
            manager.getTransaction().rollback();
        }

        manager.close();
        factory.close();
    }
}