package production.util;

import production.model.Cart;
import production.model.Item;

import java.time.LocalDateTime;
import java.util.Scanner;

import static production.util.InputMethodsUtil.itemPicker;

public class CartUtil {
    public static void cartPrint(Cart[] carts) {
        for (Cart cart : carts) {
            System.out.println(cart.getCartName());
            System.out.println("Items:");
            for (int i = 0; i < cart.getBoughtStuff().length; i++) {
                System.out.println(cart.getBoughtStuff()[i].getName() + " ");
            }
        }
    }

    public static Cart[] cartsInput(Scanner scanner, Item[] items) {
        System.out.print("Input number of wanted carts:");
        int numberOfCarts = scanner.nextInt();
        scanner.nextLine();
        Cart[] carts = new Cart[numberOfCarts];

        for (int i = 0; i < carts.length; i++) {
            System.out.print("Input cart name:");
            String name = scanner.nextLine();
            LocalDateTime timeOfBuying = LocalDateTime.now();
            System.out.print("Insert number of Items for cart:");
            int cartItemsNumber = scanner.nextInt();
            scanner.nextLine();
            Item[] cartItems = new Item[cartItemsNumber];
            Cart cart = new Cart(name, itemPicker(items, cartItems, scanner), timeOfBuying);
            carts[i] = cart;

        }
        System.out.print("Is that all or you want to remove something from carts(input 0 for edit):");
        if (scanner.nextLine().equals("0")) {
            return cartEditor(carts, scanner);
        } else {
            return carts;
        }

    }

    private static Cart[] cartEditor(Cart[] carts, Scanner scanner) {
        System.out.println("Welcome to cart editor!");

        for (Cart c : carts) {
            System.out.println("Cart name: "
                    .concat(c.getCartName())
                    .concat("\nItems:"));
            for (int i = 0; i < c.getBoughtStuff().length; i++) {
                System.out.println((i + 1) + "." + c.getBoughtStuff()[i].getName());
            }
            System.out.println("Pick item to delete or input (0) to leave it be:");
            int selectedItem = scanner.nextInt();
            scanner.nextLine();
            if(selectedItem==0)continue;
            Item[] selectedItems = new Item[c.getBoughtStuff().length - 1];
            for (int i = 0; i < c.getBoughtStuff().length; i++) {
                if (i != selectedItem - 1) {
                    selectedItems[i] = c.getBoughtStuff()[i];
                }
            }
            c.setBoughtStuff(selectedItems);
        }
        return carts;
    }
}
