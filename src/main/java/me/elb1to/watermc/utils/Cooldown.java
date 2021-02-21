package me.elb1to.watermc.utils;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import net.md_5.bungee.api.connection.ProxiedPlayer;

/**
 * Author: comphenix & aadnk
 * Gist link: https://gist.github.com/aadnk/5499140
 */
public class Cooldown {

	private static Table<String, String, Long> cooldowns = HashBasedTable.create();

	public static long getCooldown(ProxiedPlayer player, String key) {
		return calculateRemainder(cooldowns.get(player.getName(), key));
	}

	public static long setCooldown(ProxiedPlayer player, String key, long delay) {
		return calculateRemainder(cooldowns.put(player.getName(), key, System.currentTimeMillis() + delay));
	}

	// Determine if a given cooldown has expired. If it has, refresh the cooldown. If not, do nothing.
	public static boolean checkCooldown(ProxiedPlayer player, String key, long delay) {
		if (getCooldown(player, key) <= 0) {
			setCooldown(player, key, delay);
			return true;
		}

		return false;
	}

	public static void removeCooldowns(ProxiedPlayer player) {
		cooldowns.row(player.getName()).clear();
	}

	private static long calculateRemainder(Long expireTime) {
		return expireTime != null ? expireTime - System.currentTimeMillis() : Long.MIN_VALUE;
	}
}
