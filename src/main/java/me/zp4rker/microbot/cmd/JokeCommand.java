package me.zp4rker.microbot.cmd;

import me.zp4rker.core.command.ICommand;
import me.zp4rker.core.command.RegisterCommand;
import me.zp4rker.core.logger.ZLogger;
import me.zp4rker.core.yaml.file.Yaml;
import me.zp4rker.microbot.util.ColorUtil;
import me.zp4rker.microbot.util.MessageUtil;
import me.zp4rker.microbot.util.YamlUtil;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Message;

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
            Yaml yaml = new Yaml();
            yaml.loadFromString(YamlUtil.fromUrl("http://tambal.azurewebsites.net/joke/random"));

            message.getChannel().sendMessage(new EmbedBuilder()
                    .setTitle(yaml.getString("joke"))
                    .setColor(ColorUtil.randomColour()).build()).complete();
        } catch (Exception e) {
            ZLogger.warn("Could not send joke!");
        }
    }

}
