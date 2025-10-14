// =============================================
// DoctorGeneral.java
// Represents a general doctor in the hospital
// =============================================

public class DoctorGeneral extends MedicalWorker
{
    private String specialization;
    private int patientsPerDay;
    private double consultFee;

    // ================= Cosntructor =================
    public DoctorGeneral(String id, String fullName, double baseSalary, String department, int yearsExperience, String specialization, int patientsPerDay, double consultFee)
    {
        super(id, fullName, baseSalary, department, yearsExperience);
        this.specialization = specialization;
        this.patientsPerDay = patientsPerDay;
        this.consultFee = consultFee;
    }

    // ================= Getters and Setters =================

    public String getSpecialization()
    {
        return specialization;
    }

    public void setSpecialization(String specialization)
    {
        this.specialization = specialization;
    }

    public int getPatientsPerDay()
    {
        return patientsPerDay;
    }

    public void setPatientsPerDay(int patientsPerDay)
    {
        this.patientsPerDay = patientsPerDay;
    }

    public double getConsultFee()
    {
        return consultFee;
    }

    public void setConsultFee(double consultFee)
    {
        this.consultFee = consultFee;
    }

    // ================= Methods =================

    @Override
    public double calculateSalary()
    {
        return getBaseSalary() + (patientsPerDay * consultFee);
    }

    @Override
    public String toString()
    {
        return "Doctor General: " + super.toString() + " | Specialization: " + specialization + " | Patients/day: " + patientsPerDay + " | ConsultFee: " + consultFee + " | Total Salary $: " + calculateSalary();
    }
}