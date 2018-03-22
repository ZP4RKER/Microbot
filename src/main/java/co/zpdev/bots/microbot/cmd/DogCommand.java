package co.zpdev.bots.microbot.cmd;

import co.zpdev.bots.core.command.Command;
import co.zpdev.bots.microbot.util.JSONUtil;
import co.zpdev.bots.microbot.util.MessageUtil;
import net.dv8tion.jda.core.entities.Message;
import org.json.JSONObject;

/**
 * @author ZP4RKER
 */
public class DogCommand {

    @Command(aliases = "dog")
    public void onCommand(Message message) {
        MessageUtil.bypassDeleteLogs(message);

        try{
            JSONObject data = JSONUtil.readUrl("https://api.thedogapi.co.uk/v2/dog.php");

            String url = data.getJSONArray("data").getJSONObject(0).getString("url");
            MessageUtil.selfDestruct(message.getChannel().sendMessage(url).complete(), 20000);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
