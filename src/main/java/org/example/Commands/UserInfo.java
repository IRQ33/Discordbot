package org.example.Commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import net.dv8tion.jda.api.utils.cache.CacheFlag;
import org.example.ICommand;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

public class UserInfo implements ICommand {
    @Override
    public String getName() {
        return "showinfo";
    }

    @Override
    public String getDescription() {
        return "Showinginfo";
    }

    @Override
    public Collection<? extends OptionData> getOptions() {
        List<OptionData> optionData = new ArrayList<>();
        optionData.add(new OptionData(OptionType.USER, "user", "name of user which u want to check info", false));

        return  optionData;
    }

    @Override
    public void execute(SlashCommandInteractionEvent slashCommandInteractionEvent) {
        OptionMapping option1 = slashCommandInteractionEvent.getOption("user");
        if(option1 == null)
        {
            EmbedBuilder builder = new EmbedBuilder();
            builder.setTitle("User info");
            try {
                builder.addField(new MessageEmbed.Field("Name:", slashCommandInteractionEvent.getMember().getEffectiveName(),true));
            }
            catch (NullPointerException exception)
            {
                System.out.println("issue");
            }
            builder.addField(new MessageEmbed.Field("Date of joined: ", slashCommandInteractionEvent.getMember().getTimeJoined().format(DateTimeFormatter.ISO_LOCAL_DATE), false));
            builder.addField(new MessageEmbed.Field("Date of creation", slashCommandInteractionEvent.getMember().getTimeCreated().format(DateTimeFormatter.ISO_LOCAL_DATE), false));
            builder.addField(new MessageEmbed.Field("IsOnline", slashCommandInteractionEvent.getMember().getOnlineStatus().toString(), false));

            builder.setImage(slashCommandInteractionEvent.getMember().getEffectiveAvatarUrl());

            slashCommandInteractionEvent.replyEmbeds(builder.build()).queue();

        }
        else {
            EmbedBuilder builder = new EmbedBuilder();
            builder.setTitle("User info");
            try {
                builder.addField(new MessageEmbed.Field("Name:", option1.getAsMember().getEffectiveName(),true));
            }
            catch (NullPointerException exception)
            {
                System.out.println("issue");
            }
            builder.addField(new MessageEmbed.Field("Date of joined: ", option1.getAsUser().getTimeCreated().format(DateTimeFormatter.ISO_LOCAL_DATE), false));
            builder.addField(new MessageEmbed.Field("Date of creation", option1.getAsUser().getTimeCreated().format(DateTimeFormatter.ISO_LOCAL_DATE), false));
            builder.addField(new MessageEmbed.Field("Is he online", Objects.requireNonNull(option1.getAsMember()).getOnlineStatus().toString(), false));

            builder.setImage(option1.getAsMember().getEffectiveAvatarUrl());

            slashCommandInteractionEvent.replyEmbeds(builder.build()).queue();
        }

    }
}
