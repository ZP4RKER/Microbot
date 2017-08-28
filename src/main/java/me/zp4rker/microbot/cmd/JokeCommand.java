package me.zp4rker.microbot.cmd;

import me.zp4rker.core.command.ICommand;
import me.zp4rker.core.command.RegisterCommand;
import me.zp4rker.core.logger.ZLogger;
import me.zp4rker.microbot.util.ColorUtil;
import me.zp4rker.microbot.util.MessageUtil;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Message;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

/**
 * @author ZP4RKER
 */
public class JokeCommand implements ICommand {

    @RegisterCommand(aliases = "joke",
            description = "Tells a joke.",
            usage = "{prefix}joke")
    public void onCommand(Message message) {
        MessageUtil.bypassDeleteLogs(message);
        try {
            /*Yaml yaml = new Yaml();
            yaml.loadFromString(YamlUtil.fromUrl("http://tambal.azurewebsites.net/joke/random"));

            message.getChannel().sendMessage(new EmbedBuilder()
                    .setTitle(yaml.getString("joke"))
                    .setColor(ColorUtil.randomColour()).build()).complete();*/
            URL url = new URL("https://icanhazdadjoke.com/");
            URLConnection uc = url.openConnection();
            uc.setRequestProperty("Accept", "text/plain");
            InputStream is = uc.getInputStream();
            Scanner scanner = new Scanner(is);
            StringBuilder sb = new StringBuilder();
            while (scanner.hasNext()) {
                sb.append(scanner.next());
            }

            message.getChannel().sendMessage(new EmbedBuilder()
                    .setTitle(sb.toString())
                    .setColor(ColorUtil.randomColour()).build()).complete();
        } catch (Exception e) {
            ZLogger.warn("Could not send joke!");
        }
    }

}
