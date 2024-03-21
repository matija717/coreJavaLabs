package production.model;

import java.time.LocalDateTime;

public abstract class NamedEntity extends RootEntity  {
    private String name;


    public NamedEntity(String name, int identifier, LocalDateTime createdLocalDateTime) {
        super(identifier,createdLocalDateTime);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
