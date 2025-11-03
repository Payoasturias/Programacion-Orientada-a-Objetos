// =============================================
// INTERFACE: Registrable.java
// Represents a device that can record or store measured values
// =============================================

public interface Registrable
{
    void register(double value);
    double getLastValue();
}