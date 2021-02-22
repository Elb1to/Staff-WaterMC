package me.elb1to.watermc.commands.admin;

import me.elb1to.watermc.Staff;
import me.elb1to.watermc.utils.CC;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

/**
 * Created by Elb1to
 * Project: Staff
 * Date: 2/20/2021 @ 1:20 PM
 */
public class UserInfoCommand extends Command {

	public UserInfoCommand() {
		super("userinfo", "proxy.watermc.admin");
	}

	@Override
	public void execute(CommandSender sender, String[] args) {
		if (args.length == 0) {
			sender.sendMessage((CC.translate("&cUsage: /userinfo <player>")));
			return;
		}

		ProxiedPlayer target = Staff.getInstance().getProxy().getPlayer(args[0]);
		if (target == null) {
			sender.sendMessage((CC.translate("&cThat player is not online.")));
			return;
		}

		sender.sendMessage(CC.CHAT_BAR);
		sender.sendMessage(CC.translate("&b&lUser Information&f:"));
		sender.sendMessage(CC.translate("&3 ▶ &fName: &b" + target.getName()));
		sender.sendMessage(CC.translate("&3 ▶ &fUUID: &b" + target.getUniqueId()));
		sender.sendMessage(CC.translate("&3 ▶ &fRank: &b" + CC.getRank(target)));
		//sender.sendMessage(CC.translate("&3 ▶ &fStaff Chat: " + (Staff.getInstance().getStaffChatToggled().contains(target.getUniqueId()) ? "&aON" : "&cOFF")));
		sender.sendMessage(CC.translate("&3 ▶ &fCurrent Server: &b" + target.getServer().getInfo().getName()));
		sender.sendMessage(CC.CHAT_BAR);
	}
}
