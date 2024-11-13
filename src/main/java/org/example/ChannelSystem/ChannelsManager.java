package org.example.ChannelSystem;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.channel.concrete.Category;
import net.dv8tion.jda.api.entities.channel.concrete.VoiceChannel;

import java.util.ArrayList;
import java.util.List;

public class ChannelsManager {

    Guild guild;
    Category category;
    List<VoiceChannel> channels =  new ArrayList<>();
    public ChannelsManager(Long id_cateogry, Guild guild)
    {
        this.guild = guild;
        this.category = guild.getCategoryById(id_cateogry);

    }
    public void getChannels()
    {
        channels.addAll(category.getVoiceChannels());
    }

    public void Voice_Managment()
    {
        for (int i =0; i<=3; i++)
        {
            category.createVoiceChannel("Voice channel").queue();
        }
        for (VoiceChannel voiceChannel : channels)
        {

        }
    }
}
