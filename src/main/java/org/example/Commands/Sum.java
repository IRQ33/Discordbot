package org.example.Commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import org.example.ICommand;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Sum  implements ICommand {
    @Override
    public String getName() {
        return "sum";
    }

    @Override
    public String getDescription() {
        return "Summing ";
    }

    @Override
    public Collection<Permission> getPermissions() {
        return List.of(Permission.MESSAGE_SEND);
    }

    @Override
    public Collection<? extends OptionData> getOptions() {
        List<OptionData> data = new ArrayList<>();
        data.add(new OptionData(OptionType.INTEGER,"number1", "Type first number", true ).setMinValue(1).setMaxValue(100));
        data.add(new OptionData(OptionType.INTEGER,"number2", "Type second number", false ).setMinValue(1).setMaxValue(100));
        return data;
    }

    @Override
    public void execute(SlashCommandInteractionEvent slashCommandInteractionEvent) {
        OptionMapping firstnumber = slashCommandInteractionEvent.getOption("number1");
        OptionMapping secondnumber = slashCommandInteractionEvent.getOption("number2");
        assert firstnumber != null;
        int number = firstnumber.getAsInt();
        int number2= 1;
        if(secondnumber!= null)
        {
            number2 = secondnumber.getAsInt();
        }
        int calculation = number+number2;
        EmbedBuilder builder = new EmbedBuilder();
        builder.setTitle("Sum");
        builder.setDescription("Sum of this numbers is: "+calculation);
        slashCommandInteractionEvent.replyEmbeds(builder.build()).queue();
    }
}
