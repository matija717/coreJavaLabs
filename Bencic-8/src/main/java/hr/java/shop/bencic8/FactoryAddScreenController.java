package hr.java.shop.bencic8;

import hr.java.shop.bencic8.production.model.*;
import hr.java.shop.bencic8.utils.FileUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static hr.java.shop.bencic8.utils.FxDialogUtil.showAddAlert;
import static hr.java.shop.bencic8.utils.FxDialogUtil.showErrorAlert;
import static hr.java.shop.bencic8.utils.InputCheckerUtil.restrictToAlphabeticInput;
import static hr.java.shop.bencic8.utils.InputCheckerUtil.restrictToIntegerInput;

public class FactoryAddScreenController {
    private ObservableList<String> itemList;
    private Set<Item> factoryItems=new LinkedHashSet<>();
    @FXML
    private TextField nameTextField;
    @FXML
    private TextField addressStreetTextField;
    @FXML
    private TextField houseNumberTextField;
    @FXML
    private ChoiceBox<String> cityChoiceBox;
    @FXML
    private ChoiceBox<String> itemsChoiceBox;

    public void initialize(){
        restrictToAlphabeticInput(nameTextField);
        restrictToAlphabeticInput(addressStreetTextField);
        restrictToIntegerInput(houseNumberTextField);
        initializeChoiceBox();
    }

    private void initializeChoiceBox() {
        itemList=FXCollections
                .observableArrayList(FileUtils.allItems()
                .stream()
                .map(NamedEntity::getName)
                .toList());
        itemsChoiceBox.setItems(itemList);
        List<Address> addressList=FileUtils.addressInput();
        ObservableList<String> observableList=FXCollections.observableArrayList(addressList
                .stream()
                .map(a->a.getCity().getCityName())
                .toList());
        cityChoiceBox.setItems(observableList);
    }
    public void saveFactory(){
        List<Factory> factories=FileUtils.allFactories();
        Set<Item> currentFactoryItems=new LinkedHashSet<>(factoryItems);
        try{
        String name=nameTextField.getText();
        String street=addressStreetTextField.getText();
        String houseNumber=houseNumberTextField.getText();
        if(name.isEmpty()||street.isEmpty()||houseNumber.isEmpty()||cityChoiceBox.getValue()==null)
            throw new RuntimeException();
        factories.add(new Factory(name,idCalc(factories),addressOfFactory(street,houseNumber),currentFactoryItems));
        FileUtils.saveFactories(factories);
        factoryItems.clear();
        initializeChoiceBox();}
        catch (RuntimeException e){
        showErrorAlert(FactoryAddScreenController.class.getSimpleName());
        }
    }
    private Address addressOfFactory(String street,String houseNumber){
        List<Address> addressList=FileUtils.addressInput();
        Long addresId=addressList.stream().map(NamedEntity::getId).max(Long::compareTo).get()+1L;
        return new Address(addresId
                ,street
                ,houseNumber
                ,FileUtils.findCity(cityChoiceBox.getSelectionModel().getSelectedItem()));
    }
    public void saveItem() {
        savingItems(itemsChoiceBox, factoryItems, itemList);
    }
    private Long idCalc(List<Factory> factories) {
        Optional<Long> storeIdOptional = factories.stream()
                .map(NamedEntity::getId)
                .max(Long::compareTo);

        return storeIdOptional.map(aLong -> aLong + 1).orElseGet(() -> 1L);
    }

    protected static void savingItems(ChoiceBox<String> itemsChoiceBox, Set<Item> factoryItems, ObservableList<String> itemList) {
        List<Item> allItems = FileUtils.allItems();
        try{
        String selectedItemName = itemsChoiceBox.getSelectionModel().getSelectedItem();
        if(itemsChoiceBox.getValue()==null)throw new RuntimeException();
        Optional<Item> selectedItem = allItems.stream()
                .filter(item -> item.getName().equals(selectedItemName))
                .findFirst();

        selectedItem.ifPresent(item -> {
            factoryItems.add(item);
            itemList.remove(item.getName());
            allItems.remove(item);
            showAddAlert(item.getName());
        });}
        catch (RuntimeException e){
            showErrorAlert("Item not chosen error in Factory or Store");
        }

    }
}
