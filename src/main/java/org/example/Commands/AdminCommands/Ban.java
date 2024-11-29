package org.example.Commands.AdminCommands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import org.example.ICommand;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Ban implements ICommand {
    @Override
    public String getName() {
        return "ban";
    }

    @Override
    public String getDescription() {
        return "Ban someone";
    }

    @Override
    public Collection<Permission> getPermissions() {
        return List.of(
                Permission.BAN_MEMBERS
        );
    }

    @Override
    public Collection<? extends OptionData> getOptions() {
        List<OptionData> optionDataList = new ArrayList<>();
        optionDataList.add(new OptionData(OptionType.USER,"name","name of user to ban",true));
        optionDataList.add(new OptionData(OptionType.STRING,"reason","reason of ban",false));

        return optionDataList;
    }


    @Override
    public void execute(SlashCommandInteractionEvent slashCommandInteractionEvent) {

        User user = slashCommandInteractionEvent.getOption("name").getAsUser();
        String reason;
        reason = slashCommandInteractionEvent.getOption("reason").getAsString();
        if(reason.isEmpty())
        {
            slashCommandInteractionEvent.getGuild().ban(user,0, TimeUnit.DAYS).queue();
            EmbedBuilder builder = new EmbedBuilder();
            builder.setTitle("BAN!!!");
            builder.setDescription(user.getEffectiveName()+ " got banned!!!");
           // builder.setImage("https://tenor.com/pl/view/banned-thor-banned-thor-ban-thor-admin-gif-12850590");
            slashCommandInteractionEvent.replyEmbeds(builder.build()).queue();

        }
        else {
            slashCommandInteractionEvent.getGuild().ban(user,0, TimeUnit.DAYS).reason(reason).queue();
            slashCommandInteractionEvent.getGuild().ban(user,0, TimeUnit.DAYS).queue();
            EmbedBuilder builder = new EmbedBuilder();
            builder.setTitle("BAN!!!");
            builder.setDescription(user.getEffectiveName()+ " got banned for "+reason+ "!!!" );
          //  builder.setImage("https://tenor.com/pl/view/banned-thor-banned-thor-ban-thor-admin-gif-12850590");
            slashCommandInteractionEvent.replyEmbeds(builder.build()).queue();
        }
    }


}
