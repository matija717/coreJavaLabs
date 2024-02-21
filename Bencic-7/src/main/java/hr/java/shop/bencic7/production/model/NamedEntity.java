package hr.java.shop.bencic7.production.model;

import java.io.Serializable;

/**
 * The NamedEntity class is an abstract class representing an entity with a name.
 */
public abstract class NamedEntity implements Serializable {
    private String name;
    private Long id;

    /**
     * Creates a new NamedEntity object with the provided name.
     *
     * @param name The name of the entity.
     */
    public NamedEntity(String name, Long id) {
        this.name = name;
        this.id=id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
