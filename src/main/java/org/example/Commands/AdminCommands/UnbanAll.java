package org.example.Commands.AdminCommands;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import org.example.ICommand;

import java.util.Collection;
import java.util.List;

public class UnbanAll implements ICommand {
    @Override
    public String getName() {
        return "unbanall";
    }

    @Override
    public String getDescription() {
        return "Unbanning all people";
    }

    @Override
    public Collection<Permission> getPermissions() {
        return List.of(Permission.ADMINISTRATOR);
    }

    @Override
    public Collection<? extends OptionData> getOptions() {
        return List.of();
    }

    @Override
    public void execute(SlashCommandInteractionEvent slashCommandInteractionEvent) {
        List<Guild.Ban> userList = slashCommandInteractionEvent.getGuild().retrieveBanList().stream().toList();
        for (Guild.Ban ban: userList){
            User user = ban.getUser();
            slashCommandInteractionEvent.getGuild().unban(user).queue();
            System.out.println("Unbanned "+ user.getEffectiveName());
        }

    }
}
