package production.model;

public record Ingredient(String name) {
    public Ingredient(String name) {
        this.name = name;
    }


    public String name() {
        return name;
    }
}
