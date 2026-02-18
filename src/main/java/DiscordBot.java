import discord4j.core.DiscordClient;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.lifecycle.ReadyEvent;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.event.domain.message.ReactionAddEvent;
import discord4j.core.object.entity.Message;
import discord4j.core.object.entity.User;
import discord4j.core.shard.GatewayBootstrap;
import discord4j.gateway.GatewayOptions;
import discord4j.gateway.intent.Intent;
import discord4j.gateway.intent.IntentSet;
import reactor.core.publisher.Mono;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class DiscordBot {

    public static void main(String[] args) throws FileNotFoundException {

        File myObj = new File("src/main/resources/token.txt");
        Scanner myReader = new Scanner(myObj);
        GatewayBootstrap<GatewayOptions> client = DiscordClient.create(myReader.nextLine()).gateway().setEnabledIntents(IntentSet.nonPrivileged().or(IntentSet.of(Intent.MESSAGE_CONTENT)).or(IntentSet.of(Intent.GUILD_MESSAGE_REACTIONS)));
        myReader.close();


        Mono<Void> login = client.withGateway((GatewayDiscordClient gateway) -> {
            // ReadyEvent example
            Mono<Void> printOnLogin = gateway.on(ReadyEvent.class, event ->
                            Mono.fromRunnable(() -> {
                                final User self = event.getSelf();
                                System.out.printf("Logged in as %s#%s%n", self.getUsername(), self.getDiscriminator());
                            }))
                    .then();

            // MessageCreateEvent example
            Mono<Void> handlePingCommand = gateway.on(MessageCreateEvent.class, event -> {
                Message message = event.getMessage();

                if (message.getContent().equalsIgnoreCase("!ping")) {
                    return message.getChannel()
                            .flatMap(channel -> channel.createMessage("pong!"));
                }

                return Mono.empty();
            }).then();

            Mono<Void> getreactions = gateway.on(ReactionAddEvent.class ,event -> {
                event.getMessage().flatMap(msg -> msg.addReaction(ReactionEmoji.unicode("👍")))
                        .subscribe();
                return Mono.empty();
            }).then();
            // combine them!
            return printOnLogin.and(handlePingCommand);
        });
        login.block();



    }
}
