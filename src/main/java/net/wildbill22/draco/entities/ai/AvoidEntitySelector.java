package net.wildbill22.draco.entities.ai;

import net.minecraft.command.IEntitySelector;
import net.minecraft.entity.Entity;

public class AvoidEntitySelector implements IEntitySelector
{
    final EntityAIAvoidDragon entityAvoiderAI;

    AvoidEntitySelector(EntityAIAvoidDragon par1EntityAIAvoidEntity) {
        this.entityAvoiderAI = par1EntityAIAvoidEntity;
    }

    /**
     * Return whether the specified entity is applicable to this filter.
     */
    public boolean isEntityApplicable(Entity par1Entity) {
        return par1Entity.isEntityAlive() && EntityAIAvoidDragon.func_98217_a(this.entityAvoiderAI).getEntitySenses().canSee(par1Entity);
    }

}
