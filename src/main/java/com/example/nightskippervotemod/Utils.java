package com.example.nightskippervotemod;

import net.minecraft.server.MinecraftServer;

/**
 * Utility class for handling Minecraft server time-related operations.
 */
public class Utils {

    /**
     * Retrieves the current daytime in the server's overworld.
     *
     * @param server The Minecraft server instance.
     * @return The current daytime, ranging from 0 to 23999.
     */
    public static long getCurrentDaytime(MinecraftServer server) {
        return server.getWorldData().overworldData().getDayTime() % 24000;
    }

    /**
     * Sets the daytime of the server's overworld.
     *
     * @param server The Minecraft server instance.
     * @param time   The new daytime to set.
     */
    public static void setWorldDayTime(MinecraftServer server, int time) {
        server.getWorldData().overworldData().setDayTime(time);
    }

    /**
     * Converts a vote result time to the corresponding MinecraftTime enum.
     *
     * @param currentDaytime The current daytime value.
     * @return The corresponding MinecraftTime enum value.
     *         Returns null for invalid or out-of-range values.
     */
    public static MinecraftTime getTimeFromDaytime(long currentDaytime) {
        if (currentDaytime >= 13000 && currentDaytime < 23000) {
            return MinecraftTime.NIGHT;
        } else if (currentDaytime >= 0 && currentDaytime < 13000) {
            return MinecraftTime.MORNING;
        } else {
            // Handle invalid or out-of-range values
            return null; // Or throw an exception, or choose a default time value
        }
    }

    /**
     * Converts a vote result time to the corresponding MinecraftTime enum.
     *
     * @param currentDaytime The current daytime value.
     * @return The corresponding MinecraftTime enum value.
     *         Returns null for invalid or out-of-range values.
     */
    public static MinecraftTime getTimeFromVoteResultTime(long currentDaytime) {
        if (currentDaytime == 13300) {
            return MinecraftTime.VOTERESULT;
        }
        // Handle invalid or out-of-range values
        return null; // Or throw an exception, or choose a default time value
    }
}
