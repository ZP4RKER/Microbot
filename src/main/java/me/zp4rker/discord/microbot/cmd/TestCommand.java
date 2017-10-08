package me.zp4rker.discord.microbot.cmd;

import me.zp4rker.discord.core.command.ICommand;
import me.zp4rker.discord.core.command.RegisterCommand;
import net.dv8tion.jda.core.entities.Message;
import org.xhtmlrenderer.swing.Java2DRenderer;
import org.xhtmlrenderer.util.FSImageWriter;

import java.io.File;
import java.io.FileWriter;

/**
 * @author ZP4RKER
 */
public class TestCommand implements ICommand {

    @RegisterCommand(aliases = "test", showInHelp = false)
    public void onCommand(Message message, String[] args) {
        String html = "<?xml version=\"1.0\"?>\n<p>ZP4RKER</p>\n<img src=\"%avatar%\" />";
        html = html.replace("%avatar%", message.getAuthor().getEffectiveAvatarUrl());
        message.getTextChannel().sendMessage("HTML: `" + html + "`").queue();
        try {
            FileWriter writer = new FileWriter("test.html");
            writer.write(html);
            writer.flush();
            writer.close();

            Java2DRenderer renderer = new Java2DRenderer("test.html", 500);
            FSImageWriter w = new FSImageWriter();
            w.write(renderer.getImage(), "test.png");
        } catch (Exception e) {
            e.printStackTrace();
        }

        message.getTextChannel().sendFile(new File("test.png"), null).queue();
    }

}
