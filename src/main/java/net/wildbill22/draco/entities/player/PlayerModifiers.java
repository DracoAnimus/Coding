package net.wildbill22.draco.entities.player;

import java.util.UUID;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.player.EntityPlayer;
import net.wildbill22.draco.lib.BALANCE;

/**
 * Calculates and applies player modifiers depending on the Dragon Level
 * 
 * @author Maxanier
 *
 */
public abstract class PlayerModifiers {
	public static void applyModifiers(int level, EntityPlayer p, boolean isDragon) {
		double m = 0;
		
			// Speed modifier
			IAttributeInstance movement = p.getEntityAttribute(SharedMonsterAttributes.movementSpeed);
			rmMod(movement, speedModifierUUID);
			m = calculateModifierValue(level, BALANCE.DP_MODIFIERS.SPEED_LCAP, BALANCE.DP_MODIFIERS.SPEED_MAX_MOD, 
					BALANCE.DP_MODIFIERS.SPEED_TYPE);
			if (!isDragon) 
				m = 0.0;
			movement.applyModifier(new AttributeModifier(speedModifierUUID, "Dragon Speed Bonus", m, 2).setSaved(false));
	
			// Health modifier
			IAttributeInstance health = p.getEntityAttribute(SharedMonsterAttributes.maxHealth);
			rmMod(health, healthModifierUUID);
			m = calculateModifierValue(level, BALANCE.DP_MODIFIERS.HEALTH_LCAP, BALANCE.DP_MODIFIERS.HEALTH_MAX_MOD, 
					BALANCE.DP_MODIFIERS.HEALTH_TYPE);
			if (!isDragon) 
				m = 0.0;
			health.applyModifier(new AttributeModifier(healthModifierUUID, "Dragon Health Bonus", m, 2).setSaved(false));
	
			// Strength modifier
			IAttributeInstance damage = p.getEntityAttribute(SharedMonsterAttributes.attackDamage);
			rmMod(damage, damageModifierUUID);
			m = calculateModifierValue(level, BALANCE.DP_MODIFIERS.STRENGTH_LCAP, BALANCE.DP_MODIFIERS.STRENGTH_MAX_MOD,
					BALANCE.DP_MODIFIERS.STRENGTH_TYPE);
			if (!isDragon) 
				m = 0.0;
			damage.applyModifier(new AttributeModifier(damageModifierUUID, "Dragon Strength Bonus", m, 2).setSaved(false));
	}
	
	/**
	 * Calculates the modifier effect. You can decide how the modifier changes
	 * with higher levels, by using different types. Suggested values are 1/2
	 * for a square root like behavior or 1 for a linear change
	 * 
	 * @param level  Dragon level
	 * @param lcap   Level the modifier does not get any stronger
	 * @param maxMod Maximal modifier effect
	 * @param type   modifier type
	 * @return value between 0 and maxMod
	 */
	private static double calculateModifierValue(int level, int lcap, double maxMod, double type) {
		return Math.pow((level > lcap ? lcap : level), type) / Math.pow(lcap, type) * maxMod;
	}
	/**
	 * Removes existing modifiers
	 * 
	 * @param att  Attribute
	 * @param uuid UUID of modifier to remove
	 */
	private static void rmMod(IAttributeInstance att, UUID uuid) {
		AttributeModifier m = att.getModifier(uuid);
		if (m != null) {
			att.removeModifier(m);
		}
	}
	public static final String TAG = "PlayerModifier";
	public static final UUID speedModifierUUID = UUID.fromString("0FCBF922-DBEC-492A-82F5-99F73AFF5065");
	public static final UUID healthModifierUUID = UUID.fromString("56C17EFE-E3EC-4E27-A12F-99D2FE927B70");
	public static final UUID damageModifierUUID = UUID.fromString("7600D8C4-3517-40BE-8CB1-359D46705A0F");
}
