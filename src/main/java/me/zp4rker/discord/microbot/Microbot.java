package me.zp4rker.discord.microbot;

import me.zp4rker.discord.core.command.handler.CommandHandler;
import me.zp4rker.discord.core.exception.ExceptionHandler;
import me.zp4rker.discord.core.logger.ZLogger;
import me.zp4rker.discord.microbot.cmd.*;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.entities.Game;
import net.dv8tion.jda.core.hooks.AnnotatedEventManager;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author ZP4RKER
 */
public class Microbot {

    public static JDA jda;
    public static final String VERSION = "v1.0";
    public static ExecutorService async = Executors.newCachedThreadPool();

    public static void main(String[] args) throws Exception {
        Thread.setDefaultUncaughtExceptionHandler(new ExceptionHandler());

        ZLogger.initialise();

        CommandHandler handler = new CommandHandler("/");

        registerCommands(handler);

        jda = new JDABuilder(AccountType.BOT).setToken(args[0])
                .setEventManager(new AnnotatedEventManager())
                .addEventListener(handler)
                .setGame(Game.of("/help | " + VERSION)).buildAsync();
    }

    private static void registerCommands(CommandHandler handler) {
        handler.registerCommand(new CurrencyCommand());
        handler.registerCommand(new DelayCommand());
        handler.registerCommand(new EmbedCommand());
        handler.registerCommand(new JokeCommand());
        handler.registerCommand(new ReverseCommand());
        handler.registerCommand(new SelfDestructCommand());
        handler.registerCommand(new TimeCommand());
        handler.registerCommand(new DogCommand());
        handler.registerCommand(new HelpCommand(handler));
        handler.registerCommand(new InfoCommand(handler));
        handler.registerCommand(new RestartCommand());
    }

}
