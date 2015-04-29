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
package com.mcgoodtime.gti.common.core.config;

import com.mcgoodtime.gti.common.core.Gti;
import net.minecraftforge.common.config.Configuration;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.util.Properties;

/**
 * Config loader in GoodTime-Industrial.
 *
 * @author liach, SuHao
 */
public class GtiConfig {
    public static Logger gtiLogger = LogManager.getLogger(Gti.MOD_ID);
    public static Configuration gtiConfig;
    public static File configFile;

    private static Properties cfgProp = new Properties();

    public static void init() {
        configFile = new File("config", Gti.MOD_NAME + ".cfg");

        // if configFile doesn't exists
        if (!configFile.exists()) {

        }

        gtiConfig = new Configuration(configFile);
        gtiConfig.load();
        gtiLogger.log(Level.INFO, "Gti logger loaded");
    }

    // load the config file
    public static String getConfig(String configKey) {
        String key = null;
        try {
            FileInputStream in = new FileInputStream(configFile);
            InputStreamReader isr = new InputStreamReader(in, "UTF-8");
            cfgProp.load(isr);
            key = cfgProp.getProperty(configKey);
            isr.close();
            in.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return key;
    }

    // load the config file
    public static Boolean getConfigAsBoolean(String configKey) {
        Boolean key = null;
        try {
            FileInputStream in = new FileInputStream(configFile);
            InputStreamReader isr = new InputStreamReader(in, "UTF-8");
            cfgProp.load(isr);
            String stringKey = cfgProp.getProperty(configKey);

            if (stringKey.equals("true")) key = true;
            else key = false;

            isr.close();
            in.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return key;
    }

    // add to the config file
    public static void setConfig(String configKey, String value) {
        cfgProp.setProperty(configKey, value);
    }

    public static void saveConfig() {
        try {
            OutputStream out = new FileOutputStream(configFile);
            cfgProp.store(out, "# Configuration file\n# GoodTime-Industrial");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
