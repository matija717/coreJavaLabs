package hr.java.shop.bencic8;

import hr.java.shop.bencic8.production.model.BuyData;
import hr.java.shop.bencic8.production.model.Item;
import hr.java.shop.bencic8.production.model.NamedEntity;
import hr.java.shop.bencic8.production.model.Store;
import hr.java.shop.bencic8.utils.FileUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static hr.java.shop.bencic8.utils.FileUtils.readBoughts;
import static hr.java.shop.bencic8.utils.FileUtils.saveItemsToDat;

public class BuyItemsFromStoreScreenController {
    private List<BuyData> buyDataList;
    @FXML
    private ChoiceBox<String> chosenStoreChoiceBox;
    @FXML
    private ChoiceBox<String> chosenItemChoiceBox;

    public void initialize(){
        chosenStoreChoiceBox.setItems(initializeChosenStoreChoiceBox());
    }
    public void chooseStore(){
        List<Store> stores=FileUtils.storesInput();
        if(Optional.ofNullable(chosenStoreChoiceBox.getSelectionModel().getSelectedItem()).isPresent()){
            initializeItemChoiceBox(stores
                    .stream()
                    .filter(store->store.getName().equals(chosenStoreChoiceBox.getSelectionModel().getSelectedItem()))
                    .findFirst()
                    .get());
            
        }
    }
    public void addItemsToCart(){
        List<Item> itemsToCart=new ArrayList<>();
        List<Item> allItems=FileUtils.allItems();
        try{
        if(Optional.ofNullable(chosenItemChoiceBox.getSelectionModel().getSelectedItem()).isPresent()){
            Item item=allItems.stream()
                    .filter(i->i.getName().equals(chosenItemChoiceBox.getSelectionModel().getSelectedItem()))
                    .findFirst()
                    .get();
            itemsToCart.add(item);
        }}
        catch (RuntimeException e){
            //Error message
        }
        buyDataList=readBoughts();
        BigDecimal cost=itemsToCart.stream().map(Item::getSellingPrice)
                        .reduce(BigDecimal.ZERO,BigDecimal::add);
        buyDataList.add(new BuyData(chosenItemChoiceBox.getSelectionModel().getSelectedItem(),iDgen(buyDataList),itemsToCart,cost));

    }
    public void saveBought(){
        saveItemsToDat(buyDataList);
    }
    public Long iDgen(List<BuyData> data){
        Optional<Long> buyLongId= data.stream()
                .map(NamedEntity::getId)
                .max(Long::compareTo);
        return buyLongId.map(l->l+1).orElseGet(()->1L);
    }



    private ObservableList<String> initializeChosenStoreChoiceBox() {
        return FXCollections.observableArrayList(FileUtils.storesInput().stream()
                .map(NamedEntity::getName)
                .toList());
    }
    private void initializeItemChoiceBox(Store store){
        chosenItemChoiceBox.setItems(FXCollections.observableArrayList(store.getItems().stream()
                .map(NamedEntity::getName)
                .toList()));
    }
}
