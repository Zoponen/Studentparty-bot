package bot.commands;

import bot.GuildSettings;
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
    public Mono<Void> issueCommand(String[] args, MessageCreateEvent event, GuildSettings settings) {
        Mono<Message> PingMessage = event.getMessage().getChannel().flatMap(channel ->
                channel.createMessage(String.format("""
                        >>> # %s
                        Time : %s
                        Location: %s
                        Description: %s""","Test","13:37","Lipasto","AAHAH HAHDHADHAWNBSDJAWJdAHSD HAWJSDJWJASD")));
        return PingMessage.then();
    }
}

