package com.example.nightskippervotemod;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.logging.LogUtils;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.common.Mod;
import org.slf4j.Logger;

import java.util.List;
import java.util.UUID;

/**
 * Main mod class for NightSkipperVoteMod.
 */
@Mod(NightSkipperVoteMod.MODID)
public class NightSkipperVoteMod {
    public static final String MODID = "nightskippervotemod";
    private static final Logger LOGGER = LogUtils.getLogger();
    private Localize localize = new ResourceHandler().getLocalizeJAJPResource();
    private boolean night = false;
    private Vote vote = new Vote();

    /**
     * Constructor for ExampleMod.
     * Registers the mod to the MinecraftForge EVENT_BUS.
     */
    public NightSkipperVoteMod() {
        MinecraftForge.EVENT_BUS.register(this);
    }

    /**
     * Event handler for the ServerStartingEvent.
     *
     * @param event The ServerStartingEvent instance.
     */
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        LOGGER.info("HELLO from server starting");
    }

    /**
     * Event handler for the night starting event.
     *
     * @param event The TickEvent.ServerTickEvent instance.
     */
    @SubscribeEvent
    public void onNightStarting(TickEvent.ServerTickEvent event) {
        if (event.phase == TickEvent.Phase.END && event.side == LogicalSide.SERVER) {
            MinecraftServer server = event.getServer();
            List<ServerPlayer> serverPlayers = server.getPlayerList().getPlayers();

            if (serverPlayers.size() <= 1){
                return;
            }

            long currentDaytime = Utils.getCurrentDaytime(server);

            if (!night && MinecraftTime.NIGHT == Utils.getTimeFromDaytime(currentDaytime)) {
                LOGGER.debug("Server DayTime: {}", currentDaytime);
                LOGGER.debug("-------------------{}-----------------------", this.localize.getTitle());
                LOGGER.debug("-------------------{}-----------------------", this.localize.getVote());
                LOGGER.debug("-------------------{}-----------------------", this.localize.getVoteInstructions());

                this.vote = new Vote();
                executeNightTitleToActionBar(server, localize.getTitle());
                for (Player player : serverPlayers) {
                    player.sendSystemMessage(Component.nullToEmpty(this.localize.getVote()));
                    player.sendSystemMessage(Component.nullToEmpty(this.localize.getVoteInstructions()));
                }
                night = true;
            }
        }
    }

    /**
     * Event handler for delaying the vote result.
     *
     * @param event The TickEvent.ServerTickEvent instance.
     */
    @SubscribeEvent
    public void delayVoteResult(TickEvent.ServerTickEvent event) {
        if (event.phase == TickEvent.Phase.END && event.side == LogicalSide.SERVER) {
            MinecraftServer server = event.getServer();
            List<ServerPlayer> serverPlayers = server.getPlayerList().getPlayers();

            if (serverPlayers.size() <= 1){
                return;
            }

            long currentDaytime = Utils.getCurrentDaytime(event.getServer());

            if (MinecraftTime.VOTERESULT == Utils.getTimeFromVoteResultTime(currentDaytime)) {
                LOGGER.debug("Server DayTime: {}", currentDaytime);
                this.vote.setHold(serverPlayers);
                for (Player player : serverPlayers) {
                    player.sendSystemMessage(Component.nullToEmpty(this.vote.toString()));
                    LOGGER.debug("result : {}", this.vote.toString());
                }
                if (this.vote.totalling()) {
                    executeNightTitleToActionBar(server, localize.getSkipNight());
                    // 朝にする
                    Utils.setWorldDayTime(server, 500);
                    return;
                }
                executeNightTitleToActionBar(server, localize.getPreservedNight());
            }
        }
    }

    /**
     * Event handler for the morning starting event.
     *
     * @param event The TickEvent.ServerTickEvent instance.
     */
    @SubscribeEvent
    public void onMorningStarting(TickEvent.ServerTickEvent event) {
        if (event.phase == TickEvent.Phase.END && event.side == LogicalSide.SERVER) {
            MinecraftServer server = event.getServer();
            List<ServerPlayer> serverPlayers = server.getPlayerList().getPlayers();

            if (serverPlayers.size() <= 1){
                return;
            }

            long currentDaytime = Utils.getCurrentDaytime(event.getServer());

            // 起床時間の判定
            if (night && MinecraftTime.MORNING == Utils.getTimeFromDaytime(currentDaytime)) {
                LOGGER.debug("Server DayTime: {}", currentDaytime);

                night = false;
            }
        }
    }

    /**
     * Event handler for the morning starting event.
     *
     * @param event The TickEvent.ServerTickEvent instance.
     */
    @SubscribeEvent
    public void onRegisterYCommand(RegisterCommandsEvent event) {
        onVoteRegisterCommand(event, "y", context -> {
            LOGGER.debug("{} is Agree.", context.getSource().getPlayerOrException().getName().getString());
            context.getSource().getPlayerOrException().sendSystemMessage(Component.nullToEmpty(this.localize.getVotedAgree()));
            this.vote.addAgree();
        });
    }

    /**
     * Event handler for registering the "n" vote command.
     *
     * @param event   The RegisterCommandsEvent instance.
     */
    @SubscribeEvent
    public void onRegisterNCommand(RegisterCommandsEvent event) {
        onVoteRegisterCommand(event, "n", context -> {
            LOGGER.debug("{} is Oppose.", context.getSource().getPlayerOrException().getName().getString());
            context.getSource().getPlayerOrException().sendSystemMessage(Component.nullToEmpty(this.localize.getVotedOppose()));
            this.vote.addOppose();
        });
    }

    @SubscribeEvent
    public void onRegisterLocalizeCommand(RegisterCommandsEvent event) {

        LiteralArgumentBuilder<CommandSourceStack> builder = Commands.literal("lang")
                .then(Commands.literal("en")
                        .executes(context -> {
                            this.localize = new ResourceHandler().getLocalizeENUSResource();
                            context.getSource().getPlayerOrException().sendSystemMessage(Component.nullToEmpty("[Dev]:MOD language is now English."));
                            LOGGER.debug("MOD language is now English.");
                            return Command.SINGLE_SUCCESS;
                        }))
                .then(Commands.literal("ja")
                        .executes(context -> {
                            this.localize = new ResourceHandler().getLocalizeJAJPResource();
                            context.getSource().getPlayerOrException().sendSystemMessage(Component.nullToEmpty("[Dev]:MODの言語が日本語になりました。"));
                            LOGGER.debug("MODの言語が日本語になりました。");
                            return Command.SINGLE_SUCCESS;
                        }));

        event.getDispatcher().register(builder);
    }


    /**
     * Executes a title command to display the night title on the action bar.
     *
     * @param event The MinecraftServer instance.
     * @param text  The title text to display.
     */
    private void executeNightTitleToActionBar(MinecraftServer event, String text) {
        String commandText = new TitleBuilder()
                .setSelector(Selector.ALL)
                .setTitleOption(TitleOption.ACTIONBAR)
                .setTitleText(new TitleText(text).bold(true))
                .build();

        LOGGER.debug("execute command: {}", commandText);

        if (executeServerCommand(event, commandText) != 1) {
            LOGGER.debug("-------- Title Command Failure ---------");
        }
    }

    /**
     * Executes a server command.
     *
     * @param server The MinecraftServer instance.
     * @param text   The command to execute.
     * @return 0 if the command fails, 1 if the command succeeds.
     */
    private int executeServerCommand(MinecraftServer server, String text) {
        CommandSourceStack commandSourceStack = server.createCommandSourceStack().withSuppressedOutput().withPermission(4);

        var commanddispatcher = server.getCommands().getDispatcher();
        var results = commanddispatcher.parse(text, commandSourceStack);
        return server.getCommands().performCommand(results, text);
    }

    /**
     * Event handler for registering a vote command.
     *
     * @param event    The RegisterCommandsEvent instance.
     * @param command  The command string.
     * @param executeAction The action to be executed when the command is invoked.
     * @param <E>      The type of the exception that can be thrown by the executeAction.
     */
    private <E extends Exception> void onVoteRegisterCommand(RegisterCommandsEvent event, String command, ThrowingConsumer<CommandContext<CommandSourceStack>, E> executeAction) {
        LiteralArgumentBuilder<CommandSourceStack> builder = Commands.literal(command)
                .executes(context -> {
                    MinecraftServer server = context.getSource().getServer();

                    if (server.getPlayerList().getPlayers().size() <= 1){
                        context.getSource().getPlayerOrException().sendSystemMessage(Component.nullToEmpty(this.localize.getNotNight()));
                        return Command.SINGLE_SUCCESS;
                    }

                    long daytime = Utils.getCurrentDaytime(server);
                    if (MinecraftTime.NIGHT != Utils.getTimeFromDaytime(daytime)){
                        context.getSource().getPlayerOrException().sendSystemMessage(Component.nullToEmpty(this.localize.getNotNight()));
                        return Command.SINGLE_SUCCESS;
                    }
                    UUID uuid = context.getSource().getPlayerOrException().getUUID();
                    if (this.vote.coverUuid(uuid)) {
                        LOGGER.debug("{} is voted...", context.getSource().getPlayerOrException().getName().getString());
                        context.getSource().getPlayerOrException().sendSystemMessage(Component.nullToEmpty(this.localize.getVotedStatus()));
                        return Command.SINGLE_SUCCESS;
                    }

                    try {
                        executeAction.accept(context);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }

                    this.vote.setUuid(uuid);
                    LOGGER.debug(uuid.toString());
                    return Command.SINGLE_SUCCESS;
                });

        event.getDispatcher().register(builder);
    }

    /**
     * Functional interface for a consumer that can throw an exception.
     *
     * @param <T> The type of the input to the consumer.
     * @param <E> The type of the exception that can be thrown.
     */
    @FunctionalInterface
    interface ThrowingConsumer<T, E extends Exception> {
        void accept(T t) throws E;
    }
}
