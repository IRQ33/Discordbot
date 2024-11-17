package org.example;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class HiddenCommandManager extends ListenerAdapter {
    private List<ICommand> hidecommands = new ArrayList<>();


    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        for (ICommand command : hidecommands)
        {
            if(command.getName().equals(event.getName()))
            {
                command.execute(event);
                return;
            }
        }
    }

    @Override
    public void onReady(@NotNull ReadyEvent event) {

        JDA jda = event.getJDA();
        Guild guild1 = jda.getGuildById(1301278460404039750L);
        for (ICommand command : hidecommands)
        {
            if(command.getOptions()!= null)
            {
                guild1.upsertCommand(command.getName(), command.getDescription()).addOptions(command.getOptions()).queue();
            }
            else {
                guild1.upsertCommand(command.getName(), command.getDescription()).queue();
            }
        }



    }


    public  void addhidden(ICommand command)
    {
        hidecommands.add(command);
    }
}
