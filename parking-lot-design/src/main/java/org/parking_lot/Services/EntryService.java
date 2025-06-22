package org.parking_lot.Services;

import org.parking_lot.Models.Ticket;
import org.parking_lot.Models.Vehicle;

public interface EntryService {
    Ticket enterVehicle(Vehicle vehicle);
}
