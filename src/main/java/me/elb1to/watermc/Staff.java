package me.elb1to.watermc;

import lombok.Getter;
import lombok.Setter;
import me.elb1to.watermc.commands.admin.MaintenanceCommand;
import me.elb1to.watermc.commands.admin.UserInfoCommand;
import me.elb1to.watermc.commands.staff.StaffChatCommand;
import me.elb1to.watermc.commands.staff.StaffListCommand;
import me.elb1to.watermc.commands.user.CallAdminCommand;
import me.elb1to.watermc.commands.user.ReportCommand;
import me.elb1to.watermc.commands.user.RequestCommand;
import me.elb1to.watermc.user.StaffListener;
import net.md_5.bungee.api.plugin.Plugin;

@Getter
public class Staff extends Plugin {

	@Getter private static Staff instance;

	private boolean maintenance;

	@Override
	public void onEnable() {
		instance = this;

		// User
		getProxy().getPluginManager().registerCommand(this, new CallAdminCommand());
		getProxy().getPluginManager().registerCommand(this, new RequestCommand());
		getProxy().getPluginManager().registerCommand(this, new ReportCommand());

		// Staff
		getProxy().getPluginManager().registerCommand(this, new StaffChatCommand());
		getProxy().getPluginManager().registerCommand(this, new StaffListCommand());

		// Admin
		getProxy().getPluginManager().registerCommand(this, new UserInfoCommand());
		getProxy().getPluginManager().registerCommand(this, new MaintenanceCommand());

		getProxy().getPluginManager().registerListener(this, new StaffListener());
	}

	@Override
	public void onDisable() {
		getProxy().getPluginManager().unregisterCommands(this);
		getProxy().getPluginManager().unregisterListeners(this);

		instance = null;
	}

	public boolean toggleMaintenance() {
		return maintenance = !maintenance;
	}
}
