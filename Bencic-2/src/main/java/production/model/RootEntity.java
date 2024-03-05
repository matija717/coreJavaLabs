package production.model;

import java.time.LocalDateTime;

public abstract class RootEntity {
    int identifier;
    LocalDateTime createdDateTime;

    public RootEntity(int identifier, LocalDateTime createdDateTime) {
        this.identifier = identifier;
        this.createdDateTime = createdDateTime;
    }

    public int getIdentifier() {
        return identifier;
    }

    public void setIdentifier(int identifier) {
        this.identifier = identifier;
    }

    public LocalDateTime getCreatedDateTime() {
        return createdDateTime;
    }

    public void setCreatedDateTime(LocalDateTime createdDateTime) {
        this.createdDateTime = createdDateTime;
    }
}
