package bot.commands;

import bot.GuildSettings;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.Message;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

public class PartyCommand implements Command{

    @Override
    public String getCommand() {
        return "party";
    }

    @Override
    public List<String> getAliases() {
        return new ArrayList<>();
    }

    @Override
    public CommandInfo getCommandInfo() {
        return new CommandInfo("party", "get info of party","!party 0001");
    }

    @Override
    public Mono<Void> issueCommand(String[] args, MessageCreateEvent event, GuildSettings settings, GatewayDiscordClient client) {
        Mono<Message> PingMessage = event.getMessage().getChannel().flatMap(channel ->
                channel.createMessage(String.format("""
                        >>> # %s
                        Time : %s
                        Location: %s
                        Description: %s"""
                        ,"Trade fair", "13.4. 12:00-14:00", "Agora", "Showing of cool projects that have been worked with many months")));
        return PingMessage.then();
    }
}

