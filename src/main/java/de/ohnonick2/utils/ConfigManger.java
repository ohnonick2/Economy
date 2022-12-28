package de.ohnonick2.utils;

import org.bukkit.configuration.file.YamlConfiguration;
import org.checkerframework.checker.units.qual.C;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.UUID;

public class ConfigManger {

    private static String moneypath = "./plugins/MoneySystem/";
    private static String moneyfile = "money.yml";

    public File moneyDir = new File(moneypath);
    public File moneyFile = new File( moneyDir , moneyfile);
    public YamlConfiguration moneyConfig;


    public ConfigManger(){

        if (!moneyDir.exists()){
            moneyDir.mkdir();
        }
        if (!moneyFile.exists()){
            try {
                moneyFile.createNewFile();
            }catch (Exception e){
                System.out.println(e);

            }
        }

        this.moneyConfig = YamlConfiguration.loadConfiguration(moneyFile);



    }



    public void setMoney(@NotNull UUID uuid , double money){

        moneyConfig.set("Money." + uuid , money);
        saveMoneyConfig();


    }

    public void saveMoneyConfig(){

        //Save the moneyConfig
        try {
            moneyConfig.save(moneyFile);
        }catch (Exception e){
            System.out.println("Error while saving money.yml file!");
            System.out.println("Error: " + e);
        }

    }


}
