package org.example.Listeners;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class Welcome extends ListenerAdapter {
    @Override
    public void onReady(@NotNull ReadyEvent event) {
        JDA jda = event.getJDA();


        System.out.println("=======INFO=======");
        System.out.println("Guilds:");
        for (Guild guildtoshow : jda.getGuilds())
        {
            System.out.println("- "+guildtoshow.getName());
        }
        TextChannel textChannel = jda.getTextChannelById(1305559041807749260L);
        textChannel.sendMessage("est").queue();

    }

    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        if(event.getMessage().getContentRaw().equals("hi") && !event.getAuthor().isBot())
        {
            event.getMessage().reply("hello bro").queue();
        }

    }
}
