package me.elb1to.watermc.user;

import lombok.Getter;

/**
 * Created by Elb1to
 * Project: Staff [Bungee]
 * Date: 2/20/2021 @ 4:08 PM
 */
@Getter
public enum StaffRank {

	OWNER("&4❃&l ", "owner"),
	CO_OWNER("&4✦&l ", "co-owner"),
	DEVELOPER("&3❈&b&o ", "developer"),
	PLAT_ADMIN("&c&o", "platadmin"),
	SR_ADMIN("&c", "sradmin"),
	ADMIN("&c", "admin"),
	JR_ADMIN("&a", "jradmin"),
	SR_MOD("&3&o", "srmod"),
	MOD_PLUS("&5", "mod+"),
	MOD("&5", "mod"),
	TRIAL_MOD("&3", "trialmod"),
	HELPER("&b", "helper");

	private final String nametag;
	private final String name;

	StaffRank(String nametag, String name) {
		this.nametag = nametag;
		this.name = name;
	}
}
