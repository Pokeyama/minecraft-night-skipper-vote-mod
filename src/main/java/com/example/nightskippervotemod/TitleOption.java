package com.example.nightskippervotemod;

/**
 * Enum representing different options for title commands in Minecraft.
 */
public enum TitleOption {
    /**
     * Clear the title display.
     */
    CLEAR("clear"),

    /**
     * Reset the title display.
     */
    RESET("reset"),

    /**
     * Set the subtitle text.
     */
    SUBTITLE("subtitle"),

    /**
     * Set the title text.
     */
    TITLE("title"),

    /**
     * Set the action bar text.
     */
    ACTIONBAR("actionbar");

    private final String text;

    /**
     * Constructs a TitleOption enum with the specified text representation.
     *
     * @param text The text representation of the title option.
     */
    TitleOption(final String text) {
        this.text = text;
    }

    /**
     * Returns the string representation of the title option.
     *
     * @return The string representation of the title option.
     */
    public String getString() {
        return this.text;
    }
}
