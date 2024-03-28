package production.model;

import java.io.Serializable;

public abstract class NamedEntity implements Serializable {
    private String name;
    private Long id;
    protected NamedEntity(){
        this.name="";
        this.id=0L;
    }
    protected NamedEntity(String name, Long id) {
        this.id=id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
