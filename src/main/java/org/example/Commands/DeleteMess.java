package org.example.Commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.channel.unions.MessageChannelUnion;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.InteractionHook;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import net.dv8tion.jda.api.requests.RestAction;
import org.example.ICommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class DeleteMess implements ICommand {
    private static final Logger log = LoggerFactory.getLogger(DeleteMess.class);

    @Override
    public String getName() {
        return "clear";
    }

    @Override
    public String getDescription() {
        return "Deleting options";
    }

    @Override
    public Collection<? extends OptionData> getOptions() {
        return List.of(
                new OptionData(OptionType.INTEGER, "amount", "amount messages to delete",true).setMinValue(2).setMaxValue(100)
        );
    }

    @Override
    public void execute(SlashCommandInteractionEvent slashCommandInteractionEvent)  {
        MessageChannelUnion channel = slashCommandInteractionEvent.getChannel();
        int amountofmessage = Objects.requireNonNull(slashCommandInteractionEvent.getOption("amount")).getAsInt();

        List<Message> messageList =channel.getHistory().retrievePast(amountofmessage).complete();
        try {
            channel.asTextChannel().deleteMessages(messageList).queue();
        }
        catch (IllegalArgumentException e)
        {
            log.error("e: ", e);
        }


        EmbedBuilder builder = new EmbedBuilder();
        builder.setTitle("I deleted "+messageList.size()+" messages!!!");
        SendAndDestruct(slashCommandInteractionEvent,builder.build()).queue();

    }
    public RestAction<Void> SendAndDestruct(SlashCommandInteractionEvent event, MessageEmbed embed)
    {
        return event.replyEmbeds(embed).delay(5,TimeUnit.SECONDS).flatMap(InteractionHook::deleteOriginal);
    }
}
