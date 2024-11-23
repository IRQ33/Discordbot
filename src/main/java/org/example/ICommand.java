package org.example;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public interface ICommand {
    String getName();
    String getDescription();
    Collection<Permission> getPermissions();

    Collection<? extends OptionData> getOptions();
    void execute(SlashCommandInteractionEvent slashCommandInteractionEvent);

}
