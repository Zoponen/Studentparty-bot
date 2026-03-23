package bot;

import bot.listeners.MessageCreateListener;
import bot.listeners.ReadyEventListener;
import discord4j.core.DiscordClient;
import discord4j.core.DiscordClientBuilder;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.lifecycle.ReadyEvent;
import discord4j.core.event.domain.message.MessageCreateEvent;
import reactor.core.publisher.Mono;

public class Client {

    private static GatewayDiscordClient client;
    public GuildSettings settings;


    public static GatewayDiscordClient create(String token){
        DiscordClientBuilder.create(token).build().gateway()
                .withGateway(client ->{

                    final Mono<Void> onBoot = client.on(ReadyEvent.class)
                            .flatMap(ReadyEventListener::handle)
                            .then();

                    final Mono<Void> onCommand = client
                            .on(MessageCreateEvent.class, MessageCreateListener::handle)
                            .then();

                    return Mono.when(onBoot, onCommand);
                        }).block();

        try {
            return client;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

    public static GatewayDiscordClient createClient(String token) {
        GatewayDiscordClient client = DiscordClientBuilder.create(token).build().login().block();
        try {
            return client;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static GatewayDiscordClient getInstance(){
        return client;
    }
}
