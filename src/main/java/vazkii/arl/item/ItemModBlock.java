/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Psi Mod. Get the Source Code in github:
 * https://github.com/Vazkii/Psi
 *
 * Psi is Open Source and distributed under the
 * Psi License: http://psi.vazkii.us/license.php
 *
 * File Created @ [09/01/2016, 22:42:36 (GMT)]
 */
package vazkii.arl.item;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import vazkii.arl.interf.IModBlock;
import vazkii.arl.interf.IVariantHolder;

import javax.annotation.Nonnull;

public class ItemModBlock extends ItemBlock implements IVariantHolder {

	private IModBlock modBlock;

	public ItemModBlock(Block block, ResourceLocation res) {
		super(block);
		modBlock = (IModBlock) block;

		if(getVariants().length > 1)
			setHasSubtypes(true);
		setRegistryName(res);
	}

	@Override
	public int getMetadata(int damage) {
		return damage;
	}

	@Nonnull
	@Override
	public ItemBlock setTranslationKey(@Nonnull String par1Str) {
		return (ItemBlock) super.setTranslationKey(par1Str);
	}

	@Nonnull
	@Override
	public String getTranslationKey(ItemStack par1ItemStack) {
		int dmg = par1ItemStack.getItemDamage();
		String[] variants = getVariants();

		String name;
		if(dmg >= variants.length)
			name = modBlock.getBareName();
		else name = variants[dmg];

		return "tile." + getPrefix() + name;
	}

	@Override
	public void getSubItems(@Nonnull CreativeTabs tab, @Nonnull NonNullList<ItemStack> subItems) {
		String[] variants = getVariants();
		if(isInCreativeTab(tab))
			for(int i = 0; i < variants.length; i++)
				if(modBlock.shouldDisplayVariant(i))
					subItems.add(new ItemStack(this, 1, i));
	}

	@Override
	public String[] getVariants() {
		return modBlock.getVariants();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public ItemMeshDefinition getCustomMeshDefinition() {
		return modBlock.getCustomMeshDefinition();
	}

	@Nonnull
	@Override
	public EnumRarity getRarity(ItemStack stack) {
		return modBlock.getBlockRarity(stack);
	}
	
	@Override
	public String getModNamespace() {
		return modBlock.getModNamespace();
	}
	
}
