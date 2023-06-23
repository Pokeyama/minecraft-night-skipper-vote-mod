package com.example.nightskippervotemod;

/**
 * Enum representing different selectors for targeting entities in commands.
 */
public enum Selector {
    /**
     * Selects all entities.
     */
    ALL("@a"),

    /**
     * Selects the nearest player.
     */
    NEAREST("@p"),

    /**
     * Selects a random player.
     */
    RANDOM("@r"),

    /**
     * Represents the entity executing the command.
     */
    COMMAND_ENTITY("@s"),

    /**
     * Selects all entities.
     */
    ALL_ENTITIES("@e");

    private final String text;

    /**
     * Constructs a Selector enum with the specified text representation.
     *
     * @param text The text representation of the selector.
     */
    Selector(final String text) {
        this.text = text;
    }

    /**
     * Returns the string representation of the selector.
     *
     * @return The string representation of the selector.
     */
    public String getString() {
        return this.text;
    }
}