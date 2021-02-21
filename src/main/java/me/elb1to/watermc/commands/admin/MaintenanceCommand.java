package me.elb1to.watermc.commands.admin;

import me.elb1to.watermc.Staff;
import me.elb1to.watermc.utils.CC;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.plugin.Command;

/**
 * Created by Elb1to
 * Project: Staff
 * Date: 2/20/2021 @ 1:09 PM
 */
public class MaintenanceCommand extends Command {

	public MaintenanceCommand() {
		super("maintenance", "proxy.watermc.admin", "mantenimiento");
	}

	@Override
	public void execute(CommandSender sender, String[] args) {
		sender.sendMessage(CC.translate((Staff.getInstance().toggleMaintenance() ? "&cMaintenance toggle on." : "&aMaintenance toggle off.")));
	}
}
