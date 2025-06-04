package com.hexaware.cars.main;

import com.hexaware.cars.dao.CrimeAnalysisServiceImpl;
import com.hexaware.cars.dao.ICrimeAnalysisService;
import com.hexaware.cars.entity.Incident;
import com.hexaware.cars.entity.Report;
import com.hexaware.cars.exception.IncidentNumberNotFoundException;

import java.util.Collection;
import java.util.Scanner;

public class MainModule {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ICrimeAnalysisService service = new CrimeAnalysisServiceImpl();

        while (true) {
            System.out.println("\n=== Crime Analysis & Reporting System ===");
            System.out.println("1. Create Incident");
            System.out.println("2. Update Incident Status");
            System.out.println("3. Get Incidents by Date Range");
            System.out.println("4. Search Incidents by Type");
            System.out.println("5. Generate Incident Report");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");
            int choice = sc.nextInt();
            sc.nextLine(); // Consume newline

            try {
                switch (choice) {
                    case 1:
                        Incident incident = new Incident();
                        System.out.print("Enter Incident Type: ");
                        incident.setIncidentType(sc.nextLine());
                        System.out.print("Enter Incident Date (YYYY-MM-DD): ");
                        incident.setIncidentDate(sc.nextLine());
                        System.out.print("Enter Location: ");
                        incident.setLocation(sc.nextLine());
                        System.out.print("Enter Description: ");
                        incident.setDescription(sc.nextLine());
                        System.out.print("Enter Status: ");
                        incident.setStatus(sc.nextLine());
                        System.out.print("Enter Victim ID: ");
                        incident.setVictimID(sc.nextInt());
                        System.out.print("Enter Suspect ID: ");
                        incident.setSuspectID(sc.nextInt());
                        System.out.print("Enter Officer ID: ");
                        incident.setOfficerID(sc.nextInt());

                        boolean created = service.createIncident(incident);
                        System.out.println(created ? "Incident created successfully." : "Failed to create incident.");
                        break;

                    case 2:
                        System.out.print("Enter Incident ID to update: ");
                        int idToUpdate = sc.nextInt();
                        sc.nextLine();
                        System.out.print("Enter new status: ");
                        String newStatus = sc.nextLine();
                        boolean updated = service.updateIncidentStatus(newStatus, idToUpdate);
                        if (!updated) {
                            throw new IncidentNumberNotFoundException("Incident ID " + idToUpdate + " not found.");
                        }
                        System.out.println("Status updated successfully.");
                        break;

                    case 3:
                        System.out.print("Enter Start Date (YYYY-MM-DD): ");
                        String start = sc.nextLine();
                        System.out.print("Enter End Date (YYYY-MM-DD): ");
                        String end = sc.nextLine();
                        Collection<Incident> incidents = service.getIncidentsInDateRange(start, end);
                        incidents.forEach(System.out::println);
                        break;

                    case 4:
                        System.out.print("Enter Incident Type to search: ");
                        String type = sc.nextLine();
                        Collection<Incident> searchResults = service.searchIncidents(type);
                        searchResults.forEach(System.out::println);
                        break;

                    case 5:
                        System.out.print("Enter Incident ID to generate report for: ");
                        int incidentId = sc.nextInt();
                        sc.nextLine();
                        // Minimal Incident object needed to pass to service
                        Incident reportIncident = new Incident();
                        reportIncident.setIncidentID(incidentId);
                        System.out.print("Enter Officer ID: ");
                        reportIncident.setOfficerID(sc.nextInt());
                        sc.nextLine();

                        Report report = service.generateIncidentReport(reportIncident);
                        if (report != null) {
                            System.out.println("Report Generated: \n" + report);
                        } else {
                            System.out.println("Failed to generate report.");
                        }
                        break;

                    case 6:
                        System.out.println("Exiting... Thank you!");
                        sc.close();
                        return;

                    default:
                        System.out.println("Invalid option. Try again.");
                }
            } catch (IncidentNumberNotFoundException e) {
                System.out.println("Error: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("An unexpected error occurred: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }
}
