// ====================================================================================================================
// Reagendamiento.java
// Represents the rescheduling record of a medical appointment.
// Stores the original appointment ID, old and new dates, the reason for the change, and the name of the user or system
// responsible for the modification.
// =====================================================================================================================

public class Reagendamiento
{
    private String appointmentId;
    private String oldDate;
    private String nnewDate;
    private String reason;
    private String modifiedBy;

    // ================= Constructor =================
    public Reagendamiento(String appointmentId, String oldDate, String nnewDate, String reason, String modifiedBy)
    {
        this.appointmentId = appointmentId;
        this.oldDate = oldDate;
        this.nnewDate = nnewDate;
        this.reason = reason;
        this.modifiedBy = modifiedBy;
    }

    // ================= Getters and Setters =================

    public String getAppointmentId()
    {
        return appointmentId;
    }

    public String getOldDate()
    {
        return oldDate;
    }

    public void setOldDate(String oldDate)
    {
        this.oldDate = oldDate;
    }

    public String getNewDate() 
    {
        return nnewDate;
    }

    public void setNewDate(String nnewDate)
    {
        this.nnewDate = nnewDate;
    }

    public String getReason()
    {
        return reason;
    }

    public void setReason(String reason)
    {
        this.reason = reason;
    }

    public String getModifiedBy()
    {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy)
    {
        this.modifiedBy = modifiedBy;
    }

    // ================= Methods =================

    // Checks if the provided appointment ID matches this record.
    public boolean sameId(String otherId)
    {
        return this.appointmentId != null && this.appointmentId.equalsIgnoreCase(otherId);
    }

    // Returns a formatted text representation of the rescheduling record.
    @Override
    public String toString()
    {
        return "Reagendamiento - Appointment ID: " + appointmentId + " | Old Date: " + oldDate + " | New Date: " + nnewDate + " | Reason: " + reason + " | Modified By: " + modifiedBy;
    }
}