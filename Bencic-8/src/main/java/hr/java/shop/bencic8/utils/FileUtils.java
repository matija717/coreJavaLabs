package hr.java.shop.bencic8.utils;

import hr.java.shop.bencic8.production.enume.Cities;
import hr.java.shop.bencic8.production.exception.FileErrorException;
import hr.java.shop.bencic8.production.exception.NoCityException;
import hr.java.shop.bencic8.production.model.*;
import javafx.scene.control.Alert;

import java.io.*;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

import static hr.java.shop.bencic8.utils.FxDialogUtil.getAlert;

public class FileUtils {
    public static List<Factory> allFactories() {
        List<Address> addressList = addressInput();
        List<Item> items = allItems();
        return InputReaderUtil.readFactories(addressList, items);
    }
    public static List<BuyData> readBoughts(){
        List<BuyData> buyDataList=new ArrayList<>();
        List<Item> allItems=allItems();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("dat/invoice.txt"))) {
            String line;
            List<Item> itemsList = new ArrayList<>();
            while ((line = bufferedReader.readLine()) != null) {
                String name =line;
                Long id = Long.parseLong(bufferedReader.readLine());
                String itemsString = bufferedReader.readLine();
                String[] parts = itemsString.split(",");
                BigDecimal cost=new BigDecimal(bufferedReader.readLine());
                Arrays.stream(parts)
                        .forEach(p -> itemsList.add(allItems.get(Integer.parseInt(p) - 1)));
                buyDataList.add(new BuyData(name, id, itemsList,cost));
            }
        } catch (IOException e) {
            throw new FileErrorException();
        }
        return buyDataList;
    }
    public static void saveItemsToDat(List<BuyData> boughts){
        try (PrintWriter pw = new PrintWriter(new FileWriter("dat/invoice.txt"))) {
            for (BuyData b : boughts) {
                pw.println(b.getName());
                pw.println(b.getId());
                String items = b.getItems().stream()
                        .map(item -> String.valueOf(item.getId()))
                        .collect(Collectors.joining(","));
                pw.println(items);
                BigDecimal cost=b.getItems().stream()
                        .map(Item::getSellingPrice)
                        .reduce(BigDecimal.ZERO,BigDecimal::add);
                pw.println(cost);

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static List<Store> storesInput() {
        List<Store> stores = new ArrayList<>();
        List<Item> items = allItems();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("dat/stores.txt"))) {
            String line;
            Set<Item> storeItems = new HashSet<>();
            while ((line = bufferedReader.readLine()) != null) {
                String name =line;
                Long id = Long.parseLong(bufferedReader.readLine());
                String webAddress = bufferedReader.readLine();
                String itemsString = bufferedReader.readLine();
                String[] parts = itemsString.split(",");
                Arrays.stream(parts)
                        .forEach(p -> storeItems.add(items.get(Integer.parseInt(p) - 1)));
                stores.add(new Store(name, id, webAddress, storeItems));
            }
        } catch (IOException e) {
            throw new FileErrorException();
        }
        return stores;
    }

    public static void saveStores(List<Store> stores) {
        try (PrintWriter pw = new PrintWriter(new FileWriter("dat/stores.txt"))) {
            for (Store s : stores) {
                pw.println(s.getName());
                pw.println(s.getId());
                pw.println(s.getWebAddress());
                String items = s.getItems().stream()
                        .map(item -> String.valueOf(item.getId()))
                        .collect(Collectors.joining(","));
                pw.println(items);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<Address> addressInput() {
        List<Address> addressList = new ArrayList<>();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("dat/addresses.txt"))) {

            String line;
            while ((line = bufferedReader.readLine()) != null && !line.matches("^\\s*$")) {
                Long id = Long.parseLong(line);
                String street = bufferedReader.readLine();
                String houseNumber = bufferedReader.readLine();
                String selectedCityString = bufferedReader.readLine();
                Address newAddress = new Address(id, street, houseNumber, findCity(selectedCityString));
                addressList.add(newAddress);
            }

        } catch (IOException e) {
            throw new NoCityException(e.getMessage());
        }

        return addressList;
    }

    public static Cities findCity(String cityName) {
        return Arrays
                .stream(Cities.values())
                .filter(c -> c.getCityName().equals(cityName))
                .findFirst()
                .orElseThrow(NoCityException::new);
    }

    public static List<Item> allItems() {
        List<Item> allItems = new ArrayList<>();
        allItems.addAll(InputReaderUtil.readItems());
        allItems.addAll(InputReaderUtil.readBananas());
        allItems.addAll(InputReaderUtil.readLaptops());
        allItems.addAll(InputReaderUtil.readKiwis());
        return allItems;
    }

    public static List<Category> getCategories() {
        return InputReaderUtil.readCategories();
    }

    public static void saveCategories(List<Category> categories) {
        try (PrintWriter pw = new PrintWriter(new FileWriter("dat/categories.txt"))) {
            for (Category c : categories) {
                pw.println(c.getName());
                pw.println(c.getId());
                pw.println(c.getDescription());
            }
            Alert alert=alertForSucssesfullSaving();
            alert.showAndWait();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void saveItems(List<Item> items) {
        items = items.stream().filter(i -> i.getClass().equals(Item.class)).toList();
        try (PrintWriter pw = new PrintWriter(new FileWriter("dat/items.txt"))) {
            for (Item item : items) {
                itemDataPrint(item, pw);
            }
            Alert alert=alertForSucssesfullSaving();
            alert.showAndWait();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static Alert alertForSucssesfullSaving() {
        return getAlert(Alert.AlertType.INFORMATION, "Obavijest o spremannju",
                "Podaci uspjesno spremljni", "Cestitke!");
    }

    private static void itemDataPrint(Item item, PrintWriter pw) {
        pw.println(item.getName());
        pw.println(item.getId());
        pw.println(item.getObject().getName());
        pw.println(item.getWidth());
        pw.println(item.getHeight());
        pw.println(item.getLength());
        pw.println(item.getProductionCost());
        pw.println(item.getSellingPrice());
        pw.println(item.getDiscountAmount().discountAmount());
    }

    public static void saveBananas(List<Item> bananas) {
        bananas = bananas.stream().filter(i -> i.getClass().equals(Banana.class)).toList();
        try (PrintWriter pw = new PrintWriter(new FileWriter("dat/bananas.txt"))) {
            for (Item item : bananas) {
                itemDataPrint(item, pw);
                if (item instanceof Banana banana) pw.println(banana.getWeight());
            }
            Alert alert=alertForSucssesfullSaving();
            alert.showAndWait();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void saveKiwis(List<Item> kiwis) {
        kiwis = kiwis.stream().filter(i -> i.getClass().equals(Kiwi.class)).toList();
        try (PrintWriter pw = new PrintWriter(new FileWriter("dat/kiwis.txt"))) {
            for (Item item : kiwis) {
                itemDataPrint(item, pw);
                if (item instanceof Kiwi banana) pw.println(banana.getWeight());
            }
            Alert alert=alertForSucssesfullSaving();
            alert.showAndWait();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void saveLaptops(List<Item> laptops) {
        laptops = laptops.stream().filter(i -> i.getClass().equals(Laptop.class)).toList();
        try (PrintWriter pw = new PrintWriter(new FileWriter("dat/laptops.txt"))) {
            for (Item item : laptops) {
                itemDataPrint(item, pw);
                if (item instanceof Laptop laptop) pw.println(laptop.getYears());
            }
            Alert alert=alertForSucssesfullSaving();
            alert.showAndWait();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void saveFactories(List<Factory> factories) {
        try (PrintWriter pw = new PrintWriter(new FileWriter("dat/factories.txt"))) {
            for (Factory f : factories) {
                pw.println(f.getId());
                pw.println(f.getName());
                pw.println(f.getAddress().getCity().getCityName());
                String items = f.getItems().stream()
                        .map(item -> String.valueOf(item.getId()))
                        .collect(Collectors.joining(","));
                pw.println(items);
            }
            Alert alert=alertForSucssesfullSaving();
            alert.showAndWait();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
