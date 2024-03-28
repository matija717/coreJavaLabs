package production.model;


public class Category extends NamedEntity {
    private String description;


    public Category(String description, Long id, String name) {
        super(name,id);
        this.description = description;

    }

    public String getDescription() {
        return description;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    public void setDescription(String description) {
        this.description = description;
    }

}