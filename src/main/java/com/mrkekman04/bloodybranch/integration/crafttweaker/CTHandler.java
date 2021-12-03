package com.mrkekman04.bloodybranch.integration.crafttweaker;

import crafttweaker.CraftTweakerAPI;
import crafttweaker.IAction;

import java.util.LinkedList;
import java.util.List;

public class CTHandler {

    public static final List<IAction> LATE_REMOVALS = new LinkedList<>();
    public static final List<IAction> LATE_ADDITIONS = new LinkedList<>();

    public static void postInit() {
        try {
            LATE_REMOVALS.forEach(CraftTweakerAPI::apply);
            LATE_ADDITIONS.forEach(CraftTweakerAPI::apply);
        } catch (Exception e) {
            e.printStackTrace();
            CraftTweakerAPI.logError("Error while applying actions", e);
        }
        LATE_REMOVALS.clear();
        LATE_ADDITIONS.clear();
    }
}
