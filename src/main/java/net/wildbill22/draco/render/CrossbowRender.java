package net.wildbill22.draco.render;

import org.lwjgl.opengl.GL11;

//import sanandreasp.core.manpack.helpers.client.ItemRenderHelper;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;

import net.minecraftforge.client.IItemRenderer;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;


@SideOnly(Side.CLIENT)
public class CrossbowRender
    implements IItemRenderer


{
    @Override
    public boolean handleRenderType(ItemStack item, ItemRenderType type) {
        return type == ItemRenderType.EQUIPPED || type == ItemRenderType.EQUIPPED_FIRST_PERSON;
    }

    @Override
    public void renderItem(ItemRenderType type, ItemStack item, Object... data) {
        EntityLivingBase entity = (EntityLivingBase) data[1];

        GL11.glPopMatrix();                                         // prevents Forge from pre-translating the item

        if( type == ItemRenderType.EQUIPPED_FIRST_PERSON ) {
            ItemRenderHelper.renderItem(entity, item, 0);
        } else {
            GL11.glPushMatrix();

            float scale = 3F - (1F / 3F);                           // contra-translate the item from it's standard translation
                                                                    // also apply some more scale or else the bow is tiny
            GL11.glRotatef(80.0F, 1.0F, 0.0F, 0.0F);
            GL11.glRotatef(110.0F, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(0.0F, 0.0F, 0.0F, 1.0F);
            GL11.glScalef(scale, scale, scale);
            GL11.glTranslatef(-0.0F, -0.0F, -0.0F);
            
            scale = 0.625F; // render the item as 'real' bow
            
            
            GL11.glTranslatef(-0.1F, 0.7F, 0.0F);
            GL11.glRotatef(0.0F, 0.0F, 0.0F, 0.0F);
            GL11.glScalef(scale, -scale, scale);
            GL11.glRotatef(0.0F, 0.0F, 0.0F, 0.0F);
            GL11.glRotatef(0.0F, 0.0F, 0.0F, 0.0F);


            ItemRenderHelper.renderItem(entity, item, 0);

            GL11.glPopMatrix();
        }
        GL11.glPushMatrix();
    }

    @Override
    public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {
        return false;
    }
}