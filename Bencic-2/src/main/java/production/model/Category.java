package production.model;

import java.time.LocalDateTime;

public class Category extends NamedEntity {
    private String description;

    public Category(String description, String name,int identifier, LocalDateTime createdLocalDateTime) {
        super(name,identifier,createdLocalDateTime);
        this.description = description;

    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}