// =============================================
// Terapeuta.java
// Represents a therapist in the hospital.
// Salary is calculated based on base salary and session duration.
// =============================================

public class Terapeuta extends MedicalWorker
{
    private String therapyType;      // type of therapy (e.g. "Physical", "Occupational", "Speech")
    private int sessionDuration;     // duration of session in minutes

    // ==================== Constructor ====================
    public Terapeuta(String id, String fullName, double baseSalary, String department, int yearsExperience, String therapyType, int sessionDuration)
    {
        super(id, fullName, baseSalary, department, yearsExperience);
        this.therapyType = therapyType;
        this.sessionDuration = sessionDuration;
    }

    // ==================== Getters & Setters ====================
    public String getTherapyType()
    {
        return therapyType;
    }

    public void setTherapyType(String therapyType)
    {
        this.therapyType = therapyType;
    }

    public int getSessionDuration()
    {
        return sessionDuration;
    }

    public void setSessionDuration(int sessionDuration)
    {
        this.sessionDuration = sessionDuration;
    }

    // ==================== Methods ====================
    // Salary = base salary + (sessionDuration * 5)
    // (we assume each minute of session adds a fixed rate of 5)
    @Override
    public double calculateSalary()
    {
        return getBaseSalary() + (sessionDuration * 5);
    }

    @Override
    public String toString()
    {
        return "Terapeuta: " + super.toString() + " | Therapy Type: " + therapyType + " | Session Duration: " + sessionDuration + " min" + " | Total Salary: $" + calculateSalary();
    }
}
