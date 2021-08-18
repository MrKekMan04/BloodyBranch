package com.mrkekman04.bloodybranch.items;

import com.mrkekman04.bloodybranch.init.InitItems;
import com.mrkekman04.bloodybranch.main.Main;
import com.mrkekman04.bloodybranch.utils.interfaces.IHasModel;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class ItemContractAluminium extends Item implements IHasModel {

    public ItemContractAluminium (String name) {

        setRegistryName(name);
        setUnlocalizedName(name);
        setContainerItem(this);
        setCreativeTab(Main.BLOODY_BRANCH);
        setMaxDamage(63);
        setMaxStackSize(1);
        setNoRepair();

        InitItems.ITEMS.add(this);

    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add(I18n.format("desc.aluminium_contract1"));
        tooltip.add(I18n.format("desc.aluminium_contract2"));
    }

    @Override
    public void registerModels() {
        Main.proxy.registerItemRenderer(this, 0, "inventory");
    }

}
