package org.example.commands;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.TextChannel;
import org.example.commands.types.ServerCommand;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ClearCommand implements ServerCommand {

    @Override
    public void performCommand(Member m, TextChannel channel, Message message) {

        if (m.hasPermission(channel, Permission.MESSAGE_MANAGE)) {
            //message.delete().queue();
            String[] args = message.getContentDisplay().split(" ");

            if (args.length == 2) {

                try {
                    int ammount = Integer.parseInt(args[1]);
                    channel.purgeMessages(get(channel, ammount));
                    channel.sendMessage("Deleted " + ammount + " Messages.").complete().delete().queueAfter(2, TimeUnit.SECONDS);
                    return;
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }

            }
        }


    }

    public List<Message> get(MessageChannel channel, int amount) {
        List<Message> messages = new ArrayList<>();
        int i = amount + 1;

        for (Message message : channel.getIterableHistory().cache(false)) {
            if (!message.isPinned()) {
                messages.add(message);
                if (--i <= 0) break;
            }
        }


        return messages;
    }
}
