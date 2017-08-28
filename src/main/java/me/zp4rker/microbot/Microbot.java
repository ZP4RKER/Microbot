package me.zp4rker.microbot;

import me.zp4rker.core.command.handler.CommandHandler;
import me.zp4rker.core.logger.ZLogger;
import me.zp4rker.microbot.cmd.*;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.hooks.AnnotatedEventManager;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author ZP4RKER
 */
public class Microbot {

    public static ExecutorService async = Executors.newCachedThreadPool();

    public static void main(String[] args) throws Exception {
        ZLogger.initialise();

        CommandHandler handler = new CommandHandler("/");

        JDA jda = new JDABuilder(AccountType.BOT).setToken(args[0])
                .setEventManager(new AnnotatedEventManager())
                .addEventListener(handler).buildAsync();
    }

    private static void registerCommands(CommandHandler handler) {
        handler.registerCommand(new CurrencyCommand());
        handler.registerCommand(new DelayCommand());
        handler.registerCommand(new EmbedCommand());
        handler.registerCommand(new IMcApiCommand());
        handler.registerCommand(new JokeCommand());
        handler.registerCommand(new McApiCommand());
        handler.registerCommand(new ReverseCommand());
        handler.registerCommand(new SelfDestructCommand());
        handler.registerCommand(new TimeCommand());
    }

}
