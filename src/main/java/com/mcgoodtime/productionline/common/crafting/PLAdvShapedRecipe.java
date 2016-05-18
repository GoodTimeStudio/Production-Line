package com.mcgoodtime.productionline.common.crafting;

import com.mcgoodtime.productionline.common.blocks.BlockEUStorage;
import cpw.mods.fml.common.registry.GameRegistry;
import ic2.api.item.ElectricItem;
import ic2.api.item.IElectricItem;
import ic2.core.util.StackUtil;
import net.minecraft.block.Block;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.ShapedRecipes;
import net.minecraft.nbt.NBTTagCompound;

import java.util.HashMap;

/*
 * Created by BestOwl on 2016-05-16.
 */
public class PLAdvShapedRecipe extends ShapedRecipes {

    public PLAdvShapedRecipe(int width, int height, ItemStack[] itemStacks, ItemStack result) {
        super(width, height, itemStacks, result);
    }

    public static void addShapedRecipe(ItemStack result, Object... recipes) {
        int idx = 0;
        String shape = "";
        int width = 0;
        int height = 0;

        if (recipes[idx] instanceof String[]) {
            String[] parts = ((String[])recipes[idx++]);

            for (String s : parts) {
                width = s.length();
                shape += s;
            }

            height = parts.length;
        }
        else {
            while (recipes[idx] instanceof String) {
                String s = (String)recipes[idx++];
                shape += s;
                width = s.length();
                height++;
            }
        }

        if (width * height != shape.length()) {
            String ret = "Invalid shaped ore recipe: ";
            for (Object tmp :  recipes) {
                ret += tmp + ", ";
            }
            ret += recipes;
            throw new RuntimeException(ret);
        }
        //======================================================

        HashMap<Character, Object> itemMap;
        for (itemMap = new HashMap<Character, Object>(); idx < recipes.length; idx += 2) {
            Character character = (Character)recipes[idx];
            ItemStack input = null;

            if (recipes[idx + 1] instanceof Item) {
                input = new ItemStack((Item)recipes[idx + 1]);
            }
            else if (recipes[idx + 1] instanceof Block) {
                input = new ItemStack((Block) recipes[idx + 1], 1, 32767);
            }
            else if (recipes[idx + 1] instanceof ItemStack) {
                input = (ItemStack)recipes[idx + 1];
            }

            itemMap.put(character, input);
        }

        ItemStack[] itemList = new ItemStack[width * height];
        for (int i1 = 0; i1 < width * height; ++i1) {
            char c0 = shape.charAt(i1);

            if (itemMap.containsKey(c0)) {
                itemList[i1] = ((ItemStack) itemMap.get(c0)).copy();
            } else {
                itemList[i1] = null;
            }
        }

        PLAdvShapedRecipe recipe = new PLAdvShapedRecipe(width, height, itemList, result);
        GameRegistry.addRecipe(recipe);
    }

    /**
     * Checks if the region of a crafting inventory is match for the recipe.
     */
    @SuppressWarnings("unchecked")
    @Override
    public boolean checkMatch(InventoryCrafting crafting, int startX, int startY, boolean mirror) {
        for (int k = 0; k < 3; ++k) {
            for (int l = 0; l < 3; ++l) {
                int x = k - startX;
                int y = l - startY;
                ItemStack target = null;

                if (x >= 0 && y >= 0 && x < this.recipeWidth && y < this.recipeHeight) {
                    if (mirror) {
                        target = this.recipeItems[this.recipeWidth - x - 1 + y * this.recipeWidth];
                    }
                    else {
                        target = this.recipeItems[x + y * this.recipeWidth];
                    }
                }

                ItemStack input = crafting.getStackInRowAndColumn(k, l);
                if (input == null && target != null || input != null && target == null) {
                    return false;
                }

                if (input != null) {
                    if (target.getItem() != input.getItem()) {
                        return false;
                    }

                    if (!(target.getItem() instanceof IElectricItem) && (input.getItem() instanceof IElectricItem)) {
                        if (target.getItemDamage() != 32767 && target.getItemDamage() != input.getItemDamage()) {
                            return false;
                        }
                    }
                }
            }
        }

        return true;
    }

    /**
     * Returns an Item that is the result of this recipe
     */
    @Override
    public ItemStack getCraftingResult(InventoryCrafting crafting) {
        if (this.getRecipeOutput().getItem() instanceof IElectricItem) {
            return this.chargeItemStackOutput(crafting, ChargeMode.ElectricItem);
        }
        else if (Block.getBlockFromItem(this.getRecipeOutput().getItem()) instanceof BlockEUStorage) {
            return this.chargeItemStackOutput(crafting, ChargeMode.BlockEUStorage);
        }
        return super.getCraftingResult(crafting);
    }

    private ItemStack chargeItemStackOutput(InventoryCrafting crafting, ChargeMode mode) {
        ItemStack output = this.getRecipeOutput().copy();
        double energy = 0;
        int tier = 0;

        for (int i = 0; i < crafting.getSizeInventory(); i++) {
            ItemStack inStack = crafting.getStackInSlot(i);
            Item inItem = inStack.getItem();

            if (inItem instanceof IElectricItem) {
                if (((IElectricItem) inItem).canProvideEnergy(inStack)) {
                    energy += ElectricItem.manager.getCharge(inStack);
                    tier = ((IElectricItem) inItem).getTier(inStack);
                }
            }
        }

        if (mode == ChargeMode.ElectricItem) {

            ElectricItem.manager.charge(output, energy, tier, false, false);
        }
        else if (mode == ChargeMode.BlockEUStorage) {
            NBTTagCompound nbt = StackUtil.getOrCreateNbtData(output);
            nbt.setInteger("energy", (int) energy);
        }

        return output;
    }

    private enum ChargeMode {
        ElectricItem,
        BlockEUStorage
    }
}
