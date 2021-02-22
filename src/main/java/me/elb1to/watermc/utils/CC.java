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

		if (group.equalsIgnoreCase(StaffRank.OWNER.getName())) {
			color = StaffRank.OWNER.getNametag();
		} else if (group.equalsIgnoreCase(StaffRank.CO_OWNER.getName())) {
			color = StaffRank.CO_OWNER.getNametag();
		} else if (group.equalsIgnoreCase(StaffRank.DEVELOPER.getName())) {
			color = StaffRank.DEVELOPER.getNametag();
		} else if (group.equalsIgnoreCase(StaffRank.PLAT_ADMIN.getName())) {
			color = StaffRank.PLAT_ADMIN.getNametag();
		} else if (group.equalsIgnoreCase(StaffRank.SR_ADMIN.getName())) {
			color = StaffRank.SR_ADMIN.getNametag();
		} else if (group.equalsIgnoreCase(StaffRank.ADMIN.getName())) {
			color = StaffRank.ADMIN.getNametag();
		} else if (group.equalsIgnoreCase(StaffRank.JR_ADMIN.getName())) {
			color = StaffRank.JR_ADMIN.getNametag();
		} else if (group.equalsIgnoreCase(StaffRank.SR_MOD.getName())) {
			color = StaffRank.SR_MOD.getNametag();
		} else if (group.equalsIgnoreCase(StaffRank.MOD_PLUS.getName())) {
			color = StaffRank.MOD_PLUS.getNametag();
		} else if (group.equalsIgnoreCase(StaffRank.MOD.getName())) {
			color = StaffRank.MOD.getNametag();
		} else if (group.equalsIgnoreCase(StaffRank.TRIAL_MOD.getName())) {
			color = StaffRank.TRIAL_MOD.getNametag();
		} else {
			color = "&7";
		}

		return color;
	}
}
