package me.elb1to.watermc.commands.staff;

import me.elb1to.watermc.Staff;
import me.elb1to.watermc.utils.CC;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

import java.util.stream.Collectors;

/**
 * Created by Elb1to
 * Project: Staff
 * Date: 2/20/2021 @ 1:09 PM
 */
public class StaffChatCommand extends Command {

	public StaffChatCommand() {
		super("staffchat", "proxy.watermc.staffchat", "sc", "schat");
	}

	@Override
	public void execute(CommandSender sender, String[] args) {
		if (!(sender instanceof ProxiedPlayer)) {
			sender.sendMessage((CC.translate("&cComediante.")));
			return;
		}

		ProxiedPlayer player = (ProxiedPlayer) sender;
		if (args.length == 0) {
			player.sendMessage((CC.translate("&cUsage: /sc <message>")));
			return;
		}

		Staff.getInstance().getProxy().getPlayers()
				.stream()
				.filter(staffP -> staffP.hasPermission("proxy.watermc.staffchat"))
				.collect(Collectors.toList())
				.forEach(staffP -> staffP.sendMessage(CC.translate("&3[S] &b[" + player.getServer().getInfo().getName() + "] " + CC.getNameColor(player) + player + "&8: &7" + CC.message(args, 0)))
		);
	}
}
