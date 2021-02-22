package me.elb1to.watermc.commands.staff;

import me.elb1to.watermc.Staff;
import me.elb1to.watermc.utils.CC;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Elb1to
 * Project: Staff
 * Date: 2/20/2021 @ 8:56 PM
 */
public class StaffListCommand extends Command {

	public StaffListCommand() {
		super("stafflist", "proxy.watermc.stafflist", "staffs");
	}

	@Override
	public void execute(CommandSender sender, String[] args) {
		List<String> players = new ArrayList<>();
		Staff.getInstance().getProxy().getPlayers()
				.stream()
				.filter(ProxiedPlayer::isConnected).filter(staffM -> staffM.hasPermission("proxy.watermc.staff"))
				.forEach(player -> players.add(CC.translate(CC.getNameColor(player) + player.getName()))
		);

		sender.sendMessage(CC.CHAT_BAR);
		sender.sendMessage(CC.translate("&3&lWaterMC &fNetwork &8- &7Staff Online: &f" + players.size()));
		sender.sendMessage(CC.translate(""));
		sender.sendMessage(CC.translate("Members: " + String.join("&f, ", players)));
		sender.sendMessage(CC.CHAT_BAR);
	}
}
