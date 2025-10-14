// ==============================================================================
// MedicalWorker.java
// Abstract base class representing a medical worker in the hospital.
// Contains common attributes and defines the abstract salary calculation method.
// ==============================================================================

public abstract class MedicalWorker
{
    private String id;
    private String fullName;
    private String department;
    private int yearsExperience;
    private double baseSalary;

    // ================= Cosntructor =================
    public MedicalWorker(String id, String fullName, double baseSalary, String department, int yearsExperience)
    {
        this.id = id;
        this.fullName = fullName;
        this.baseSalary = baseSalary;
        this.department = department;
        this.yearsExperience = yearsExperience;
    }

    // ================= Getters and Setters =================

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getFullName()
    {
        return fullName;
    }

    public void setFullName(String fullName)
    {
        this.fullName = fullName;
    }

    public double getBaseSalary()
    {
        return baseSalary;
    }

    public void setBaseSalary(double baseSalary)
    {
        this.baseSalary = baseSalary;
    }

    public String getDepartment()
    {
        return department;
    }

    public void setDeparment(String department)
    {
        this.department = department;
    }

    public int getYearsExperience()
    {
        return yearsExperience;
    }

    public void setYearsExperience(int yearsExperience)
    {
        this.yearsExperience = yearsExperience;
    }


    // Abstract method to calculate the salary. 
    public abstract double calculateSalary();

    // Checks whether this worker has the same id as the provided id String.
    public boolean sameId(String otherId)
    {
        return otherId != null && this.id != null && this.id.equals(otherId);
    }

    // Checks whether this worker has the same id as another MedicalWorker.
    public boolean sameId(MedicalWorker other)
    {
        return other != null && sameId(other.getId());
    }

    @Override
    public String toString()
    {
        return "ID: " + id + " | Name: " + fullName + " | Dept: " + department + " | Exp: " + yearsExperience + " | Total Salary: $" + baseSalary;
    }
}