package zhdans.jpa2;

import jakarta.persistence.*;
import zhdans.jpa2.entity.Category;
import zhdans.jpa2.entity.Option;
import zhdans.jpa2.entity.Product;
import zhdans.jpa2.entity.Value;

import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Update {
    public void start(){

            EntityManagerFactory factory = Persistence.createEntityManagerFactory("main");
            EntityManager manager = factory.createEntityManager();

            Scanner scanner = new Scanner(System.in);

            System.out.print("Введите id товара: ");
            Integer prod_id = Integer.parseInt(scanner.nextLine());

            TypedQuery<Value> all = manager.createQuery(
                    "select v from Value v where v.product.id = ?1 and v.option.id = ?2", Value.class
            );

            Product product = manager.find(Product.class, prod_id);
            try {
                manager.getTransaction().begin();

                // Если ничего не было введено, оставить старые данные без изменений.
                System.out.print("Введите название товара: ");
                String name = scanner.nextLine();

                if (!name.isEmpty()) {
                    product.setName(name);
                }

                System.out.print("Введите цену товара: ");
                String price = scanner.nextLine();

                if (!price.isEmpty()) {
                    product.setPrice(Integer.parseInt(price));
                }
                manager.persist(product);


                all.setParameter(1, prod_id);
                for (Option a : product.getCategory().getOptions()) {
                    System.out.print("Введите значение (" + a.getName() + ") : ");
                    String op = scanner.nextLine();

                    all.setParameter(2, a.getId());
                    if (!op.isEmpty()) {
                        try {
                            Value value = all.getSingleResult();

                            value.setOption(a);
                            value.setProduct(product);
                            value.setValue(op);
                            manager.persist(value);
                        }catch (Exception ex){
                            Value value = new Value();
                            value.setOption(a);
                            value.setProduct(product);
                            value.setValue(op);
                            manager.persist(value);
                        }

                    }

                }
                manager.getTransaction().commit();
            }catch (Exception e){
                manager.getTransaction().rollback();
            }

            manager.close();
            factory.close();
        }
}