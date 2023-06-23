package com.example.nightskippervotemod;

/**
 * Localization class for storing localized strings related to voting.
 */
public class Localize {

    private final String prefix = "[Dev]:";

    /**
     * The localized title for the voting.
     */
    private String title;

    /**
     * The localized description of the voting process.
     */
    private String vote;

    /**
     * Instructions for participating in the vote.
     */
    private String voteInstructions;

    /**
     * Message indicating that the player has voted "agree".
     */
    private String votedAgree;

    /**
     * Message indicating that the player has voted "oppose".
     */
    private String votedOppose;

    /**
     * Message indicating that the player has voted "hold".
     */
    private String votedHold;

    /**
     * Message indicating that the player has already voted.
     */
    private String votedStatus;

    /**
     * Message Not night.
     */
    private String notNight;

    /**
     * Message displaying the vote result.
     */
    private String voteResult;

    /**
     * Message indicating that the night is being skipped.
     */
    private String skipNight;

    /**
     * Message indicating that the night is preserved.
     */
    private String preservedNight;

    /**
     * Retrieves the localized title for the voting.
     *
     * @return The title string.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Retrieves the localized description of the voting process.
     *
     * @return The voting description string.
     */
    public String getVote() {
        return prefix + vote;
    }

    /**
     * Retrieves the instructions for participating in the vote.
     *
     * @return The vote instructions string.
     */
    public String getVoteInstructions() {
        return prefix + voteInstructions;
    }

    /**
     * Retrieves the message indicating that the player has voted "agree".
     *
     * @return The voted "agree" message string.
     */
    public String getVotedAgree() {
        return prefix + votedAgree;
    }

    /**
     * Retrieves the message indicating that the player has voted "oppose".
     *
     * @return The voted "oppose" message string.
     */
    public String getVotedOppose() {
        return prefix + votedOppose;
    }

    /**
     * Retrieves the message indicating that the player has voted "hold".
     *
     * @return The voted "hold" message string.
     */
    public String getVotedHold() {
        return prefix + votedHold;
    }

    /**
     * Retrieves the message indicating that the player has already voted.
     *
     * @return The voted status message string.
     */
    public String getVotedStatus() {
        return prefix + votedStatus;
    }

    /**
     * Retrieves the message displaying the vote result.
     *
     * @return The vote result message string.
     */
    public String getVoteResult() {
        return prefix + voteResult;
    }

    /**
     *
     * @return The not Night message string.
     */
    public String getNotNight() {
        return prefix + notNight;
    }

    /**
     * Retrieves the message indicating that the night is being skipped.
     *
     * @return The skip night message string.
     */
    public String getSkipNight() {
        return skipNight;
    }

    /**
     * Retrieves the message indicating that the night is preserved.
     *
     * @return The preserved night message string.
     */
    public String getPreservedNight() {
        return prefix + preservedNight;
    }
}
