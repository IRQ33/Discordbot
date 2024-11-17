package org.example.Helpers;

import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.InteractionHook;
import net.dv8tion.jda.api.requests.RestAction;

import java.util.concurrent.TimeUnit;

public class SendAndDestruct {
    public static RestAction<Void> SendAndDestruct(SlashCommandInteractionEvent event, MessageEmbed embed)
    {
        return event.replyEmbeds(embed).delay(5, TimeUnit.SECONDS).flatMap(InteractionHook::deleteOriginal);
    }


}
