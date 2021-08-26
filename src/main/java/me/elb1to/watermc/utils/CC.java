package me.elb1to.watermc.utils;

import me.elb1to.watermc.user.StaffRank;
import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.model.user.User;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.connection.ProxiedPlayer;

import java.util.Objects;

/**
 * Created by Elb1to
 * Project: Staff
 * Date: 2/20/2021 @ 1:29 PM
 */
public class CC {

	public final static String CHAT_BAR = ChatColor.GRAY.toString() + ChatColor.STRIKETHROUGH.toString() + "------------------------------------------------";

	public static String translate(String s) {
		return ChatColor.translateAlternateColorCodes('&', s);
	}

	public static String message(String[] args, int x) {
		StringBuilder builder = new StringBuilder();

		for (int i = x; i < args.length; ++i) {
			builder.append(args[i]);
			builder.append(" ");
		}

		return builder.toString().trim();
	}

	public static String getRank(ProxiedPlayer player) {
		User user = LuckPermsProvider.get().getUserManager().getUser(player.getUniqueId());
		if (user == null) {
			throw new IllegalArgumentException("LuckPerms user for " + player.getName() + " could not be found");
		} else {
			return user.getPrimaryGroup();
		}
	}

	public static String getNameColor(ProxiedPlayer player) {
		String color;
		String group = Objects.requireNonNull(LuckPermsProvider.get().getUserManager().getUser(player.getUniqueId())).getPrimaryGroup();

		switch (group.toLowerCase()) {
			case "owner":
				color = StaffRank.OWNER.getNametag();
				break;
			case "co-owner":
				color = StaffRank.CO_OWNER.getNametag();
				break;
			case "developer":
				color = StaffRank.DEVELOPER.getNametag();
				break;
			case "platadmin":
				color = StaffRank.PLAT_ADMIN.getNametag();
				break;
			case "sradmin":
				color = StaffRank.SR_ADMIN.getNametag();
				break;
			case "admin":
				color = StaffRank.ADMIN.getNametag();
				break;
			case "jradmin":
				color = StaffRank.JR_ADMIN.getNametag();
				break;
			case "srmod":
				color = StaffRank.SR_MOD.getNametag();
				break;
			case "mod+":
				color = StaffRank.MOD_PLUS.getNametag();
				break;
			case "mod":
				color = StaffRank.MOD.getNametag();
				break;
			case "trialmod":
				color = StaffRank.TRIAL_MOD.getNametag();
				break;
			default:
				color = "&7";
		}

		return color;
	}
}
