// =================================================================
// HospitalView.java
// Console view that exposes only the requested methods.
// All console I/O is handled here; Manager performs logic and data.
// NOTE: this class does NOT use String.trim() anywhere.
// =================================================================

import java.util.ArrayList;
import java.util.Scanner;

public class HospitalView 
{
    private HospitalManager manager;
    private Scanner scanner;

    // ========================= Constructor =========================
    public HospitalView(HospitalManager manager)
    {
        this.manager = manager;
        this.scanner = new Scanner(System.in);
    }

    // Reads a line from console and returns it.
    public String readString(String message)
    {
        System.out.print(message + ": ");
        try
        {
            return scanner.nextLine();
        }
        
        catch (Exception e)
        {
            return "";
        }
    }

    // Reads an integer once. Returns 0 if invalid input
    public int readInt(String message)
    {
        System.out.print(message + ": ");
        try 
        {
            String line = scanner.nextLine();
            return Integer.parseInt(line);
        }
        
        catch (Exception e)
        {
            return 0;
        }
    }

    // Reads a double once. Returns 0.0 if invalid input
    public double readDouble(String message)
    {
        System.out.print(message + ": ");
        try
        {
            String line = scanner.nextLine();
            return Double.parseDouble(line);
        }
        
        catch (Exception e)
        {
            return 0.0;
        }
    }

    // Generic message output.
    public void showMessage(String message)
    {
        System.out.println(message);
    }

    // Show a single worker.
    public void showWorker(MedicalWorker worker)
    {
        if (worker == null)
        {
            System.out.println("[No worker to show]");
            return;
        }
        System.out.println(worker.toString());
    }

    // Show a single appointment.
    public void showAppointment(Appointment appointment)
    {
        if (appointment == null)
        {
            System.out.println("[No appointment to show]");
            return;
        }
        System.out.println(appointment.toString());
    }

    // ========================= Main menu flow =========================

    // Starts the interactive console menu.
    public void startMenu()
    {
        boolean running = true;
        while (running)
        {
            printMainMenu();
            String opt = readString("Select option");
            if (opt == null) opt = "";

            switch (opt)
            {
                case "1":
                    manageStaffMenu();
                    break;
                case "2":
                    manageAppointmentsMenu();
                    break;
                case "3":
                    manageOperationsMenu();
                    break;
                case "4":
                    reportsMenu();
                    break;
                case "5":
                    showMessage("Exiting...");
                    running = false;
                    break;
                default:
                    showMessage("Invalid option.");
            }
        }
    }

    // Prints the main menu.
    public void printMainMenu()
    {
        System.out.println("\n=== HOSPITAL SYSTEM ===");
        System.out.println("1 - Manage Staff");
        System.out.println("2 - Manage Appointments");
        System.out.println("3 - Operations / Rescheduling");
        System.out.println("4 - Reports");
        System.out.println("5 - Exit");
    }

    // ==================== Menus ====================

    // Staff management menu.
    public void manageStaffMenu()
    {
        boolean back = false;
        while (!back)
        {
            System.out.println("\n-- Manage Staff --");
            System.out.println("1 - Add Worker");
            System.out.println("2 - List Workers");
            System.out.println("3 - Back");
            String opt = readString("Option");

            if ("1".equals(opt))
            {
                addWorkerFlow();
            }

            else if ("2".equals(opt))
            {
                ArrayList<MedicalWorker> list = manager.getAllWorkers();
                if (list.isEmpty())
                {
                    showMessage("No workers registered.");
                }

                else 
                {
                    for (MedicalWorker w : list)
                    {
                        showWorker(w);
                    }
                }
            }

            else if ("3".equals(opt))
            {
                back = true;
            }

            else
            {
                showMessage("Invalid option.");
            }
        }
    }

