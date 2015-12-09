package com.mcgoodtime.gti.common.nei;

import codechicken.nei.api.API;
import codechicken.nei.api.IConfigureNEI;
import com.mcgoodtime.gti.common.core.Gti;
import com.mcgoodtime.gti.common.nei.handler.RecipeHandlerCarbonizeFurnace;

/**
 * Created by BestOwl on 2015.11.29.0029.
 *
 * @author BestOwl
 */
public class NEIGtiConfig implements IConfigureNEI {

    @Override
    public void loadConfig() {
        API.registerRecipeHandler(new RecipeHandlerCarbonizeFurnace());
        API.registerUsageHandler(new RecipeHandlerCarbonizeFurnace());
    }

    @Override
    public String getName() {
        return Gti.MOD_NAME;
    }

    @Override
    public String getVersion() {
        return Gti.VERSION;
    }
}
