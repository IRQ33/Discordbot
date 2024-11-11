package org.example.Listeners;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class WelcomeUser extends ListenerAdapter {

    @Override
    public void onGuildMemberJoin(@NotNull GuildMemberJoinEvent event) {
        if(event.getUser().isBot()) return;
        System.out.println("test");
        JDA jda = event.getJDA();
        TextChannel channel = jda.getTextChannelById(1305559041807749260L);
        EmbedBuilder builder = new EmbedBuilder();
        builder.setTitle("New person joined!!!");
        builder.setDescription(event.getMember().getEffectiveName());
        builder.setImage(event.getMember().getEffectiveAvatarUrl());
        assert channel != null;
        channel.sendMessageEmbeds(builder.build()).queue();

    }

}
