package hr.java.shop.bencic7.production.model;

/**
 * The Category class represents a named entity with a description.
 * It is used to categorize items or objects.
 */
public class Category extends NamedEntity {
    private String description;

    /**
     * Creates a new Category object with the provided description and name.
     *
     * @param description The description of the category.
     * @param name        The name of the category.
     */
    public Category(String description,Long id, String name) {
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