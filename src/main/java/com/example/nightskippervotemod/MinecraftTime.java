package com.example.nightskippervotemod;

/**
 * Enum representing different time values in Minecraft.
 */
public enum MinecraftTime {
    DAWN("dawn"),
    MORNING("morning"),
    NOON("noon"),
    AFTERNOON("afternoon"),
    DUSK("dusk"),
    NIGHT("night"),
    MIDNIGHT("midnight"),
    VOTERESULT("voteResult");

    private final String text;

    /**
     * Constructs a Time enum with the specified text representation.
     *
     * @param text The text representation of the time value.
     */
    MinecraftTime(final String text) {
        this.text = text;
    }

    /**
     * Returns the string representation of the time value.
     *
     * @return The string representation of the time value.
     */
    public String getString() {
        return this.text;
    }
}
