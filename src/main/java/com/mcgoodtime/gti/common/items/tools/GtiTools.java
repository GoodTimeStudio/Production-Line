/*
 * This file is part of GoodTime-Industrial, licensed under MIT License (MIT).
 *
 * Copyright (c) 2015 Minecraft-GoodTime <http://github.com/Minecraft-GoodTime>
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
package com.mcgoodtime.gti.common.items.tools;

import com.mcgoodtime.gti.common.items.ItemGti;
import net.minecraft.item.*;

/*
 * Created by suhao on 2015.7.19.
 */
public class GtiTools extends ItemGti {

    public static Item registerPickaxe(ToolMaterial toolMaterial, String name) {
        return new Pickaxe(toolMaterial, name);
    }

    public static Item registerAxe(ToolMaterial toolMaterial, String name) {
        return new Axe(toolMaterial, name);
    }

    public static Item registerSpade(ToolMaterial toolMaterial, String name) {
        return new Spade(toolMaterial, name);
    }

    public static Item registerSword(ToolMaterial toolMaterial, String name) {
        return new Sword(toolMaterial, name);
    }

    private GtiTools(String name) {
        super(name);
    }

    private static class Pickaxe extends ItemPickaxe {
        private Pickaxe(ToolMaterial toolMaterial, String name) {
            super(toolMaterial);
            new GtiTools(name);
        }
    }

    private static class Axe extends ItemAxe {
        private Axe(ToolMaterial toolMaterial, String name) {
            super(toolMaterial);
            new GtiTools(name);
        }
    }

    private static class Spade extends ItemSpade {
        private Spade(ToolMaterial toolMaterial, String name) {
            super(toolMaterial);
            new GtiTools(name);
        }
    }

    private static class Sword extends ItemSword {
        private Sword(ToolMaterial toolMaterial, String name) {
            super(toolMaterial);
            new GtiTools(name);
        }
    }
}



