package production.util;

import production.model.Cart;
import production.model.Item;

import java.time.LocalDateTime;
import java.util.Scanner;

import static production.util.InputMethodsUtil.itemPicker;

public class CartUtil {
    public static void cartPrint(Cart[] carts) {


    }

    public static Cart[] cartsInput(Scanner scanner, Item[] items) {
        System.out.println("Input number of wanted carts");
        int numberOfCarts = scanner.nextInt();
        scanner.nextLine();
        Cart[] carts = new Cart[numberOfCarts];

        for (int i = 0; i < carts.length; i++) {
            LocalDateTime timeOfBuying = LocalDateTime.now();
            System.out.print("Insert number of Items for cart");
            int cartItemsNumber = scanner.nextInt();
            scanner.nextLine();
            Item[] cartItems = new Item[cartItemsNumber];
            Cart cart = new Cart(itemPicker(items, cartItems, scanner), timeOfBuying);
            carts[i] = cart;

        }
        System.out.println("Is that all or you want to remove something");
        carts = cartEditor(carts, scanner);


        return carts;
    }

    private static Cart[] cartEditor(Cart[] carts, Scanner scanner) {
        System.out.println("Welcome to cart editor");

        for (Cart c : carts) {
            for (int i = 0; i < c.getBoughtStuff().length; i++) {
                System.out.println((i + 1) + "." + c.getBoughtStuff()[i].getName());
            }
            System.out.println("Pick item to delete:");
            int selectedItem = scanner.nextInt();
            scanner.nextLine();
            Item[] selectedItems = new Item[c.getBoughtStuff().length - 1];
            for (int i = 0; i < c.getBoughtStuff().length; i++) {
                if (!(i == selectedItem - 1)) {
                    selectedItems[i] = c.getBoughtStuff()[i];
                }
            }
            c.setBoughtStuff(selectedItems);
        }
        return carts;
    }
}
