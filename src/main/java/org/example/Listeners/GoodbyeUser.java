package org.example.Listeners;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.events.guild.GuildLeaveEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class GoodbyeUser extends ListenerAdapter {

    @Override
    public void onGuildLeave(@NotNull GuildLeaveEvent event) {
        JDA jda = event.getJDA();
        TextChannel channel = jda.getTextChannelById(1306000659560136765L);

        EmbedBuilder builder = new EmbedBuilder();
        builder.setTitle("Goodbye!!!");
        builder.setDescription("Someone left :(");
        builder.addField("Users: ", event.getGuild().getMembers().size()+" users",true);

        channel.sendMessageEmbeds(builder.build()).queue();
    }
}
