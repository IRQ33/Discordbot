package org.example.ChannelSystem;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Widget;
import net.dv8tion.jda.api.entities.channel.concrete.Category;

import java.util.ArrayList;
import java.util.List;

public class ChannelsManager {

    Guild guild;
    Category category;
    List<Widget.VoiceChannel> channels =  new ArrayList<>();
    public ChannelsManager(Long id_cateogry, Guild guild)
    {
        this.guild = guild;
        this.category = guild.getCategoryById(id_cateogry);

    }

    public void Voice_Managment()
    {
        for (int i =0; i<=3; i++)
        {
            

        }
    }
}
