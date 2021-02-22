package me.elb1to.watermc.commands.staff;

import me.elb1to.watermc.Staff;
import me.elb1to.watermc.utils.CC;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

/**
 * Created by Elb1to
 * Project: Staff
 * Date: 2/20/2021 @ 1:09 PM
 */
public class ToggleStaffChatCommand extends Command {

	public ToggleStaffChatCommand() {
		super("togglestaffchat", "proxy.watermc.staffchat", "tsc", "togglesc");
	}

	@Override
	public void execute(CommandSender sender, String[] args) {
		if (!(sender instanceof ProxiedPlayer)) {
			sender.sendMessage((CC.translate("&cComediante.")));
			return;
		}

		//ProxiedPlayer player = (ProxiedPlayer) sender;
		//if (!Staff.getInstance().getStaffChatToggled().contains(player.getUniqueId())) {
		//	Staff.getInstance().getStaffChatToggled().add(player.getUniqueId());
		//	player.sendMessage(CC.translate("&8[&2&l!&8] &aYou have toggled staff chat on."));
		//	player.sendMessage(CC.translate("&8[&4&l!&8] &cStaffChat will be toggled off when you leave the network."));
		//} else {
		//	Staff.getInstance().getStaffChatToggled().remove(player.getUniqueId());
		//	player.sendMessage(CC.translate("&8[&4&l!&8] &cYou have toggled staff chat off."));
		//}
	}
}
