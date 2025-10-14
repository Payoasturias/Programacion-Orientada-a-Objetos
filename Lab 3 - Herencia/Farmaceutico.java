// =============================================
// Farmaceutico.java
// Represents other specialists with procedure commissions
// =============================================

public class Farmaceutico extends MedicalWorker
{
    private int prescriptionLimit;
    private String controlledLicense;
    private double commissionPerProcedure;

    // ================= Cosntructor =================
    public Farmaceutico(String id, String fullName, double baseSalary, String department, int yearsExperience, int prescriptionLimit, String controlledLicense, double commissionPerProcedure)
    {
        super(id, fullName, baseSalary, department, yearsExperience);
        this.prescriptionLimit = prescriptionLimit;
        this.controlledLicense = controlledLicense;
        this.commissionPerProcedure = commissionPerProcedure;
    }

    // ================= Getters and Setters =================
    public int getPrescriptionLimit()
    {
        return prescriptionLimit;
    }

    public void setPrescriptionLimit(int prescriptionLimit)
    {
        this.prescriptionLimit = prescriptionLimit;
    }

    public String getControlledLicense()
    {
        return controlledLicense;
    }

    public void setControlledLicense(String controlledLicense)
    {
        this.controlledLicense = controlledLicense;
    }

    public double getCommissionPerProcedure()
    {
        return commissionPerProcedure;
        
    }

    public void setCommissionPerProcedure(double commissionPerProcedure)
    {
        this.commissionPerProcedure = commissionPerProcedure;
    }

    // ================= Methods =================

    @Override
    public double calculateSalary()
    {
        return getBaseSalary() + (commissionPerProcedure * prescriptionLimit);
    }

    @Override
    public String toString()
    {
        return "Farmaceutico: " + super.toString() + " | Prescription Limit: " + prescriptionLimit +" | Controlled License: " + controlledLicense + " | Commission/Procedure: " + commissionPerProcedure + " | Total Salary $: " + calculateSalary();
    }
}