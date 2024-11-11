package org.example.Listeners;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import org.jetbrains.annotations.NotNull;

public class Welcome extends ListenerAdapter {
    @Override
    public void onReady(@NotNull ReadyEvent event) {
        JDA jda = event.getJDA();

        Guild guild = jda.getGuildById(1301278460404039750L);



        System.out.println("=======INFO=======");
        System.out.println("Guilds:");
        for (Guild guildtoshow : jda.getGuilds())
        {
            System.out.println("- "+guildtoshow.getName());
        }

    }

    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        if(event.getMessage().getContentRaw().equals("hi") && !event.getAuthor().isBot())
        {
            event.getMessage().reply("hello bro").queue();
        }

    }
}
