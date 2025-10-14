// =============================================
// Enfermero.java
// Represents a nurse with shift and certification
// =============================================

public class Enfermero extends MedicalWorker
{
    private String shiftType; // "day" or "night"
    private double nightBonus;
    private String certificationLevel;

    // ================= Cosntructor =================
    public Enfermero(String id, String fullName, double baseSalary, String department, int yearsExperience, String shiftType, double nightBonus, String certificationLevel)
    {
        super(id, fullName, baseSalary, department, yearsExperience);
        this.shiftType = shiftType;
        this.nightBonus = nightBonus;
        this.certificationLevel = certificationLevel;
    }

    // ================= Getters and Setters =================

    public String getShiftType()
    {
        return shiftType;
    }

    public void setShiftType(String shiftType)
    {
        this.shiftType = shiftType;
    }

    public double getNightBonus()
    {
        return nightBonus;
    }

    public void setNightBonus(double nightBonus)
    {
        this.nightBonus = nightBonus;
    }

    public String getCertificationLevel()
    {
        return certificationLevel;
    }

    public void setCertificationLevel(String certificationLevel)
    {
        this.certificationLevel = certificationLevel;
    }

    // ================= Methods =================

    @Override
    public double calculateSalary()
    {
        if (shiftType != null && shiftType.equalsIgnoreCase("night"))
        {
            return getBaseSalary() + nightBonus;
        }

        return getBaseSalary();
    }

    @Override
    public String toString()
    {
        return "Enfermero: " + super.toString() + " | Shift Type: " + shiftType + " | Night Bonus: " + nightBonus +  " | Certification: " + certificationLevel + " | Total Salary $: " + calculateSalary();
    }
}