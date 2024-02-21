package production.main;

import production.enume.Cities;
import production.exception.*;
import production.genericsi.FoodStore;
import production.genericsi.TehnicalStore;
import production.model.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import production.sort.GradeSorter;
import production.sort.ProductionSorter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import production.model.Category;


public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    /**
     * The main method that represents the entry point of the program. This method
     * initiates the program, collects various input data, and performs several
     * calculations and comparisons to find and display specific information about
     * items and products.
     *
     * @param args The command-line arguments passed to the program (not used in this program).
     */
    public static void main(String[] args) {
        logger.info("Program startup");
        List<Category> categories = categoriesViaFileInput();
        // Collect input data for various product types (Bananas, Kiwis, Laptops, Items)
        // and stores and factories that deal with these products.
        List<Grade> grades = gradeReader();
        List<Item> items = itemsInput(categories);
        List<Banana> bananas = bananasInput(categories);
        List<Kiwi> kiwis = kiwisInput(categories);
        List<Laptop> laptops = laptopsInput(categories);
        List<Item> allItems = new ArrayList<>(items);
        allItems.addAll(bananas);
        allItems.addAll(kiwis);
        allItems.addAll(laptops);
        List<Address> addressList = addressInput();
        List<Factory> factories = factoriesInput(addressList, allItems);
        List<Store> stores = storesInput(allItems);

        List<Item>articlesWithGrades= articleGradeConection(allItems,grades);
        categoriesfilterGrade(articlesWithGrades,categories);



        itemsWithGreaterAverageVolumeLambdaCalculation(items);
        itemsWithGreaterAverageVolumeWithoutLambdaCalculation(items);

        FoodStore<Edible> foodStore = new FoodStore<>("Tvornica"
                , Long.valueOf("13")
                , "www.foodara.com"
                , new LinkedHashSet<>(items)
                , edibleItemsInput(allItems));
        foodStore.setItems(itemSortingWithLambda(foodStore.getItems()));
        itemSortingWithoutLambda(foodStore.getItems());

        TehnicalStore<Technical> tehnicalStore = new TehnicalStore<>(
                "Trgovina tehnicka",
                Long.valueOf("41"),
                "www.tehnika.com"
                , new LinkedHashSet<>(items), technicalItemsInput(allItems));
        tehnicalStore.setItems(itemSortingWithLambda(tehnicalStore.getItems()));
        itemSortingWithoutLambda(tehnicalStore.getItems());
        try {
            allItemsSortingWithGreaterThanZeroDiscount(allItems);
        } catch (NoItemException ex) {
            System.out.println(ex.getMessage());
        }


        cheapestAndMostExpensiveItemPerCategory(categories, allItems);
        System.out.println(mostExpensiveAndCheapestItems(allItems));
        stores.add(tehnicalStore);
        stores.add(foodStore);
        stores.forEach(store ->
                System.out.println("Store:"
                        .concat(store.getName())
                        .concat(" Number of Items:")
                        .concat(String.valueOf(store.getItems().size()))));
        stores.forEach(store -> store
                .getItems()
                .forEach(Item::toString));
        serializeFactoriesAndStores(factories, stores);
        //average item number
        int averageItemNumber = stores.stream()
                .mapToInt(store -> store.getItems().size())
                .sum();
        averageItemNumber = averageItemNumber / stores.size();
        int finalAverageItemNumber = averageItemNumber;
        List<Store> storesWithAboveAverageItems = stores.stream()
                .filter(store -> store.getItems().size() > finalAverageItemNumber)
                .toList();
        System.out.println(storesWithAboveAverageItems);

        //System.out.println(findBiggestItemInFactories(factories));
        System.out.println(findCheapestItemInStores(stores));

        logger.info("Finding laptop with longest guarantee!");
        // Find and display information about the laptop with the longest guarantee.
        try {
            System.out.println(findLaptopWithLongestGuaranteeValue(laptops));
        } catch (NoItemException ex) {
            System.out.println(ex.getMessage());
        }
        System.out.println(mostExpensiveAndCheapestItems(allItems));
    }

    private static void categoriesfilterGrade(List<Item> items, List<Category> categories) {
        categories.forEach(
                category -> items.stream()
                        .filter(item -> item.getObject().equals(category))
                        .toList()
                        .stream()
                        .max(new GradeSorter())
                        .ifPresent(item -> System.out.println("In "
                                .concat(category.getName())
                                .concat(" category article with best grade is ")
                                .concat(item.getName())))

        );
        categories.forEach(
                category -> items.stream()
                        .filter(item -> item.getObject().equals(category))
                        .toList()
                        .stream()
                        .min(new GradeSorter())
                        .ifPresent(item -> System.out.println("In "
                                .concat(category.getName())
                                .concat(" category article with worst grade is ")
                                .concat(item.getName())))


        );
        Double averageGrade=items.stream()
                .mapToInt(item->item.getGradeValue().getGradeOfArticle()).average().getAsDouble();
        System.out.println("Items with more than average grades are:");
        categories.forEach(
                category -> items.stream()
                        .filter(item -> item.getObject().equals(category))
                        .toList()
                        .stream()
                        .filter(item-> item.getGradeValue().getGradeOfArticle()
                                .compareTo(averageGrade.intValue()) > 0)
                        .toList()
                        .forEach(System.out::println));



    }

    private static List<Item> articleGradeConection(List<Item> allItems, List<Grade> grades) {
        List<Item> itemsWithGrades=new ArrayList<>();
        for (Grade g:grades) {
            List<Item> itemsWithGrade;
             itemsWithGrade=allItems.stream().filter(item->item.getId().equals(g.getId())).toList();
             itemsWithGrades.addAll(itemsWithGrade);
        }
        return itemsWithGrades;
    }

    private static List<Grade> gradeReader() {
        List<Grade> grades = new ArrayList<>();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("dat/Review.txt"))) {
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                String gradeName= line;
                Long id = Long.parseLong(bufferedReader.readLine());
                Integer gradeValue = Integer.parseInt(bufferedReader.readLine());
                String gradeDescription= bufferedReader.readLine();
                grades.add(new Grade(gradeName,id,gradeValue,gradeDescription));

            }
        } catch (IOException e) {
            throw new RuntimeException();
        }
        return grades;
    }

    private static void serializeFactoriesAndStores(List<Factory> factories, List<Store> stores) {
        List<Factory> factoriesOverFiveItems = factories
                .stream()
                .filter(f -> f.getItems().size() > 4)
                .toList();
        List<Store> storesWithOverFiveItems = stores
                .stream()
                .filter(s -> s.getItems().size() > 4)
                .toList();
        try (ObjectOutputStream oos = new ObjectOutputStream(Files.newOutputStream(Paths.get("dat/binary.dat")))) {
            storesWithOverFiveItems.forEach(store ->
            {
                try {
                    oos.writeObject(store);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            });

            factoriesOverFiveItems.forEach(factory ->
            {
                try {
                    oos.writeObject(factory);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static List<Store> storesInput(List<Item> items) {
        List<Store> stores = new ArrayList<>();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("dat/stores.txt"))) {
            String line;
            Set<Item> storeItems = new HashSet<>();
            while ((line = bufferedReader.readLine()) != null) {
                Long id = Long.parseLong(line);
                String name = bufferedReader.readLine();
                String webAddress = bufferedReader.readLine();
                String itemsString = bufferedReader.readLine();
                String[] parts = itemsString.split(",");
                Arrays.stream(parts)
                        .forEach(p -> storeItems.add(items.get(Integer.parseInt(p) - 1)));
                stores.add(new Store(name, id, webAddress, storeItems));
            }
        } catch (IOException e) {
            throw new RuntimeException();
        }
        return stores;
    }

    private static List<Factory> factoriesInput(List<Address> addressList, List<Item> items) {
        List<Factory> factoryList = new ArrayList<>();


        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("dat/factories.txt"))) {
            String line;

            Set<Item> factoryItems = new HashSet<>();
            while ((line = bufferedReader.readLine()) != null) {
                Long id = Long.parseLong(line);
                String name = bufferedReader.readLine();
                String cityName = bufferedReader.readLine();
                Cities city = Arrays.stream(Cities.values())
                        .filter(c -> c.getCity().equals(cityName))
                        .findFirst()
                        .orElseThrow(NoCityException::new);
                Address address = addressList
                        .stream()
                        .filter(a -> a.getCity().equals(city))
                        .findFirst()
                        .orElseThrow(NoCityException::new);
                String itemsString = bufferedReader.readLine();
                String[] parts = itemsString.split(",");
                Arrays.stream(parts)
                        .forEach(p -> factoryItems.add(items.get(Integer.parseInt(p) - 1)));

                factoryList.add(new Factory(name, id, address, factoryItems));
            }


        } catch (IOException e) {
            throw new NoCityException(e.getMessage());
        }

        return factoryList;
    }

    private static List<Address> addressInput() {
        List<Address> addressList = new ArrayList<>();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("dat/addresses.txt"))) {

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                Long id = Long.parseLong(line);
                String name = bufferedReader.readLine();
                String street = bufferedReader.readLine();
                String houseNumber = bufferedReader.readLine();
                String selectedCityString = bufferedReader.readLine();
                Cities city = Arrays
                        .stream(Cities.values())
                        .filter(c -> c.getCity().equals(selectedCityString))
                        .findFirst()
                        .orElseThrow(NoCityException::new);

                Address newAddress = new Address(id, name, street, houseNumber, city);
                addressList.add(newAddress);
            }

        } catch (IOException e) {
            throw new NoCityException(e.getMessage());
        }

        return addressList;
    }

    private static List<Laptop> laptopsInput(List<Category> categories) {
        List<Laptop> laptops = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("dat/laptops.txt"))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                Item laptop = itemReader(bufferedReader, categories, line);
                Integer warnity = Integer.parseInt(bufferedReader.readLine());
                laptops.add(new Laptop(
                        laptop.getName(),
                        laptop.getId(),
                        laptop.getObject(),
                        laptop.getWidth(),
                        laptop.getHeight(),
                        laptop.getLength(),
                        laptop.getProductionCost(),
                        laptop.getSellingPrice(),
                        laptop.getDiscountAmount(),
                        warnity,
                        laptop.getGradeValue()
                ));
            }
        } catch (IOException e) {
            throw new FileErrorException(e.getMessage());
        }
        return laptops;
    }

    private static List<Kiwi> kiwisInput(List<Category> categories) {
        List<Kiwi> kiwis = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("dat/kiwis.txt"))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                Item kiwi = itemReader(bufferedReader, categories, line);
                BigDecimal weight = BigDecimal.valueOf(Double.parseDouble(bufferedReader.readLine()));
                kiwis.add(new Kiwi(
                        kiwi.getName(),
                        kiwi.getId(),
                        kiwi.getObject(),
                        kiwi.getWidth(),
                        kiwi.getHeight(),
                        kiwi.getLength(),
                        kiwi.getProductionCost(),
                        kiwi.getSellingPrice(),
                        kiwi.getDiscountAmount(),
                        weight,
                        kiwi.getGradeValue()

                ));
            }
        } catch (IOException e) {
            throw new FileErrorException(e.getMessage());
        }
        return kiwis;
    }


    private static List<Category> categoriesViaFileInput() throws RuntimeException {
        List<Category> categories = new ArrayList<>();
        String absolutehPath = "C:/Users/Korisnik/coreJavaLabs/Bencic-6/dat/categories.txt";
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(absolutehPath))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                Long id = Long.parseLong(bufferedReader.readLine());
                String caregoryDescription = bufferedReader.readLine();
                categories.add(new Category(caregoryDescription, id, line));
            }

        } catch (IOException e) {
            throw new FileErrorException(e.getMessage());
        }
        categories.forEach(category -> System.out.println(category.getName()));
        return categories;
    }

    private static List<Item> itemsInput(List<Category> categories) throws FileErrorException {
        List<Item> items = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("dat/items.txt"))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                items.add(itemReader(bufferedReader, categories, line));
            }
        } catch (IOException e) {
            throw new FileErrorException(e.getMessage());
        }
        return items;
    }

    private static List<Banana> bananasInput(List<Category> categories) {
        List<Banana> bananas = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("dat/bananas.txt"))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                Item banana = itemReader(bufferedReader, categories, line);
                BigDecimal weight = BigDecimal.valueOf(Double.parseDouble(bufferedReader.readLine()));
                bananas.add(new Banana(
                        banana.getName(),
                        banana.getId(),
                        banana.getObject(),
                        banana.getWidth(),
                        banana.getHeight(),
                        banana.getLength(),
                        banana.getProductionCost(),
                        banana.getSellingPrice(),
                        banana.getDiscountAmount(),
                        weight,
                        banana.getGradeValue()
                ));
            }
        } catch (IOException e) {
            throw new FileErrorException(e.getMessage());
        }
        return bananas;
    }

    private static Item itemReader(BufferedReader bufferedReader, List<Category> categories, String itemName)
            throws IOException, NoCategoryException {
        Long id = Long.parseLong(bufferedReader.readLine());
        String categoryName = bufferedReader.readLine();

        Category itemCategory = categories.stream()
                .filter(category -> category.getName().equals(categoryName))
                .findFirst()
                .orElseThrow(() -> new NoCategoryException("No category match found"));

        BigDecimal width = BigDecimal.valueOf(Double.parseDouble(bufferedReader.readLine()));
        BigDecimal height = BigDecimal.valueOf(Double.parseDouble(bufferedReader.readLine()));
        BigDecimal length = BigDecimal.valueOf(Double.parseDouble(bufferedReader.readLine()));
        BigDecimal productionCost = BigDecimal.valueOf(Double.parseDouble(bufferedReader.readLine()));
        BigDecimal sellingPrice = BigDecimal.valueOf(Double.parseDouble(bufferedReader.readLine()));
        BigDecimal discountAmount = BigDecimal.valueOf(Double.parseDouble(bufferedReader.readLine()));

        return new Item(itemName, id, itemCategory, width, height, length, productionCost, sellingPrice,
                new Discount(discountAmount),new Grade("",Long.valueOf("0"),0,""));
    }


    private static List<Item> allItemsSortingWithGreaterThanZeroDiscount(List<Item> allItems) throws NoItemException {
        return allItems.stream()
                .filter(item -> Optional.of(item.getDiscountAmount().discountAmount())
                        .map(discount -> discount.compareTo(BigDecimal.ZERO) > 0)
                        .orElseThrow(() -> new NoItemException("No items with discount found")))
                .toList();
    }

    private static void itemsWithGreaterAverageVolumeWithoutLambdaCalculation(List<Item> items) {
        long startTime = System.nanoTime();
        BigDecimal averageSum = BigDecimal.ZERO;
        for (int i = 0; i < items.size(); i++) {
            averageSum = averageSum.add(volume(items.get(i)));
        }
        averageSum = averageSum.divide(BigDecimal.valueOf(items.size()), RoundingMode.HALF_UP);
        List<Item> itemsWithGreaterAverageVolume = new ArrayList<>();
        for (Item item : items) {
            if (volume(item).compareTo(averageSum) > 0) {
                itemsWithGreaterAverageVolume.add(item);
            }
        }
        long endTime = System.nanoTime();
        System.out.println("Time for comparing volume without lambda is:" + (endTime - startTime));
    }

    private static BigDecimal volume(Item item) {
        return item.getHeight().
                multiply(item.getLength()).multiply(item.getWidth());
    }

    private static void itemSortingWithoutLambda(Set<Item> items) {
        long startTime = System.nanoTime();
        Comparator<Item> itemComparator = Comparator.comparing(Item::volumeOfItemCalculation);
        List<Item> itemsForSort = new ArrayList<>(items);
        itemsForSort.sort(itemComparator);
        LinkedHashSet<Item> sortedItems = new LinkedHashSet<>(itemsForSort);
        long endTime = System.nanoTime();
        System.out.println("Time for sorting items by volume without lambda is:" + (endTime - startTime));
    }

    private static LinkedHashSet<Item> itemSortingWithLambda(Set<Item> items) {
        long startTime = System.nanoTime();
        LinkedHashSet<Item> sortedItems = items
                .stream()
                .sorted(Comparator.comparing(Item::volumeOfItemCalculation))
                .collect(Collectors.toCollection(LinkedHashSet::new));
        long endTime = System.nanoTime();
        System.out.println("Time for sorting with lambda:" + (endTime - startTime));
        return sortedItems;
    }

    private static void itemsWithGreaterAverageVolumeLambdaCalculation(List<Item> items) {
        long startTime = System.nanoTime();
        BigDecimal averageVolume = items
                .stream()
                .map(Item::volumeOfItemCalculation)
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .divide(BigDecimal.valueOf(items.size()), RoundingMode.HALF_UP);
        List<Item> itemsAboveAveraagePrice = items
                .stream()
                .filter(item -> Item.volumeOfItemCalculation(item).compareTo(averageVolume) > 0)
                .toList();
        System.out.println(
                itemsAboveAveraagePrice.stream()
                        .map(Item::getSellingPrice)
                        .reduce(BigDecimal.ZERO, BigDecimal::add)
                        .divide(BigDecimal.valueOf(itemsAboveAveraagePrice.size()), RoundingMode.HALF_UP));
        long endTime = System.nanoTime();
        System.out.println("Lambda time for sorting items with greater average volume:"
                .concat(String.valueOf(endTime - startTime)));
    }

    private static List<Edible> edibleItemsInput(List<Item> items) {
        List<Edible> edibleItems = new ArrayList<>();
        items.stream()
                .filter(Edible.class::isInstance)
                .map(Edible.class::cast)
                .forEach(edibleItems::add);
        return edibleItems;
    }

    private static List<Technical> technicalItemsInput(List<Item> items) {
        List<Technical> techItems = new ArrayList<>();
        items.stream()
                .filter(Technical.class::isInstance)
                .map(Technical.class::cast)
                .forEach(techItems::add);
        return techItems;
    }


    private static Map<Category, List<Item>> cheapestAndMostExpensiveItemPerCategory
            (List<Category> categories, List<Item> allItems) {
        Map<Category, List<Item>> categoryItemMap = new HashMap<>();
        for (Category category : categories) {
            categoryItemMap.put(category, new ArrayList<>());
            for (Item item : allItems) {
                if (item.getObject().equals(category)) {
                    categoryItemMap.get(category).add(item);
                }
            }
            System.out.println(findCheapestAndMostExpensiveArticlePerCategory(category, categoryItemMap));
        }
        return categoryItemMap;
    }

    private static String findCheapestAndMostExpensiveArticlePerCategory
            (Category category, Map<Category, List<Item>> itemsOfOneCategory) {
        itemsOfOneCategory.get(category).sort(new ProductionSorter());
        if (itemsOfOneCategory.get(category).isEmpty()) {
            return "This "
                    .concat(category.getName())
                    .concat(" category contains no item");
        } else {
            return "Most expensive article in "
                    .concat(category.getName())
                    .concat(" category is ")
                    .concat(itemsOfOneCategory.get(category).getFirst().getName())
                    .concat("and the cheapest article is ")
                    .concat(itemsOfOneCategory.get(category).getLast().getName());
        }
    }

    /**
     * Finds the laptop with the longest guarantee among an array of laptops.
     *
     * @param laptops An array of Laptop objects to search for the one with the longest guarantee.
     * @return The Laptop object with the longest guarantee value.
     */
    private static String findLaptopWithLongestGuaranteeValue(List<Laptop> laptops) throws NoItemException {
        return laptops.stream()
                .max(Comparator.comparing(Laptop::getYears))
                .map(laptop -> "Laptop with longest guarantee is "
                        .concat(laptop.getName())
                        .concat(" with guarantee of ")
                        .concat(String.valueOf(laptop.getYears())))
                .orElseThrow(() -> new NoItemException("No laptops found!"));
    }


    private static String mostExpensiveAndCheapestItems(List<Item> itemsToSearch) {
        if (itemsToSearch.isEmpty()) {
            return "No items found";
        } else {
            return "Most expensive item is "
                    .concat(itemsToSearch.getFirst().getName())
                    .concat("with cost of ")
                    .concat(itemsToSearch.getFirst().getSellingPrice().toString())
                    .concat("\nMost cheapest item is ")
                    .concat(itemsToSearch.getLast().getName())
                    .concat("with cost of ")
                    .concat(itemsToSearch.getLast().getSellingPrice().toString());
        }
    }


    /**
     * Finds and returns the cheapest item among a given array of Store objects.
     *
     * @param stores An array of Store objects from which to find the cheapest item.
     * @return The cheapest Item found among the Store objects.
     */

    private static String findCheapestItemInStores(List<Store> stores) {
        Optional<Item> cheapestItem = stores.stream().flatMap(s -> s.getItems().stream()).
                min(Comparator.comparing(Item::getSellingPrice));
        return cheapestItem.map(item -> "Cheapest item is " + item.getName() + " with cost of " +
                item.getSellingPrice() + " EUR").orElse("No item found");
    }


}
