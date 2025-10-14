// =============================================
// HospitalManager.java
// Central manager that handles workers, appointments and rescheduling history.
// Uses ArrayList and String-based date/time as requested in the UML.
// =============================================

import java.util.ArrayList;

public class HospitalManager
{
    private ArrayList<MedicalWorker> workers;
    private ArrayList<Appointment> appointments;
    private ArrayList<Reagendamiento> history;

    // ================= Constructor =================
    public HospitalManager()
    {
        this.workers = new ArrayList<>();
        this.appointments = new ArrayList<>();
        this.history = new ArrayList<>();
    }

    // ========================= Worker managment =========================

    // Add worker to the system
    public void addWorker(MedicalWorker worker)
    {
        if(worker == null)
        {
            return;
        }

        workers.add(worker);
    }

    public void addAppointment(Appointment appointment)
    {
        if(appointment == null)
        {
            return;
        }

        appointments.add(appointment);
    }

    // ================= Check for conflicts =================
    // Availability & assignment
    // Checks whether the given worker (object) is busy at the given date/time string.
    // Returns true if the worker has a non-cancelled appointment at that exact date/time.
    public boolean isWorkerBusyAt(MedicalWorker worker, String dateTimeStr)
    {
        if(worker == null || dateTimeStr == null)
        {
            return false;
        }

        for(Appointment a : appointments)
        {
            if(a != null)
            {
                MedicalWorker assigned = a.getAssignedWorker();
                
                if(assigned != null)
                {
                    boolean sameWorker = assigned.getId().equalsIgnoreCase(worker.getId());
                    boolean sameDate = dateTimeStr.equalsIgnoreCase(a.getDateTime());

                    if(sameWorker && sameDate && !"CANCELADA".equalsIgnoreCase(a.getStatus()) && !"COMPLETADA".equalsIgnoreCase(a.getStatus()))
                    {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    // ================= Smart Assignment =================
    // Find an available worker in the given department at the given date.
    // excludeId can be used to exclude a specific worker (e.g. the current assigned one).
    public MedicalWorker assignAvailableWorker(String department, String date, String excludeId)
    {
        for(MedicalWorker w : workers)
        {
            if(w == null)
            {
                // Ignore null entries
            }

            else
            {
                boolean correctDept = department != null && department.equalsIgnoreCase(w.getDepartment());
                boolean notExcluded = excludeId == null || !excludeId.equalsIgnoreCase(w.getId());
                boolean available = !isWorkerBusyAt(w, date);

                if(correctDept && notExcluded && available)
                {
                    return w;
                }
            }
        }

        return null; // No available worker found
    }

    // ========================= Reassign Appointment =========================

    // Logic:
    // - Find appointment by id.
    // - If it has an assigned worker and that worker is busy at newDate, attempt to find
    // another available worker in the same department. If not found, return false.
    // - Update the appointment date/time and status to "RESCHEDULED".
    // - Add a Reagendamiento record to history (oldDate and newDate are Strings).
    
    public void reassignAppointment(String id, String nnewDate, String reason, String modifiedBy)
    {
        if(id == null || nnewDate == null)
        {
            return;
        }

        for(Appointment a : appointments)
        {
            if(a != null && a.sameId(id))
            {
                String currentStatus = a.getStatus();

                // Skip if appointment already closed or concelled
                if("CANCELADA".equalsIgnoreCase(currentStatus) || "COMPLETADA".equalsIgnoreCase(currentStatus))
                {
                    return;
                }

                String oldDate = a.getDateTime();
                MedicalWorker assigned = a.getAssignedWorker();

                if(assigned != null)
                {
                    if(isWorkerBusyAt(assigned, nnewDate))
                    {
                        String dept = assigned.getDepartment();
                        MedicalWorker alt = assignAvailableWorker(dept, nnewDate, assigned.getId());

                        if(alt != null)
                        {
                            a.setAssignedWorker(alt);
                        }

                        else
                        {
                            // No replacement available
                            return;
                        }
                    }
                }

                // Update appointment
                a.setDateTime(nnewDate);
                a.setStatus("REAGENDADA");

                // Record the rescheduling in the history.
                Reagendamiento r = new Reagendamiento(id, oldDate, nnewDate, reason, modifiedBy);
                history.add(r);
                return;
            }
        }
    }

    // ================= Appointment state transitions =================

    // Confirm an appointment (PROGRAMADA -> CONFIRMADA).
    // Returns true if the status was updated, false if not found or not applicable.
    public boolean confirmAppointment(String appointmentId)
    {
        if (appointmentId == null)
        {
            return false;
        }

        for (Appointment a : appointments)
        {
            if (a != null && a.sameId(appointmentId))
            {
                String s = a.getStatus();
                if ("PROGRAMADA".equalsIgnoreCase(s))
                {
                    a.setStatus("CONFIRMADA");
                    return true;
                }

                // If already confirmed or in progress or other, do not change here
                return false;
            }
        }

        return false;
    }

    // Start an appointment (CONFIRMADA -> EN_PROGRESO).
    // Returns true if transitioned, false otherwise.
    public boolean startAppointment(String appointmentId)
    {
        if (appointmentId == null)
        {
            return false;
        }

        for (Appointment a : appointments)
        {
            if (a != null && a.sameId(appointmentId))
            {
                String s = a.getStatus();
                if ("CONFIRMADA".equalsIgnoreCase(s))
                {
                    a.setStatus("EN_PROGRESO");
                    return true;
                }

                return false;
            }
        }

        return false;
    }

    // Complete an appointment (EN_PROGRESO -> COMPLETADA).
    // Returns true if transitioned, false otherwise.
    public boolean completeAppointment(String appointmentId)
    {
        if (appointmentId == null)
        {
            return false;
        }

        for (Appointment a : appointments)
        {
            if (a != null && a.sameId(appointmentId))
            {
                String s = a.getStatus();
                if ("EN_PROGRESO".equalsIgnoreCase(s))
                {
                    a.setStatus("COMPLETADA");
                    return true;
                }

                return false;
            }
        }

        return false;
    }

    // Cancel an appointment (unless already COMPLETADA).
    // Returns true if cancelled, false otherwise.
    public boolean cancelAppointment(String appointmentId)
    {
        if (appointmentId == null)
        {
            return false;
        }

        for (Appointment a : appointments)
        {
            if (a != null && a.sameId(appointmentId))
            {
                String s = a.getStatus();
                if ("COMPLETADA".equalsIgnoreCase(s))
                {
                    return false; // cannot cancel a completed appointment
                }

                // set CANCELADA regardless of previous active state
                a.setStatus("CANCELADA");
                return true;
            }
        }

        return false;
    }

    // ================= Reports by worker =================

    // Returns all appointments assigned to a given worker (any status).
    public ArrayList<Appointment> getAppointmentsByWorker(String workerId)
    {
        ArrayList<Appointment> result = new ArrayList<>();
        if (workerId == null)
        {
            return result;
        }

        for (Appointment a : appointments)
        {
            if (a != null && a.getAssignedWorker() != null && workerId.equalsIgnoreCase(a.getAssignedWorker().getId()))
            {
                result.add(a);
            }
        }

        return result;
    }

    // Returns a formatted list of all appointments assigned to a given worker.
    // The View class will handle the actual printing or display.
    public ArrayList<Appointment> reportAppointmentsByWorker(String workerId)
    {
        return getAppointmentsByWorker(workerId);
    }

    // Calculate total payroll summing calculateSalary() of each worker.
    public double calculatePayroll()
    {
        double total = 0.0;

        for(MedicalWorker w : workers)
        {
            if(w != null)
            {
                total += w.calculateSalary();
            }
        }

        return total;
    }

    // Calculate payroll for a specific department.
    public double calculateTotalPayrollByDepartment(String department)
    {
        double total = 0.0;
        if (department == null)
        {
            return total;
        }

        for (MedicalWorker w : workers)
        {
            if (w != null && department.equalsIgnoreCase(w.getDepartment()))
            {
                total += w.calculateSalary();
            }
        }

        return total;
    }

    // ================= Getters =================

    // Calculate utilization of a worker as percentage of completed appointments
    // among the appointments assigned to that worker.
    public double getWorkerUtilization(String workerId)
    {
        if (workerId == null)
        {
            return 0.0;
        }

        int totalAppointments = 0;
        int attendedAppointments = 0;

        for (Appointment a : appointments)
        {
            if (a != null)
            {
                MedicalWorker assigned = a.getAssignedWorker();
                if (assigned != null && workerId.equalsIgnoreCase(assigned.getId()))
                {
                    totalAppointments++;
                    if ("COMPLETADA".equalsIgnoreCase(a.getStatus()))
                    {
                        attendedAppointments++;
                    }
                }
            }
        }

        if (totalAppointments == 0)
        {
            return 0.0;
        }

        return (double) attendedAppointments / totalAppointments * 100.0;
    }

    public ArrayList<Appointment> getAppointmentsByStatus(String status)
    {
        ArrayList<Appointment> filtered = new ArrayList<>();

        for (Appointment a : appointments)
        {
            if (a != null && status != null && status.equalsIgnoreCase(a.getStatus()))
            {
                filtered.add(a);
            }
        }

        return filtered;
    }

    // Returns a list of all workers.
    public ArrayList<MedicalWorker> getAllWorkers()
    {
        return new ArrayList<>(workers);
    }

    // ========================= History =========================
    // Returns the complete list of rescheduling history.
    public ArrayList<Reagendamiento> getReagendamientoHistory()
    {
        return new ArrayList<>(history);
    }
}