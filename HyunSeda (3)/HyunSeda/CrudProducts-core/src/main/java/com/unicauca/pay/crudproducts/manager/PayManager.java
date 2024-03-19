/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.unicauca.pay.crudproducts.manager;

import com.unicauca.pay.crudproducts.common.interfaces.iPayPlugin;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author JUAN DAVID
 */
public class PayManager {
    private static final String FILE_NAME = "plugin.properties";
    private static PayManager instance;

    private Properties pluginProperties;

    private PayManager() {
        pluginProperties = new Properties();
    }

    public static PayManager getInstance() {
        return instance;
    }

    public static void init(String basePath) throws Exception {

        instance = new PayManager();
        instance.loadProperties(basePath);
        if (instance.pluginProperties.isEmpty()) {
            throw new Exception("Could not initialize plugins");
        }

    }

    public iPayPlugin getDeliveryPlugin(String countryCode) {

        //Verificar si existe una entrada en el archivo para el país indicado.
        String propertyName = "pay." + countryCode.toLowerCase();
        if (!pluginProperties.containsKey(propertyName)) {
            return null;
        }

        iPayPlugin plugin = null;
        //Obtener el nombre de la clase del plugin.
        String pluginClassName = pluginProperties.getProperty(propertyName);

        try {

            //Obtener una referencia al tipo de la clase del plugin.
            Class<?> pluginClass = Class.forName(pluginClassName);//En esta línea está el error
            if (pluginClass != null) {

                //Crear un nuevo objeto del plugin.
                Object pluginObject = pluginClass.getDeclaredConstructor().newInstance();

                if (pluginObject instanceof iPayPlugin) {
                    plugin = (iPayPlugin) pluginObject;
                }
            }

        } catch (ClassNotFoundException | IllegalAccessException | IllegalArgumentException | InstantiationException | NoSuchMethodException | SecurityException | InvocationTargetException ex) {
            Logger.getLogger("DeliveryPluginManager").log(Level.SEVERE, "Error al ejecutar la aplicación", ex);
        }

        return plugin;

    }

    private void loadProperties(String basePath) {

        String filePath = basePath;
        filePath += FILE_NAME;
        try (FileInputStream stream = new FileInputStream(filePath)) {
            pluginProperties.load(stream);

        } catch (IOException ex) {
            Logger.getLogger("DeliveryPluginManager").log(Level.SEVERE, "Error al ejecutar la aplicación", ex);
        }

    }
}
