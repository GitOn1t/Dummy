package com.hexaware.cars.entity;

public class Incident {
    private int incidentID;
    private String incidentType;
    private String incidentDate;  // Stored as String for simplicity; can use LocalDate if preferred
    private String location;
    private String description;
    private String status;
    private int victimID;
    private int suspectID;
    private int officerID;

    // Default Constructor
    public Incident() {
    }

    // Parameterized Constructor (without incidentID for insertions)
    public Incident(String incidentType, String incidentDate, String location, String description,
                    String status, int victimID, int suspectID, int officerID) {
        this.incidentType = incidentType;
        this.incidentDate = incidentDate;
        this.location = location;
        this.description = description;
        this.status = status;
        this.victimID = victimID;
        this.suspectID = suspectID;
        this.officerID = officerID;
    }

    // Full Parameterized Constructor (with incidentID for updates, retrievals)
    public Incident(int incidentID, String incidentType, String incidentDate, String location, String description,
                    String status, int victimID, int suspectID, int officerID) {
        this.incidentID = incidentID;
        this.incidentType = incidentType;
        this.incidentDate = incidentDate;
        this.location = location;
        this.description = description;
        this.status = status;
        this.victimID = victimID;
        this.suspectID = suspectID;
        this.officerID = officerID;
    }

    // Getters and Setters
    public int getIncidentID() {
        return incidentID;
    }

    public void setIncidentID(int incidentID) {
        this.incidentID = incidentID;
    }

    public String getIncidentType() {
        return incidentType;
    }

    public void setIncidentType(String incidentType) {
        this.incidentType = incidentType;
    }

    public String getIncidentDate() {
        return incidentDate;
    }

    public void setIncidentDate(String incidentDate) {
        this.incidentDate = incidentDate;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getVictimID() {
        return victimID;
    }

    public void setVictimID(int victimID) {
        this.victimID = victimID;
    }

    public int getSuspectID() {
        return suspectID;
    }

    public void setSuspectID(int suspectID) {
        this.suspectID = suspectID;
    }

    public int getOfficerID() {
        return officerID;
    }

    public void setOfficerID(int officerID) {
        this.officerID = officerID;
    }

    @Override
    public String toString() {
        return "Incident{" +
                "incidentID=" + incidentID +
                ", incidentType='" + incidentType + '\'' +
                ", incidentDate='" + incidentDate + '\'' +
                ", location='" + location + '\'' +
                ", description='" + description + '\'' +
                ", status='" + status + '\'' +
                ", victimID=" + victimID +
                ", suspectID=" + suspectID +
                ", officerID=" + officerID +
                '}';
    }
}
