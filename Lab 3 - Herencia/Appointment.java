// ======================================================================================
// Appointment.java
// Represents a medical appointment with basic information:
// ID, patient name, assigned worker, date/time, type of appointment, and current status.
// ======================================================================================

public class Appointment
{
    private String appointmentId;
    private String patientName;
    private MedicalWorker assignedWorker;
    private String dateTime;
    private String appointmentType;
    private String status;

    // ================= Constructor =================
    public Appointment(String appointmentId, String patientName, MedicalWorker assignedWorker, String dateTime, String appointmentType, String status)
    {
        this.appointmentId = appointmentId;
        this.patientName = patientName;
        this.assignedWorker = assignedWorker;
        this.dateTime = dateTime;
        this.appointmentType = appointmentType;
        this.status = status;
    }

    // ================= Getters and Setters =================

    public String getAppointmentId()
    {
        return appointmentId;
    }

    public String getPatientName()
    {
        return patientName;
    }

    public MedicalWorker getAssignedWorker()
    {
        return assignedWorker;
    }

    public void setAssignedWorker(MedicalWorker assignedWorker)
    {
        this.assignedWorker = assignedWorker;
    }

    public String getDateTime()
    {
        return dateTime;
    }

    public void setDateTime(String dateTime)
    {
        this.dateTime = dateTime;
    }

    public String getAppointmentType()
    {
        return appointmentType;
    }

    public void setAppointmentType(String appointmentType)
    {
        this.appointmentType = appointmentType;
    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    // ================= Methods =================

    // Checks if the given ID matches this appointment's ID.
    public boolean sameId(String otherId)
    {
        return this.appointmentId != null && this.appointmentId.equalsIgnoreCase(otherId);
    }

    // Returns a formatted description of the appointment.
    @Override
    public String toString()
    {
        String workerName = (assignedWorker != null) ? assignedWorker.getFullName() : "Unassigned";
        return "Appointment ID: " + appointmentId + " | Patient: " + patientName + " | Worker: " + workerName + " | Date: " + dateTime + " | Type: " + appointmentType + " | Status: " + status;
    }
}