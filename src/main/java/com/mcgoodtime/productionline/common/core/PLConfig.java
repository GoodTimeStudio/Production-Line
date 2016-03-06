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
package com.mcgoodtime.productionline.common.core;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;

/**
 * Config loader in GoodTime-Industrial.
 *
 * @author liach, BestOwl
 */
public class PLConfig extends Configuration {
    public static Logger gtiLogger = LogManager.getLogger(ProductionLine.MOD_NAME);
    public static PLConfig instance;

    public boolean throwableUran238;
    public boolean throwablePackagedSalt;
    public boolean explosiveFurnace;

    private PLConfig(File configFile) {
        super(configFile);
    }

    public static void init(File configFile) {
        instance = new PLConfig(configFile);
        
        if (!configFile.exists()) {
            gtiLogger.log(Level.ERROR, "Cannot create ProductionLine config file");
            gtiLogger.log(Level.INFO, "Skipping config load");
        } else {
            instance.load();

            Property throwableUran238 = instance.get(CATEGORY_GENERAL, "ThrowableUran238", true);
            throwableUran238.comment = "Allow throw uranium 238, was hit after the radiation effect";
            instance.throwableUran238 = throwableUran238.getBoolean();

            Property throwablePackagedSalt = instance.get(CATEGORY_GENERAL, "ThrowablePackagedSalt", true);
            throwablePackagedSalt.comment = "Allow throw uranium 238, was hit after the salty effect";
            instance.throwablePackagedSalt = throwablePackagedSalt.getBoolean();

            instance.explosiveFurnace = instance.get(CATEGORY_GENERAL, "ExplosiveFurnace", true).getBoolean();

            instance.save();
        }
        gtiLogger.log(Level.INFO, "ProductionLine config loaded");
    }
}
