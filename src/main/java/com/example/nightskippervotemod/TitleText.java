package com.example.nightskippervotemod;

public class TitleText {
    private final String text;
    private String color;
    private boolean italic;
    private boolean bold;

    /**
     * Constructs a TitleText object with the specified text.
     *
     * @param text The text of the title.
     */
    public TitleText(String text) {
        this.text = text;
    }

    /**
     * Sets the color of the title text.
     *
     * @param color The color of the title text.
     * @return The updated TitleText object.
     */
    public TitleText color(String color) {
        this.color = color;
        return this;
    }

    /**
     * Sets the italic style of the title text.
     *
     * @param italic Whether the title text should be italic.
     * @return The updated TitleText object.
     */
    public TitleText italic(boolean italic) {
        this.italic = italic;
        return this;
    }

    /**
     * Sets the bold style of the title text.
     *
     * @param bold Whether the title text should be bold.
     * @return The updated TitleText object.
     */
    public TitleText bold(boolean bold) {
        this.bold = bold;
        return this;
    }

    /**
     * Returns the text of the title.
     *
     * @return The text of the title.
     */
    public String getText() {
        return text;
    }

    /**
     * Returns the color of the title text.
     *
     * @return The color of the title text.
     */
    public String getColor() {
        return color;
    }

    /**
     * Returns whether the title text is italic.
     *
     * @return {@code true} if the title text is italic, {@code false} otherwise.
     */
    public boolean isItalic() {
        return italic;
    }

    /**
     * Returns whether the title text is bold.
     *
     * @return {@code true} if the title text is bold, {@code false} otherwise.
     */
    public boolean isBold() {
        return bold;
    }
}