    // Appointments management menu.
    public void manageAppointmentsMenu()
    {
        boolean back = false;
        while (!back)
        {
            System.out.println("\n-- Manage Appointments --");
            System.out.println("1 - Create Appointment");
            System.out.println("2 - Reschedule Appointment");
            System.out.println("3 - Change Appointment Status");
            System.out.println("4 - Cancel Appointment");
            System.out.println("5 - List Appointments by Status");
            System.out.println("6 - List Appointments by Worker");
            System.out.println("7 - Back");
            String opt = readString("Option");
            if ("1".equals(opt))
            {
                createAppointmentFlow();
            }

            else if ("2".equals(opt))
            {
                rescheduleAppointmentFlow();
            }

            else if ("3".equals(opt))
            {
                changeAppointmentStatusFlow();
            }

            else if ("4".equals(opt))
            {
                cancelAppointmentFlow();
            }

            else if ("5".equals(opt))
            {
                String status = readString("Status (PROGRAMADA, CONFIRMADA, EN_PROGRESO, COMPLETADA, REAGENDADA, CANCELADA)");
                ArrayList<Appointment> list = manager.getAppointmentsByStatus(status);
                if (list.isEmpty())
                {
                    showMessage("No appointments with status: " + status);
                }

                else
                {
                    for (Appointment a : list) 
                    {
                        showAppointment(a);
                    }
                }
            }

            else if ("6".equals(opt))
            {
                String workerId = readString("Worker ID");
                ArrayList<Appointment> list = manager.getAppointmentsByWorker(workerId);
                if (list.isEmpty())
                {
                    showMessage("No appointments for worker: " + workerId);
                }

                else
                {
                    for (Appointment a : list)
                    {
                        showAppointment(a);
                    }
                }
            }

            else if ("7".equals(opt))
            {
                back = true;
            }

            else
            {
                showMessage("Invalid option.");
            }
        }
    }


    // Operations / rescheduling menu.
    public void manageOperationsMenu()
    {
        boolean back = false;
        while (!back)
        {
            System.out.println("\n-- Operations / Rescheduling --");
            System.out.println("1 - Execute smart reassignment (reassignAppointment)");
            System.out.println("2 - Show reschedule history");
            System.out.println("3 - Back");
            String opt = readString("Option");
            if ("1".equals(opt))
            {
                rescheduleAppointmentFlow();
            }

            else if ("2".equals(opt))
            {
                ArrayList<Reagendamiento> hist = manager.getReagendamientoHistory();
                if (hist.isEmpty())
                {
                    showMessage("No reschedule history.");
                }

                else
                {
                    for (Reagendamiento r : hist)
                    {
                        System.out.println(r.toString());
                    }
                }
            }

            else if ("3".equals(opt))
            {
                back = true;
            }

            else
            {
                showMessage("Invalid option.");
            }
        }
    }

    // ========================= Reports Menu =========================

    // Reports menu.
    public void reportsMenu()
    {
        boolean back = false;
        while (!back) {
            System.out.println("\n-- Reports --");
            System.out.println("1 - Total payroll");
            System.out.println("2 - Department payroll");
            System.out.println("3 - Worker utilization");
            System.out.println("4 - All workers");
            System.out.println("5 - Back");
            String opt = readString("Option");
            if ("1".equals(opt))
            {
                double total = manager.calculatePayroll();
                showMessage("Total payroll: " + total);
            }

            else if ("2".equals(opt))
            {
                String dept = readString("Department");
                double deptTotal = manager.calculateTotalPayrollByDepartment(dept);
                showMessage("Payroll for " + dept + ": " + deptTotal);
            }

            else if ("3".equals(opt))
            {
                String id = readString("Worker ID");
                double util = manager.getWorkerUtilization(id);
                showMessage(String.format("Utilization for worker %s: %.2f%%", id, util));
            }

            else if ("4".equals(opt))
            {
                ArrayList<MedicalWorker> list = manager.getAllWorkers();
                if (list.isEmpty())
                {
                    showMessage("No workers.");
                }

                else
                {
                    for (MedicalWorker w : list) showWorker(w);
                }
            }

            else if ("5".equals(opt))
            {
                back = true;
            }

            else
            {
                showMessage("Invalid option.");
            }
        }
    }

    // ========================= Flows requested =========================

