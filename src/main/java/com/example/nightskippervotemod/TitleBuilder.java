package com.example.nightskippervotemod;

import com.google.gson.Gson;

/**
 * Builder class for constructing title commands in Minecraft.
 */
public class TitleBuilder {
    private final StringBuilder commandBuilder;

    private Selector selector;
    private TitleOption titleOption;
    private TitleText titleText;

    /**
     * Constructs a new TitleBuilder instance.
     */
    public TitleBuilder() {
        commandBuilder = new StringBuilder();
        commandBuilder.append("title");
    }

    /**
     * Sets the selector for the title command.
     *
     * @param selector The selector to set.
     * @return The TitleBuilder instance.
     */
    public TitleBuilder setSelector(Selector selector) {
        this.selector = selector;
        return this;
    }

    /**
     * Sets the title option for the title command.
     *
     * @param titleOption The title option to set.
     * @return The TitleBuilder instance.
     */
    public TitleBuilder setTitleOption(TitleOption titleOption) {
        this.titleOption = titleOption;
        return this;
    }

    /**
     * Sets the title text for the title command.
     *
     * @param titleText The title text to set.
     * @return The TitleBuilder instance.
     */
    public TitleBuilder setTitleText(TitleText titleText) {
        this.titleText = titleText;
        return this;
    }

    /**
     * Builds the title command as a string.
     *
     * @return The title command as a string.
     */
    public String build() {
        if (selector == null || titleOption == null || titleText == null) {
            return "";
        }

        Gson gson = new Gson();
        commandBuilder.append(" ")
                .append(selector.getString())
                .append(" ")
                .append(titleOption.getString())
                .append(" ")
                .append(gson.toJson(titleText));

        return commandBuilder.toString();
    }
}

