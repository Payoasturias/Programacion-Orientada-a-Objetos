// =============================================
// Cirujano.java
// Represents a surgeon with risk bonus and surgery hours
// =============================================
public class Cirujano extends MedicalWorker
{
    private String surgeryTypes;
    private double surgeryHours;
    private double hourlyRate;
    private double riskBonus;

    // ================= Cosntructor =================
    public Cirujano(String id, String fullName, double baseSalary, String department, int yearsExperience, String surgeryTypes, double surgeryHours, double hourlyRate, double riskBonus)
    {
        super(id, fullName, baseSalary, department, yearsExperience);
        this.surgeryTypes = surgeryTypes;
        this.surgeryHours = surgeryHours;
        this.hourlyRate = hourlyRate;
        this.riskBonus = riskBonus;
    }

    // ================= Getters and Setters =================

    public String getSurgeryTypes()
    {
        return surgeryTypes;
    }

    public void setSurgeryTypes(String surgeryTypes)
    {
        this.surgeryTypes = surgeryTypes;
    }

    public double getSurgeryHours()
    {
        return surgeryHours;
    }

    public void setSurgeryHours(double surgeryHours)
    {
        this.surgeryHours = surgeryHours;
    }

    public double getHourlyRate()
    {
        return hourlyRate;
    }

    public void setHourlyRate(double hourlyRate)
    {
        this.hourlyRate = hourlyRate;
    }

    public double getRiskBonus()
    {
        return riskBonus;
    }

    public void setRiskBonus(double riskBonus)
    {
        this.riskBonus = riskBonus;
    }

    // ================= Methods =================

    @Override
    public double calculateSalary()
    {
        return super.getBaseSalary() + (surgeryHours * hourlyRate) + riskBonus;
    }

    @Override
    public String toString()
    {
        return "Cirujano: " + super.toString() + " | Surgery Types: " + surgeryTypes + " | Surgery Hours: " + " | Hourly Rate: " + hourlyRate + " | Risk Bonus: " + riskBonus + " | Total Salary $: " + calculateSalary();
    }
}