    // Adds a new worker via interactive prompts.
    // This method uses manager.addWorker(...) to register the worker.
    public void addWorkerFlow()
    {
        String type = readString("Type 1. Doctor General, 2. Cirujano, 3. Emfermero, 4. Radiologo, 5. Farmaceutico, 6. Terapeuta");
        String id = readString("ID");
        String name = readString("Full name");
        String dept = readString("Department");
        int years = readInt("Years of experience (int)");
        double base = readDouble("Base salary (double)");

        try
        {
            switch (type)
            {
                case "1":
                {
                    String specialization = readString("Specialization");
                    int patients = readInt("Patients per day (int)");
                    double fee = readDouble("Consultation fee (double)");
                    manager.addWorker(new DoctorGeneral(id, name, base, dept, years, specialization, patients, fee));
                    showMessage("Doctor General added.");
                    break;
                }

                case "2":
                {
                    String surgeryType = readString("Surgery type");
                    int hours = readInt("Surgery hours (int)");
                    double risk = readDouble("Risk bonus (double)");
                    // Cirujano constructor: (id, fullName, baseSalary, department, yearsExperience, surgeryTypes, surgeryHours, hourlyRate, riskBonus)
                    // We map 'hours' -> surgeryHours and 'risk' -> riskBonus; use hours as surgeryHours and risk as riskBonus, and set a default hourlyRate (0.0)
                    manager.addWorker(new Cirujano(id, name, base, dept, years, surgeryType, hours, 0.0, risk));
                    showMessage("Cirujano added.");
                    break;
                }

                case "3":
                {
                    String shift = readString("Shift type (day/night)");
                    String cert = readString("Certification level");
                    // Enfermero constructor: (id, fullName, baseSalary, department, yearsExperience, shiftType, nightBonus, certificationLevel)
                    // We don't have nightBonus from UI; set to 0.0 by default.
                    manager.addWorker(new Enfermero(id, name, base, dept, years, shift, 0.0, cert));
                    showMessage("Enfermero added.");
                    break;
                }

                case "4":
                {
                    String equipment = readString("Equipment certified");
                    double studyFee = readDouble("Study fee (double)");
                    double commission = readDouble("Commission per procedure (double)");
                    manager.addWorker(new Radiologo(id, name, base, dept, years, equipment, studyFee, commission));
                    showMessage("Radiologo added.");
                    break;
                }

                case "5":
                {
                    int limit = readInt("Prescription limit (int)");
                    String lic = readString("Controlled license (Yes/No)");
                    double comm = readDouble("Commission per procedure (double)");
                    manager.addWorker(new Farmaceutico(id, name, base, dept, years, limit, lic, comm));
                    showMessage("Farmaceutico added.");
                    break;
                }

                case "6": // Terapeuta
                {
                    String therapy = readString("Therapy type");
                    int duration = readInt("Session duration (minutes)");
                    manager.addWorker(new Terapeuta(id, name, base, dept, years, therapy, duration));
                    showMessage("Terapeuta added.");
                    break;
                }


                default:
                    showMessage("Invalid type.");
            }
        }
        
        catch (Exception e)
        {
            showMessage("Error adding worker: " + e.getMessage());
        }
    }

    // Create appointment flow.
    public void createAppointmentFlow()
    {
        String apptId = readString("Appointment ID");
        String patient = readString("Patient name");
        String dateTime = readString("Date/time (String)");
        String type = readString("Appointment type (consultation/surgery/diagnostic)");
        String dept = readString("Preferred department (or blank)");

        MedicalWorker assigned = null;
        if (dept != null && dept.length() > 0)
        {
            assigned = manager.assignAvailableWorker(dept, dateTime, null);
            if (assigned != null)
            {
                showMessage("Auto-assigned: " + assigned.getFullName());
            }

            else
            {
                        showMessage("No auto-assigned worker available.");
                    }
                }

        Appointment a = new Appointment(apptId, patient, assigned, dateTime, type, "PROGRAMADA");
        manager.addAppointment(a);
        showMessage("Appointment created: " + a.toString());
    }

    // Reschedule appointment flow (invokes manager.reassignAppointment).
    public void rescheduleAppointmentFlow()
    {
        String apptId = readString("Appointment ID to reschedule");
        String newDate = readString("New date/time (String)");
        String reason = readString("Reason");
        String modifiedBy = readString("Modified by");
        manager.reassignAppointment(apptId, newDate, reason, modifiedBy);
        showMessage("Reschedule attempted.");
    }

    // Cancel appointment flow (invokes manager.cancelAppointment).
    public void cancelAppointmentFlow()
    {
        String apptId = readString("Appointment ID to cancel");
        boolean ok = manager.cancelAppointment(apptId);
        showMessage(ok ? "Appointment cancelled." : "Could not cancel appointment.");
    }

    // ========================= New: Change status flow =========================

    // Prompts user to change appointment status using the HospitalManager API.
    // Uses confirmAppointment, startAppointment, completeAppointment and cancelAppointment.
    public void changeAppointmentStatusFlow()
    {
        String apptId = readString("Appointment ID to change status");
        if (apptId == null || apptId.length() == 0)
        {
            showMessage("Invalid appointment ID.");
            return;
        }

        System.out.println("Actions: 1=Confirmar 2=Iniciar 3=Completar 4=Cancelar 5=Volver");
        String act = readString("Select action");

        boolean result = false;
        switch (act)
        {
            case "1": // Confirm
                result = manager.confirmAppointment(apptId);
                break;
            case "2": // Start
                result = manager.startAppointment(apptId);
                break;
            case "3": // Complete
                result = manager.completeAppointment(apptId);
                break;
            case "4": // Cancel
                result = manager.cancelAppointment(apptId);
                break;
            case "5":
                return;
            default:
                showMessage("Invalid action.");
                return;
        }

        showMessage(result ? "Status updated successfully." : "Could not update status (check current state).");
    }
}