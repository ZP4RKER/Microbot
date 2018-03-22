package co.zpdev.bots.microbot.cmd;

import co.zpdev.bots.core.command.Command;
import net.dv8tion.jda.core.entities.Message;

/**
 * @author ZP4RKER
 */
public class TestCommand {

    @Command(aliases = "test")
    public void onCommand(Message message, String[] args) {
        //
    }

}
