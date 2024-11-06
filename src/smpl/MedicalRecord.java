package smpl;

import java.util.Scanner;

public class MedicalRecord {
    static Scanner scanner = new Scanner(System.in);
    static config dbConfig = new config();

    // Display the Medical Record panel and allow actions
    public static void medicalRecordPanel() {
        int action;
        do {
            System.out.println("MEDICAL RECORD MANAGEMENT");
            System.out.println("1. Add Medical Record");
            System.out.println("2. View Medical Records");
            System.out.println("3. Update Medical Record");
            System.out.println("4. Delete Medical Record");
            System.out.println("5. Back to Main Menu");
            System.out.print("Enter action: ");
            action = scanner.nextInt();
            scanner.nextLine(); // consume the newline

            switch (action) {
                case 1:
                    addMedicalRecord();
                    break;
                case 2:
                    viewMedicalRecords();
                    break;
                case 3:
                    updateMedicalRecord();
                    break;
                case 4:
                    deleteMedicalRecord();
                    break;
                case 5:
                    System.out.println("Returning to main menu...");
                    break;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        } while (action != 5);
    }

    // Method to add a new medical record
    private static void addMedicalRecord() {
        System.out.println("Enter Record ID: ");
        int record_id = scanner.nextInt();
        System.out.println("Enter Patient ID: ");
        int p_id = scanner.nextInt();
        System.out.println("Enter Doctor ID: ");
        int dr_id = scanner.nextInt();
        scanner.nextLine(); // consume newline
        System.out.println("Enter Visit Date (yyyy-mm-dd): ");
        String visit_date = scanner.nextLine();
        System.out.println("Enter Diagnosis: ");
        String diagnosis = scanner.nextLine();
        System.out.println("Enter Treatment: ");
        String treatment = scanner.nextLine();
        System.out.println("Enter Notes: ");
        String note = scanner.nextLine();

        String sql = "INSERT INTO medical_record "
                + "(record_id, p_id, dr_id, visit_date,"
                + " diagnosis, treatment, note) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";
        dbConfig.addPatient(sql, record_id, p_id, dr_id,
                visit_date, diagnosis, treatment, note
        );
    }

    // Method to view all medical records
    private static void viewMedicalRecords() {
        String sqlQuery = "SELECT * FROM medical_record";
        String[] columnHeaders = 
        {"Record ID", "Patient ID", "Doctor ID", "Visit Date", 
            "Diagnosis", "Treatment", "Notes"};
        String[] columnNames = 
        {"record_id", "p_id", "dr_id", "visit_date", 
            "diagnosis", "treatment", "note"};
        dbConfig.viewRecords(sqlQuery, columnHeaders,
                columnNames
        );
    }

    // Method to update a medical record
    private static void updateMedicalRecord() {
        System.out.println("Enter Record ID to update: ");
        int record_id = scanner.nextInt();
        scanner.nextLine(); // consume newline
        System.out.println("Enter new Diagnosis: ");
        String diagnosis = scanner.nextLine();
        System.out.println("Enter new Treatment: ");
        String treatment = scanner.nextLine();
        System.out.println("Enter new Notes: ");
        String note = scanner.nextLine();

        String sql = 
        "UPDATE medical_record SET diagnosis = ?, "
           + "treatment = ?, note = ? WHERE record_id = ?";
        dbConfig.updateRecord(sql, diagnosis,
                treatment, note, record_id
        );
    }

    // Method to delete a medical record
    private static void deleteMedicalRecord() {
        System.out.println("Enter Record ID to delete: ");
        int record_id = scanner.nextInt();

        String sql = "DELETE FROM medical_record WHERE record_id = ?";
        dbConfig.deleteRecord(sql, record_id);
    }
}
