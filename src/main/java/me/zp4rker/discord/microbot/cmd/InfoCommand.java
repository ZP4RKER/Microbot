package me.zp4rker.discord.microbot.cmd;

import me.zp4rker.discord.core.command.ICommand;
import me.zp4rker.discord.core.command.RegisterCommand;
import me.zp4rker.discord.core.command.handler.CommandHandler;
import me.zp4rker.discord.microbot.Microbot;
import me.zp4rker.discord.microbot.util.ColorUtil;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.entities.Message;

/**
 * @author ZP4RKER
 */
public class InfoCommand implements ICommand {

    private CommandHandler handler;

    public InfoCommand(CommandHandler handler) {
        this.handler = handler;
    }

    @RegisterCommand(aliases = "info",
            description = "Displays info about the bot.",
            usage = "{prefix}info")
    public void onCommand(Message message) {
        message.delete().queue();

        EmbedBuilder embed = new EmbedBuilder();
        compileEmbed(embed, message.getJDA());

        message.getTextChannel().sendMessage(embed.build()).complete();
    }

    private void compileEmbed(EmbedBuilder embed, JDA jda) {
        embed.setAuthor("Microbot", "https://github.com/ZP4RKER/microbot", jda.getSelfUser().getEffectiveAvatarUrl());

        embed.addField("Version", Microbot.VERSION, true);
        embed.addField("Commands", handler.getCommands().size() + "", true);
        embed.addField("Author", "ZP4RKER#3333", true);
        embed.addField("Github", "https://github.com/ZP4RKER/microbot", true);
        embed.addField("Description", "An open-source Discord bot, which adds a levelling system to your server.", true);

        embed.setColor(ColorUtil.randomColour());
    }

}
