package co.zpdev.bots.microbot.cmd;

import co.zpdev.bots.core.command.Command;
import co.zpdev.bots.microbot.util.ColorUtil;
import co.zpdev.bots.microbot.util.JSONUtil;
import co.zpdev.bots.microbot.util.MessageUtil;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Message;
import org.json.JSONObject;

import java.text.DecimalFormat;

/**
 * @author ZP4RKER
 */
public class CurrencyCommand {

    @Command(aliases = "currency")
    public void onCommand(Message message, String[] args) {
        if (args.length != 3) {
            MessageUtil.selfDestruct(MessageUtil.sendError("Invalid arguments!", "Wrong arguments!\n\nUsage: `.currency <AMOUNT> " +
                    "<CURRENCY-FROM> <CURRENCY-TO>`", message), 6000);
            return;
        }

        try {
            double amount = Double.parseDouble(args[0]);

            String from = args[1].toUpperCase();
            String to = args[2].toUpperCase();

            JSONObject data = JSONUtil.readUrl("http://api.fixer.io/latest?symbols=" + from + "," + to);

            JSONObject rates = data.getJSONObject("rates");
            double fromRate = from.equals("EUR") ? 1 : rates.getDouble(from);
            double toRate = to.equals("EUR") ? 1 : rates.getDouble(to);

            if (fromRate == 0 || toRate == 0) {
                String reply = fromRate == 0 ? (toRate == 0 ? "Both currencies are invalid!" : from + " is an" +
                        " invalid currency!") : to + " is an invalid currency!";
                MessageUtil.selfDestruct(MessageUtil.sendError("Invalid arguments!", reply, message), 6000);
                return;
            }

            double conv = toRate / fromRate;

            DecimalFormat df = new DecimalFormat("#.##");
            double convAmount = Double.parseDouble(df.format(conv * amount));

            message.getChannel().sendMessage(new EmbedBuilder().setTitle(amount + " " + from + " = " + convAmount + " " + to)
                    .setColor(ColorUtil.randomColour()).build()).complete();
        } catch (Exception e) {
            if (e instanceof NumberFormatException)
                MessageUtil.selfDestruct(MessageUtil.sendError("Invalid arguments!", args[0] + " is not a number!", message), 6000);
            else e.printStackTrace();
        }
    }

}
