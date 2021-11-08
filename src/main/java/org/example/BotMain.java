package org.example;

import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.sharding.DefaultShardManagerBuilder;
import net.dv8tion.jda.api.sharding.ShardManager;
import org.example.listener.CommandListener;

import javax.security.auth.login.LoginException;
import java.util.Scanner;

public class BotMain {

    public static BotMain INSTANCE;

    public ShardManager manager;
    private CommandManager cmdmanager;

    public static void main(String[] args) {
        try {
            new BotMain();
        } catch (LoginException | IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    public BotMain() throws LoginException, IllegalArgumentException {

        INSTANCE = this;

        DefaultShardManagerBuilder builder = DefaultShardManagerBuilder.createDefault("OTA3MzE3NDI3NjMxNjkzODI0.YYlbMw.TVc1jSP6fG8dPu3eU2xuwg8E7M4");

        builder.setActivity(Activity.playing("mit deiner Mum."));

        builder.setStatus(OnlineStatus.ONLINE);

        this.cmdmanager = new CommandManager();

        builder.addEventListeners(new CommandListener());

        manager = builder.build();
        System.out.println("Bot Online");

        shutdown();

    }

    public void shutdown() {

        new Thread(() -> {

            String line = "";
            Scanner input = new Scanner(System.in);
            while ((line = input.nextLine()) != null) {

                if (line.equalsIgnoreCase("exit")) {
                    if (manager != null) {
                        manager.setStatus(OnlineStatus.OFFLINE);
                        manager.shutdown();
                        System.out.println("Bot offline.");
                    }
                    input.close();
                } else {
                    System.out.println("Use 'exit' to shutdown.");
                }
            }
        }).start();
    }

    public CommandManager getCmdmanager() {
        return cmdmanager;
    }
}


