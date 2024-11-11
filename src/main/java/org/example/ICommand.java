package org.example;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

import java.util.Collection;

public interface ICommand {
    String getName();
    String getDescription();

    Collection<? extends OptionData> getOptions();
    void execute(SlashCommandInteractionEvent slashCommandInteractionEvent);

}
