package org.example.Commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import org.example.ICommand;

import java.util.Collection;
import java.util.List;

public class Embed implements ICommand {
    @Override
    public String getName() {
        return "embedtest";
    }

    @Override
    public String getDescription() {
        return "return of embed";
    }

    @Override
    public Collection<Permission> getPermissions() {
        return List.of(Permission.MESSAGE_SEND);
    }

    @Override
    public Collection<? extends OptionData> getOptions() {
        return List.of();
    }

    @Override
    public void execute(SlashCommandInteractionEvent slashCommandInteractionEvent) {
        EmbedBuilder builder = new EmbedBuilder();
        builder.setTitle("Testing");
        builder.setDescription("Epic test");
        builder.setAuthor(slashCommandInteractionEvent.getMember().getEffectiveName());


        slashCommandInteractionEvent.replyEmbeds(builder.build()).queue();
    }
}
