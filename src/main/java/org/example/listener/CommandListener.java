package org.example.listener;

import net.dv8tion.jda.api.entities.ChannelType;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.example.BotMain;

public class CommandListener extends ListenerAdapter {

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {

        String message = event.getMessage().getContentDisplay();

        if (event.isFromType(ChannelType.TEXT)) {
            TextChannel channel = event.getTextChannel();

            if (message.startsWith("!")) {
                String[] args = message.substring(1).split(" ");

                if (args.length == 1) {
                    if (args[0].equalsIgnoreCase("danke")) {
                        channel.sendMessage("Kein Ding " + event.getMember().getAsMention() + ":thumbsup:").queue();
                    }
                }
                if (args.length > 0) {
                    if (!BotMain.INSTANCE.getCmdmanager().perform(args[0], event.getMember(), channel, event.getMessage())) {

                    }
                }
            }
        }
    }

}


