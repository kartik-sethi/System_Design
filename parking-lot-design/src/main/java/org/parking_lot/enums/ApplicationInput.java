package org.parking_lot.enums;

import java.util.Arrays;

/**
 * Enum representing the available user commands in the Parking Lot Application.
 * Each command has a name and an associated integer input key.
 */
public enum ApplicationInput {

    /**
     * Represents the entry of a vehicle into the parking lot.
     */
    ENTRY("Entry", 0),

    /**
     * Represents the exit of a vehicle from the parking lot.
     */
    EXIT("Exit", 1),

    /**
     * Adds a new parking spot to the system.
     */
    ADD_SPOT("Add Parking Spot", 2),

    /**
     * Removes an existing parking spot from the system.
     */
    REMOVE_SPOT("Remove Parking Spot", 3),

    /**
     * Stops or terminates the application.
     */
    STOP("Stop", 9);

    private final String name;
    private final int inputKey;

    /**
     * Constructor for ApplicationInput enum.
     *
     * @param name     Display name of the command.
     * @param inputKey Integer key used by the user to trigger this command.
     */
    ApplicationInput(String name, int inputKey) {
        this.name = name;
        this.inputKey = inputKey;
    }

    /**
     * Gets the display name of the input command.
     *
     * @return name of the input command.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Gets the input key associated with the command.
     *
     * @return input key.
     */
    public int getInputKey() {
        return this.inputKey;
    }

    /**
     * Provides a formatted message describing the user input option.
     *
     * @return formatted user instruction message.
     */
    public String getMessage() {
        return String.format("Press %s to %s", inputKey, name);
    }

    /**
     * Finds an ApplicationInput enum value by its associated input key.
     *
     * @param inputKey integer input entered by user.
     * @return matching ApplicationInput enum value.
     * @throws RuntimeException if the inputKey does not map to any known command.
     */
    public static ApplicationInput findInputFromInputKey(int inputKey) {
        return Arrays.stream(ApplicationInput.values())
                .filter(e -> e.inputKey == inputKey)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Invalid Input"));
    }
}
