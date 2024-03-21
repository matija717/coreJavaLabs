package production.model;

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
    public Category(String description, String name) {
        super(name);
        this.description = description;

    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}