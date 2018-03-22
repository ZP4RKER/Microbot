package co.zpdev.bots.microbot;

import co.zpdev.bots.core.command.handler.CommandHandler;
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
    public static final String VERSION = "v1.5.0";
    public static ExecutorService async = Executors.newCachedThreadPool();

    public static void main(String[] args) throws Exception {
        CommandHandler handler = new CommandHandler("/", "co.zpdev.bots.microbot.cmd");

        jda = new JDABuilder(AccountType.BOT).setToken(args[0])
                .setEventManager(new AnnotatedEventManager())
                .addEventListener(handler)
                .setGame(Game.playing("/help | " + VERSION)).buildAsync();
    }

}
