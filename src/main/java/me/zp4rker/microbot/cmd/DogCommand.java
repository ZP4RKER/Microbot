package me.zp4rker.microbot.cmd;

import me.zp4rker.core.command.ICommand;
import me.zp4rker.core.command.RegisterCommand;
import me.zp4rker.core.logger.ZLogger;
import me.zp4rker.core.yaml.file.Yaml;
import me.zp4rker.microbot.util.YamlUtil;
import net.dv8tion.jda.core.entities.Message;

import java.util.LinkedHashMap;

import static me.zp4rker.microbot.util.MessageUtil.*;

/**
 * @author ZP4RKER
 */
public class DogCommand implements ICommand {

    @RegisterCommand(aliases = "dog",
                    description = "Sends a random picture of a dog.",
                    usage = "{prefix}dog")
    public void onCommand(Message message) {
        bypassDeleteLogs(message);

        try{
            Yaml yaml = new Yaml();
            yaml.loadFromString(YamlUtil.fromUrl("https://api.thedogapi.co.uk/v2/dog.php").replaceAll("\\\\([\"/])", "$1"));

            String url = ((LinkedHashMap) yaml.getList("data").get(0)).get("url").toString();
            selfDestruct(message.getChannel().sendMessage(url).complete(), 10000);

        } catch (Exception e) {
            e.printStackTrace();
            ZLogger.warn("Could not get data!");
        }
    }

}
