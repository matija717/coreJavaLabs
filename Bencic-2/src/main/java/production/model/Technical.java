package production.model;

public sealed interface Technical permits Laptop {
    Integer durationOfGuarantee(Integer months);
}
