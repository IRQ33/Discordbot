package org.example.Commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import org.example.ICommand;

import java.util.Collection;
import java.util.List;

public class Unmute implements ICommand {
    @Override
    public String getName() {
        return "unmute";
    }

    @Override
    public String getDescription() {
        return "Unmute someone";
    }

    @Override
    public Collection<Permission> getPermissions() {
        return List.of(
                Permission.MESSAGE_MANAGE
        );
    }

    @Override
    public Collection<? extends OptionData> getOptions() {
        return List.of(
                new OptionData(OptionType.USER,"user","user to unmute",true)
        );
    }

    @Override
    public void execute(SlashCommandInteractionEvent slashCommandInteractionEvent) {
        OptionMapping optionMapping = slashCommandInteractionEvent.getOption("user");

        slashCommandInteractionEvent.getGuild().removeTimeout(optionMapping.getAsUser()).queue();

        EmbedBuilder builder = new EmbedBuilder();
        builder.setTitle("Unmuted!!!");
        builder.setDescription("You unmuted: "+ optionMapping.getAsMember().getEffectiveName());

        slashCommandInteractionEvent.replyEmbeds(builder.build()).queue();

    }
}
