package com.mrkekman04.bloodybranch.container;

import com.mrkekman04.bloodybranch.container.slot.SlotInput;
import com.mrkekman04.bloodybranch.container.slot.SlotOutput;
import com.mrkekman04.bloodybranch.tile.TileEntityFactory;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.items.wrapper.CombinedInvWrapper;


public class ContainerFactory extends BaseContainer<TileEntityFactory> {

    private final TileEntityFactory te;
    private final EntityPlayer player;

//    CombinedInvWrapper inventory = te.getInventory().getInventory();


    public ContainerFactory(EntityPlayer player, TileEntityFactory te) {

        super(te);
        this.te = te;
        this.player = player;

        int index = 0;

        addSlotToContainer(new SlotInput(te.getInventory(), index++, 71, 36));
        addSlotToContainer(new SlotOutput(te.getInventory(), index, 125, 36));

        addInventoryPlayer(player, 8, 18, 84, 18, 8, 18, 142);
    }





}
