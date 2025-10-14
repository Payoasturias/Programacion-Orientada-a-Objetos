// ========================================================================
// Radiologo.java
// Represents a radiologist certified to operate specific medical equipment
// and calculate salary based on study fees and per-procedure commissions.
// ========================================================================

public class Radiologo extends MedicalWorker
{
    private String equipmentCertified;
    private double studyFee;
    private double commissionPerProcedure;

    // ================= Constructor =================
    public Radiologo(String id, String fullName, double baseSalary, String department, int yearsExperience, String equipmentCertified, double studyFee, double commissionPerProcedure)
    {
        super(id, fullName, baseSalary, department, yearsExperience);
        this.equipmentCertified = equipmentCertified;
        this.studyFee = studyFee;
        this.commissionPerProcedure = commissionPerProcedure;
    }

    // ================= Getters and Setters =================

    public String getEquipmentCertified()
    {
        return equipmentCertified;
    }

    public void setEquipmentCertified(String equipmentCertified)
    {
        this.equipmentCertified = equipmentCertified;
    }

    public double getStudyFee()
    {
        return studyFee;
    }

    public void setStudyFee(double studyFee)
    {
        this.studyFee = studyFee;
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
        return getBaseSalary() + studyFee + commissionPerProcedure;
    }

    @Override
    public String toString()
    {
        return "Radiologo: " + super.toString() + " | Equipment Certified: " + equipmentCertified + " | Study Fee: " + studyFee + " | Commission/Procedure: " + commissionPerProcedure + " | Total Salary $: " + calculateSalary();
    }
}   