package me.elb1to.watermc.commands.user;

import me.elb1to.watermc.Staff;
import me.elb1to.watermc.utils.CC;
import me.elb1to.watermc.utils.Cooldown;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.chat.hover.content.Text;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

import java.util.stream.Collectors;

/**
 * Created by Elb1to
 * Project: Staff
 * Date: 2/20/2021 @ 7:19 PM
 */
public class RequestCommand extends Command {

	public RequestCommand() {
		super("request", "proxy.watermc.request", "helpop");
	}

	@Override
	public void execute(CommandSender sender, String[] args) {
		if (!(sender instanceof ProxiedPlayer)) {
			sender.sendMessage((CC.translate("&cComediante.")));
			return;
		}

		ProxiedPlayer player = (ProxiedPlayer) sender;
		if (args.length == 0) {
			player.sendMessage((CC.translate("&cUsa: /request <mensaje>")));
			return;
		}

		if (!Cooldown.checkCooldown(player, "requestCooldown", 60000L)) {
			player.sendMessage((CC.translate("&cNo puedes utilizar este comando actualmente, inténtalo de nuevo en un rato.")));
			return;
		}

		TextComponent message = new TextComponent(CC.translate(CC.CHAT_BAR + "\n&9[Request] &b[" + player.getServer().getInfo().getName() + "] &a" + player.getName() + " &7has requested assistance\n&2Reason: &a" + CC.message(args, 0) + ".\n" + CC.CHAT_BAR));
		message.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new Text(CC.translate("&8(&aClick to Assist - " + player.getServer().getInfo().getName() + "&8)"))));
		message.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/server " + player.getServer().getInfo().getName()));

		Staff.getInstance().getProxy().getPlayers()
				.stream()
				.filter(staff -> staff.hasPermission("proxy.watermc.staff.request"))
				.collect(Collectors.toList())
				.forEach(staff -> staff.sendMessage(message));

		player.sendMessage((CC.translate("&aTu solicitud ha sido recibida exitosamente!")));
		Cooldown.setCooldown(player, "requestCooldown", 60000L);
	}
}
