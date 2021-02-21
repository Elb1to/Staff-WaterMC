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
 * Date: 2/20/2021 @ 1:20 PM
 */
public class ReportCommand extends Command {

	public ReportCommand() {
		super("report", "proxy.watermc.report", "reportar");
	}

	@Override
	public void execute(CommandSender sender, String[] args) {
		if (!(sender instanceof ProxiedPlayer)) {
			sender.sendMessage((CC.translate("&cComediante.")));
			return;
		}

		ProxiedPlayer player = (ProxiedPlayer) sender;
		if (args.length == 0) {
			player.sendMessage((CC.translate("&cUsa: /report <jugador> <razon>")));
			return;
		}

		ProxiedPlayer target = Staff.getInstance().getProxy().getPlayer(args[0]);
		if (target == null) {
			player.sendMessage((CC.translate("&cEl jugador que intentas reportar no está conectado.")));
			return;
		}
		if (target == player) {
			player.sendMessage((CC.translate("&c¡No te puedes reportar a ti mismo!")));
			return;
		}

		if (args.length == 1) {
			player.sendMessage((CC.translate("&cNecesitas especificar la razón del reporte.")));
			return;
		}

		if (!Cooldown.checkCooldown(player, "reportCooldown", 60000L)) {
			player.sendMessage((CC.translate("&cNo puedes utilizar este comando actualmente, inténtalo de nuevo en un rato.")));
			return;
		}

		String serverName = Staff.getInstance().getProxy().getPlayer(player.getUniqueId()).getServer().getInfo().getName();
		TextComponent message = new TextComponent(CC.translate("&b[S] &3[" + serverName + "] &a" + player.getName() + " &7has reported &c" + target.getName() + " &7for &c" + CC.message(args, 1) + ". &8(&aClick to Go&8)"));
		message.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new Text(CC.translate("&8(&aClick to Go&8)"))));
		message.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/server " + serverName));

		Staff.getInstance().getProxy().getPlayers()
				.stream()
				.filter(staff -> !staff.hasPermission("proxy.watermc.staff.report"))
				.collect(Collectors.toList())
				.forEach(staff -> staff.sendMessage(message));

		player.sendMessage((CC.translate("&a¡Tu reporte ha sido enviado satisfactoriamente!")));
		Cooldown.setCooldown(player, "reportCooldown", 60000L);
	}
}
