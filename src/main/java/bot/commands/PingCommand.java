package bot.commands;

import bot.GuildSettings;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.Message;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

public class PingCommand implements Command {

    @Override
    public String getCommand() {
        return "ping";
    }

    @Override
    public List<String> getAliases() {
        return new ArrayList<>();
    }

    @Override
    public CommandInfo getCommandInfo() {
        return new CommandInfo("Test","test","!ping");
    }


    @Override
    public Mono<Void> issueCommand(String[] args, MessageCreateEvent event, GuildSettings settings) {
        Mono<Message> PingMessage = event.getMessage().getChannel().flatMap(channel -> channel.createMessage("pong!"));
        return PingMessage.then();
    }
}
