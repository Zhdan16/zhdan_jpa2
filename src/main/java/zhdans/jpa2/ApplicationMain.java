package zhdans.jpa2;

import java.util.Scanner;

public class ApplicationMain {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true){
            System.out.print("\n- Создание товара [1]\n- Изменение товара [2]\n- Удаление товара [3]\nВыберите действие: ");
            int num = Integer.parseInt(scanner.nextLine());
            System.out.println();
            CreateProduct createProduct = new CreateProduct();
            Update update = new Update();
            DeleteValue deleteValue = new DeleteValue();

            if (num == 1){
                createProduct.start();
            }else if (num == 2){
                update.start();
            } else if (num == 3) {
                deleteValue.start();
            }
        }
    }
}
