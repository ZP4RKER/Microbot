package me.zp4rker.discord.microbot.cmd;

import gui.ava.html.Html2Image;
import me.zp4rker.discord.core.command.ICommand;
import me.zp4rker.discord.core.command.RegisterCommand;
import net.dv8tion.jda.core.entities.Message;

import java.io.File;

/**
 * @author ZP4RKER
 */
public class TestCommand implements ICommand {

    @RegisterCommand(aliases = "test", showInHelp = false)
    public void onCommand(Message message, String[] args) {
        String html = "<p>ZP4RKER</p><img src=\"%avatar%\" />";
        html = html.replace("%avatar%", "https://lh3.googleusercontent.com/-pNoP55FB7qs/AAAAAAAAAAI/AAAAAAAAAAA/ACnBePYV606C3fI83Shl_RvYuScLDdNl-w/s32-c-mo/photo.jpg");
        message.getTextChannel().sendMessage("HTML: `" + html + "`").queue();
        Html2Image.fromHtml(html).getImageRenderer().setHeight(500).saveImage(new File("test.png"));
        message.getTextChannel().sendFile(new File("test.png"), null).queue();
    }

}
