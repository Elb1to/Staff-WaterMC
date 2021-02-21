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
 * Date: 2/20/2021 @ 2:15 PM
 */
public class CallAdminCommand extends Command {

	public CallAdminCommand() {
		super("calladmin", "proxy.watermc.calladmin", "ca");
	}

	@Override
	public void execute(CommandSender sender, String[] args) {
		if (!(sender instanceof ProxiedPlayer)) {
			sender.sendMessage(CC.translate("&cComediante."));
			return;
		}

		ProxiedPlayer player = (ProxiedPlayer) sender;
		if (!Cooldown.checkCooldown(player, "reportCooldown", 90000L)) {
			player.sendMessage(CC.translate("&cSolicitaste ayuda de un administrador recientemente, espera unos minutos..."));
			return;
		}

		Cooldown.setCooldown(player, "reportCooldown", 90000L);
		String serverName = Staff.getInstance().getProxy().getPlayer(player.getUniqueId()).getServer().getInfo().getName();

		TextComponent message = new TextComponent(CC.translate("&8[&b" + serverName + "&8] &a" + player.getName() + " &cha solicitado ayuda de un admin. &7(&aClic aquí&7)"));
		message.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new Text(CC.translate("&8(&aClic para ir&8)"))));
		message.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/server " + serverName));

		Staff.getInstance().getProxy().getPlayers()
				.stream()
				.filter(adminPlayer -> adminPlayer.hasPermission("proxy.watermc.admin"))
				.collect(Collectors.toList())
				.forEach(adminPlayer -> adminPlayer.sendMessage(message));

		player.sendMessage(CC.translate("&aHas solicitado ayuda de un administrador exitosamente."));
	}
}
