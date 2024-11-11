package org.example;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.cache.CacheFlag;
import org.example.Commands.Embed;
import org.example.Commands.Sum;
import org.example.Commands.UserInfo;
import org.example.Listeners.Welcome;
import org.example.Listeners.WelcomeUser;

import java.io.*;
import java.nio.file.Path;
import java.util.EnumSet;
import java.util.Properties;

public class Main {
    public static void main(String[] args) throws IOException {
        Properties properties=  new Properties();
        InputStream inputStream = Main.class.getClassLoader().getResourceAsStream("config.properties");
        properties.load(inputStream);
        String token = properties.getProperty("TOKEN");

        JDA jda =JDABuilder.createLight(token,EnumSet.of(GatewayIntent.GUILD_MEMBERS,GatewayIntent.GUILD_MESSAGES, GatewayIntent.MESSAGE_CONTENT,GatewayIntent.GUILD_PRESENCES))
                .enableCache(CacheFlag.ONLINE_STATUS).addEventListeners(new Welcome(), new WelcomeUser()).build();

        CommandManager commandManager = new CommandManager();
        commandManager.add(new Sum());
        commandManager.add(new Embed());
        commandManager.add(new UserInfo());
        jda.addEventListener(commandManager);

    }
}