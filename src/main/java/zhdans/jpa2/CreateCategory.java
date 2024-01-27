package zhdans.jpa2;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import zhdans.jpa2.entity.Category;
import zhdans.jpa2.entity.Option;

import java.util.Scanner;

public class CreateCategory {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите название категории: ");
        String cat = scanner.nextLine();

        System.out.print("\nВведите характеристики категории: ");
        String[] opt = scanner.nextLine().split(", ");

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("main");
        EntityManager manager = factory.createEntityManager();

        Category category = new Category();
        try {
            manager.getTransaction().begin();

            category.setName(cat);
            manager.persist(category);

            for (String o : opt){
                Option option = new Option();
                option.setName(o);
                option.setCategory(category);
                manager.persist(option);
            }

            manager.getTransaction().commit();

        }catch (Exception e){
            manager.getTransaction().rollback();
        }

        manager.close();
        factory.close();
    }
}
