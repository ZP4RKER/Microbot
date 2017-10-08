package me.zp4rker.discord.microbot.cmd;

import me.zp4rker.discord.core.command.ICommand;
import me.zp4rker.discord.core.command.RegisterCommand;
import me.zp4rker.discord.core.exception.ExceptionHandler;
import me.zp4rker.discord.core.logger.ZLogger;
import me.zp4rker.discord.microbot.util.JSONUtil;
import me.zp4rker.discord.microbot.util.MessageUtil;
import net.dv8tion.jda.core.entities.Message;
import org.json.JSONObject;

/**
 * @author ZP4RKER
 */
public class DogCommand implements ICommand {

    @RegisterCommand(aliases = "dog",
                    description = "Sends a random picture of a dog.",
                    usage = "{prefix}dog")
    public void onCommand(Message message) {
        MessageUtil.bypassDeleteLogs(message);

        try{
            JSONObject data = JSONUtil.readUrl("https://api.thedogapi.co.uk/v2/dog.php");

            String url = data.getJSONArray("data").getJSONObject(0).getString("url");
            MessageUtil.selfDestruct(message.getChannel().sendMessage(url).complete(), 20000);

        } catch (Exception e) {
            ExceptionHandler.handleException(e);
            ZLogger.warn("Could not get data!");
        }
    }

}
