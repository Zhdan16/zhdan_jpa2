package zhdans.jpa2;

import jakarta.persistence.*;

import java.util.Scanner;

public class DeleteValue {
    public void start (){
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("main");
        EntityManager manager = factory.createEntityManager();

        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите id товара который хотите удалить: ");
        int delId = Integer.parseInt(scanner.nextLine());


        try {
            manager.getTransaction().begin();

            Query deleteProduct = manager.createQuery(
                    "delete from Product p where id = ?1 "
            );
            deleteProduct.setParameter(1, delId);

            deleteProduct.executeUpdate();
            manager.getTransaction().commit();
        }catch (Exception e){
            manager.getTransaction().rollback();
        }

        try {
            manager.getTransaction().begin();

            Query deleteProduct = manager.createQuery(
                    "delete from Value v where product.id = ?1 "
            );
            deleteProduct.setParameter(1, delId);

            deleteProduct.executeUpdate();
            manager.getTransaction().commit();
        }catch (Exception e){
            manager.getTransaction().rollback();
        }

        manager.close();
        factory.close();
    }
}
