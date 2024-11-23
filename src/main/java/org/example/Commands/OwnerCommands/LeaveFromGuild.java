package org.example.Commands.OwnerCommands;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import org.example.ICommand;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class LeaveFromGuild implements ICommand {
    @Override
    public String getName() {
        return "leavefromguild";
    }

    @Override
    public String getDescription() {
        return "automatic leaving from guild";
    }

    @Override
    public Collection<Permission> getPermissions() {
        return List.of();
    }

    @Override
    public Collection<? extends OptionData> getOptions() {
        List<OptionData> optionData = new ArrayList<>();
        optionData.add(new OptionData(OptionType.STRING,"id","id"));

        return optionData;
    }

    @Override
    public void execute(SlashCommandInteractionEvent slashCommandInteractionEvent) {
        OptionMapping optionMapping = slashCommandInteractionEvent.getOption("id");
        long id = optionMapping.getAsLong();
        JDA jda = slashCommandInteractionEvent.getJDA();

        jda.getGuildById(id).leave().queue();
        slashCommandInteractionEvent.reply("Ok I left from"+ jda.getGuildById(id).getName());

    }
}
