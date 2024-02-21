package hr.java.shop.bencic7.production.model;
/**
 * The Technical interface represents objects related to technical devices and permits the 'Laptop' class.
 */
public sealed interface Technical permits Laptop {
    /**
     * Calculates the duration of the warranty or guarantee for a technical device in months.
     *
     * @param months The number of months for which the warranty or guarantee is valid.
     * @return The duration of the warranty or guarantee in months.
     */
    Integer durationOfGarante(Integer months);
}
