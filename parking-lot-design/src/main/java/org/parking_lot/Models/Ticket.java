package org.parking_lot.Models;

import java.time.LocalDateTime;
import java.util.UUID;

public class Ticket {
    private final String ticketId;
    private final String parkingSpotId;
    private final Vehicle vehicle;
    private final LocalDateTime entryTime;
    private LocalDateTime exitTime;

    public Ticket(String parkingSpotId, Vehicle vehicle) {
        this.ticketId = UUID.randomUUID().toString();
        this.parkingSpotId = parkingSpotId;
        this.vehicle = vehicle;
        this.entryTime = LocalDateTime.now();
    }
    
    public String getTicketId() {
        return ticketId;
    }

    public String getParkingSpotId() {
        return parkingSpotId;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public LocalDateTime getEntryTime() {
        return entryTime;
    }

    public LocalDateTime getExitTime() {
        return exitTime;
    }
    
    public void setExitTime(LocalDateTime exitTime) {
        this.exitTime = exitTime;
    }
    
    @Override
    public String toString() {
        return "Ticket{" +
                "ticketId='" + ticketId + '\'' +
                ", parkingSpotId='" + parkingSpotId + '\'' +
                ", vehicle=" + vehicle +
                ", entryTime=" + entryTime +
                ", exitTime=" + exitTime +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Ticket ticket = (Ticket) o;

        return ticketId.equals(ticket.ticketId);
    }

    @Override
    public int hashCode() {
        return ticketId.hashCode();
    }

}
