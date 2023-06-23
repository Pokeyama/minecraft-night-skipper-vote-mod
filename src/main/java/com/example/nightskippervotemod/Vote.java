package com.example.nightskippervotemod;

import net.minecraft.server.level.ServerPlayer;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * The Vote class represents a voting system.
 * It keeps track of the votes and provides methods to manage the voting process.
 */
public class Vote {

    private final List<UUID> uuidList = new ArrayList<>();
    private int agree;
    private int oppose;
    private int hold;

    /**
     * Sets the UUID of a player who has voted.
     *
     * @param uuid The UUID of the player who has voted.
     */
    public void setUuid(UUID uuid) {
        this.uuidList.add(uuid);
    }

    /**
     * Checks if a player with the given UUID has voted.
     *
     * @param anotherUuid The UUID of the player to check.
     * @return true if the player has voted, false otherwise.
     */
    public boolean coverUuid(UUID anotherUuid) {
        for (UUID uuid : uuidList) {
            if (uuid.equals(anotherUuid)) {
                // cover uuid
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if the votes have reached the required total count.
     *
     * @return true if the total votes satisfy the required count, false otherwise.
     */
    public boolean totalling() {
        return this.oppose <= this.agree + hold;
    }

    /**
     * Increments the count of agree votes.
     */
    public void addAgree() {
        this.agree++;
    }

    /**
     * Increments the count of oppose votes.
     */
    public void addOppose() {
        this.oppose++;
    }

    /**
     * Sets the hold count based on the remaining players in the voting process.
     * If the number of remaining players is equal to the total votes, hold count will not be set.
     * Otherwise, hold count will be set to the difference between the remaining players and the total votes.
     *
     * @param serverPlayers The list of server players involved in the voting process.
     */
    public void setHold(List<ServerPlayer> serverPlayers) {
        if (serverPlayers.size() == this.agree + this.oppose) {
            return;
        }
        this.hold = serverPlayers.size() - (this.agree + this.oppose);
    }

    /**
     * Returns a string representation of the Vote object.
     *
     * @return A string representation of the Vote object.
     */
    public String toString() {
        return String.format("[Dev]: agree %d, oppose %d, hold %d", agree, oppose, hold);
    }
}
