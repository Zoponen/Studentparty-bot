package bot.listeners;

import discord4j.core.event.domain.lifecycle.ReadyEvent;
import discord4j.core.object.entity.User;
import reactor.core.publisher.Mono;

public class ReadyEventListener {
    public static Mono<Void> handle(final ReadyEvent event) {
        User self = event.getSelf();
        System.out.println(String.format("Logged in as %s#%s", self.getUsername(), self.getDiscriminator()));

        return Mono.empty();
    }
}
