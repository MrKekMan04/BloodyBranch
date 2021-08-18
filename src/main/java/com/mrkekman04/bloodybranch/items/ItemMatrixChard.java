package com.mrkekman04.bloodybranch.items;

import com.mrkekman04.bloodybranch.init.InitItems;
import com.mrkekman04.bloodybranch.main.Main;
import com.mrkekman04.bloodybranch.utils.interfaces.IHasModel;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class ItemMatrixChard extends Item implements IHasModel {
    public ItemMatrixChard(String name){
        setUnlocalizedName(name);
        setRegistryName(name);
        setCreativeTab(Main.BLOODY_BRANCH);

        InitItems.ITEMS.add(this);
    }

    @Override
    public void registerModels() {
        Main.proxy.registerItemRenderer(this, 0, "inventory");
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add(I18n.format("desc.matrix_chard1"));
        tooltip.add(I18n.format("desc.matrix_chard2"));
        tooltip.add(I18n.format("desc.matrix_chard3"));
        tooltip.add(I18n.format("desc.matrix_chard4"));
    }
}
