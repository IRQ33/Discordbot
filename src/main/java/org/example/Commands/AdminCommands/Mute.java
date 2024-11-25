package org.example.Commands.AdminCommands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import org.example.ICommand;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class Mute implements ICommand {
    @Override
    public String getName() {
        return "mute";
    }

    @Override
    public String getDescription() {
        return "Mute";
    }

    @Override
    public Collection<Permission> getPermissions() {
        return List.of(
                Permission.MESSAGE_MANAGE
        );
    }

    @Override
    public Collection<? extends OptionData> getOptions() {
        List<OptionData> optionDataList = new ArrayList<>();
        optionDataList.add(new OptionData(OptionType.USER,"name","name of user to mute",true));
        optionDataList.add(new OptionData(OptionType.STRING, "limit","how long to mute the user",true));
        optionDataList.add(new OptionData(OptionType.STRING,"reason","reason of mute",false));

        return optionDataList;
    }


    @Override
    public void execute(SlashCommandInteractionEvent slashCommandInteractionEvent) {
        User user = Objects.requireNonNull(slashCommandInteractionEvent.getOption("name")).getAsUser();
        String limit = Objects.requireNonNull(slashCommandInteractionEvent.getOption("limit")).getAsString();

        String reason = "";
        if (slashCommandInteractionEvent.getOption("reason") != null) {
            reason = Objects.requireNonNull(slashCommandInteractionEvent.getOption("reason")).getAsString();
        }

        //3m 5h 12d
        StringBuilder lenght= new StringBuilder();
        StringBuilder type= new StringBuilder();
        for (int i = 0; i < limit.length(); i++) {
            if (Character.isDigit(limit.charAt(i))) {
                lenght.append(limit.charAt(i));
            } else {
                type.append(limit.charAt(i));
            }
        }

        TimeUnit timeUnit = TimeUnit.HOURS;
        switch (type.toString())
        {
            case "m"-> timeUnit = TimeUnit.MINUTES;
            case "d"-> timeUnit = TimeUnit.DAYS;
            default -> slashCommandInteractionEvent.reply("You can use only minutes, hours or days").queue();
        }
        System.out.println("Mute: " + lenght + " " + timeUnit);


        if(reason.isEmpty())
        {

            slashCommandInteractionEvent.getGuild().timeoutFor(user, Duration.of(Long.parseLong(lenght.toString()),timeUnit.toChronoUnit())).queue();
            EmbedBuilder builder = new EmbedBuilder();
            builder.setTitle("Mute!!!");
            builder.setDescription(user.getEffectiveName()+ " got muted!!!");
            slashCommandInteractionEvent.replyEmbeds(builder.build()).queue();

        }
        else {
            slashCommandInteractionEvent.getGuild().timeoutFor(user, Duration.of(Long.parseLong(lenght.toString()),timeUnit.toChronoUnit())).reason(reason).queue();
            EmbedBuilder builder = new EmbedBuilder();
            builder.setTitle("Mute!!!");
            builder.setDescription(user.getEffectiveName()+ " got muted for "+reason+ "!!!" );
            //  builder.setImage("https://tenor.com/pl/view/banned-thor-banned-thor-ban-thor-admin-gif-12850590");
            slashCommandInteractionEvent.replyEmbeds(builder.build()).queue();
        }
    }


}
