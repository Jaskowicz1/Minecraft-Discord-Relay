package me.jaskowicz.minecraftdiscordrelay.Utils;

import me.jaskowicz.minecraftdiscordrelay.Minecraftdiscordrelay;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.MessageBuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageEmbed;

import java.awt.*;
import java.time.OffsetDateTime;
import java.util.HashMap;

public class SimpleEmbedCreation {

    private String channelToSendTo;
    private String embedTitle = "";
    private String embedDescription = "";
    private Color embedColor;
    private String embedFooter = "";
    private String embedFooterLink = "";
    private String embedTimestamp = "";
    private String embedThumbnail = "";
    private String content = "";
    private HashMap<String, HashMap<String, Boolean>> fields = new HashMap<>();

    public SimpleEmbedCreation() {

    }

    public String getContent() {
        return content;
    }

    public String getChannelToSendTo() {
        return channelToSendTo;
    }

    public String getEmbedDescription() {
        return embedDescription;
    }

    public String getEmbedTitle() {
        return embedTitle;
    }

    public Color getEmbedColor() {
        return embedColor;
    }

    public String getEmbedFooter() {
        return embedFooter;
    }

    public String getEmbedFooterLink() {
        return embedFooterLink;
    }

    public String getEmbedTimestamp() {
        return embedTimestamp;
    }

    public String getEmbedThumbnail() {
        return embedThumbnail;
    }

    public HashMap<String, HashMap<String, Boolean>> getFields() {
        return fields;
    }

    public MessageBuilder buildMessage() {

        EmbedBuilder embed = new EmbedBuilder()
                .setTitle(this.embedTitle)
                .setDescription(this.embedDescription)
                .setColor(this.embedColor);

        if(this.embedTimestamp.isEmpty() || this.embedTimestamp.equals("AVOID")) {
            embed.setTimestamp(null);
        } else {
            embed.setTimestamp(OffsetDateTime.parse(this.embedTimestamp));
        }

        if(!this.embedThumbnail.isEmpty() && !this.embedThumbnail.equals("AVOID")) {
            embed.setThumbnail(this.embedThumbnail);
        }

        if(this.embedFooter.isEmpty() || this.embedFooter.equals("AVOID")) {
            embed.setFooter(null, null);
        } else {
            if(this.embedFooterLink.isEmpty() || this.embedFooterLink.equals("AVOID")) {
                embed.setFooter(this.embedFooter, null);
            } else {
                embed.setFooter(this.embedFooter, this.embedFooterLink);
            }
        }

        if(this.fields.size() != 0) {
            for (String title : this.fields.keySet()) {
                HashMap<String, Boolean> fieldData = this.fields.get(title);

                for (String description : fieldData.keySet()) {
                    embed.addField(title, description, fieldData.get(description));
                }
            }
        }

        return new MessageBuilder()
                .append(this.content)
                .setEmbed(embed.build());
    }

    public MessageEmbed buildEmbed() {

        EmbedBuilder embed = new EmbedBuilder()
                .setTitle(this.embedTitle)
                .setDescription(this.embedDescription)
                .setColor(this.embedColor);

        if(this.embedTimestamp.isEmpty() || this.embedTimestamp.equals("AVOID")) {
            embed.setTimestamp(null);
        } else {
            embed.setTimestamp(OffsetDateTime.parse(this.embedTimestamp));
        }

        if(!this.embedThumbnail.isEmpty() && !this.embedThumbnail.equals("AVOID")) {
            embed.setThumbnail(this.embedThumbnail);
        }

        if(this.embedFooter.isEmpty() || this.embedFooter.equals("AVOID")) {
            embed.setFooter(null, null);
        } else {
            if(this.embedFooterLink.isEmpty() || this.embedFooterLink.equals("AVOID")) {
                embed.setFooter(this.embedFooter, null);
            } else {
                embed.setFooter(this.embedFooter, this.embedFooterLink);
            }
        }

        if(this.fields.size() != 0) {
            for (String title : this.fields.keySet()) {
                HashMap<String, Boolean> fieldData = this.fields.get(title);

                for (String description : fieldData.keySet()) {
                    embed.addField(title, description, fieldData.get(description));
                }
            }
        }

        return embed.build();
    }


    // All voids after this line.


    public void setContent(String content) {
        this.content = content;
    }

    public void setChannelToSendTo(String channelToSendTo) {
        this.channelToSendTo = channelToSendTo;
    }

    public void setEmbedTitle(String embedTitle) {
        this.embedTitle = embedTitle;
    }

    public void setEmbedDescription(String embedDescription) {
        this.embedDescription = embedDescription;
    }

    public void setEmbedColor(Color embedColor) {
        this.embedColor = embedColor;
    }

    public void setEmbedTimestamp(String embedTimestamp) {
        this.embedTimestamp = embedTimestamp;
    }

    public void setEmbedFooter(String embedFooter) {
        this.embedFooter = embedFooter;
    }

    public void setEmbedFooterLink(String embedFooterLink) {
        this.embedFooterLink = embedFooterLink;
    }

    public void setEmbedThumbnail(String embedThumbnail) {
        this.embedThumbnail = embedThumbnail;
    }

    public void addField(String title, String description, boolean inline) {
        HashMap<String, Boolean> fieldData = new HashMap<>();
        fieldData.put(description, inline);
        fields.put(title, fieldData);
    }

    public Message sendAndGetMessage() {
        if(Minecraftdiscordrelay.jda.getTextChannelById(channelToSendTo) != null) {
            return Minecraftdiscordrelay.jda.getTextChannelById(channelToSendTo).sendMessage(buildMessage().build()).complete();
        } else {
            return null;
        }
    }

    public boolean sendMessage() {
        if(Minecraftdiscordrelay.jda.getTextChannelById(channelToSendTo) != null) {
            Minecraftdiscordrelay.jda.getTextChannelById(channelToSendTo).sendMessage(buildMessage().build()).queue();
            return true;
        } else {
            return false;
        }
    }
}
