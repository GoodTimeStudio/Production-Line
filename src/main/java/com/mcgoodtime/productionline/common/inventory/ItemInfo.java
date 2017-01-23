/*
 * This file is part of GoodTime-Industrial, licensed under MIT License (MIT).
 *
 * Copyright (c) 2015 GoodTime Studio <https://github.com/GoodTimeStudio>
 * Copyright (c) contributors
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.mcgoodtime.productionline.common.inventory;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.RegistryDelegate;

import javax.annotation.Nullable;

/**
 * An immutable information of an item type, meta aware.
 */
public final class ItemInfo {

    private static final ItemInfo EMPTY = of(Inventories.AIR, 0);

    private final RegistryDelegate<Item> delegate;
    private final int meta;

    public static ItemInfo of() {
        return EMPTY;
    }

    public static ItemInfo of(@Nullable Item item) {
        if (item == null)
            return EMPTY;
        return new ItemInfo(item.delegate, 0);
    }

    public static ItemInfo of(@Nullable Item item, int meta) {
        if (item == null)
            return EMPTY;
        return new ItemInfo(item.delegate, meta);
    }

    public static ItemInfo of(@Nullable ItemStack stack) {
        if (stack == null)
            return EMPTY;
        return of(stack.getItem(), stack.getMetadata());
    }

    private ItemInfo(RegistryDelegate<Item> item, int meta) {
        this.delegate = item;
        this.meta = meta;
    }

    public Item getItem() {
        return delegate.get();
    }

    public ResourceLocation getIdentifier() {
        return delegate.name();
    }

    public int getMeta() {
        return meta;
    }

    @Override
    public int hashCode() {
        return delegate.hashCode() ^ meta;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof ItemInfo))
            return false;
        ItemInfo other = (ItemInfo) obj;
        return delegate.equals(other.delegate) && meta == other.meta;
    }

    @Override
    public String toString() {
        return delegate.name().toString() + " " + meta;
    }
}
