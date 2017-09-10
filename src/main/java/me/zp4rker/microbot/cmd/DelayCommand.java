package me.zp4rker.microbot.cmd;

import me.zp4rker.core.command.ICommand;
import me.zp4rker.core.command.RegisterCommand;
import net.dv8tion.jda.core.entities.Message;

import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;

import static me.zp4rker.microbot.util.MessageUtil.*;

/**
 * @author ZP4RKER
 */
public class DelayCommand implements ICommand {

    @RegisterCommand(aliases = "delay",
            description = "Sends a delayed message.",
            usage = "{prefix}delay <DELAY> <MESSAGE>")
    public void onCommand(Message message, String[] args) {
        bypassDeleteLogs(message);
        try {
            if (args.length < 2) {
                selfDestruct(sendError("Invalid arguments!", "You did not provide a message!\n\nUsage: `.delay <DELAY>" +
                        " <MESSAGE>`", message), 6000);
                return;
            }

            long delay = Long.parseLong(args[0]);

            String msg = String.join(" ", Arrays.copyOfRange(args, 1, args.length));

            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    message.getChannel().sendMessage(msg).complete();
                }
            }, delay);
        } catch (Exception e) {
            if (e instanceof NumberFormatException) {
                selfDestruct(sendError("Invalid arguments!", args[0] + " is not a valid number!", message), 6000);
            }
        }
    }

}
