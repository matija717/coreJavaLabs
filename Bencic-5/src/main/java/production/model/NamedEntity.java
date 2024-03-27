package production.model;

/**
 * The NamedEntity class is an abstract class representing an entity with a name.
 */
public abstract class NamedEntity {
    private String name;

    /**
     * Creates a new NamedEntity object with the provided name.
     *
     * @param name The name of the entity.
     */
    protected NamedEntity(String name) {
        this.name = name;
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
