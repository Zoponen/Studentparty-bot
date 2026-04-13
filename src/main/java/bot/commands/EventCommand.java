package bot.commands;


import bot.GuildSettings;
import discord4j.common.util.Snowflake;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.Message;
import discord4j.core.object.entity.channel.TextChannel;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

public class EventCommand implements Command {

    public String getCommand() {
        return "bgl";
    }

    @Override
    public List<String> getAliases() {
        return new ArrayList<>();
    }

    @Override
    public CommandInfo getCommandInfo() {
        return new CommandInfo("bgl", "bgl","bgl");
    }

    @Override
    public Mono<Void> issueCommand(String[] args, MessageCreateEvent event, GuildSettings settings, GatewayDiscordClient client) {


        Mono<Message> EventMessage =
                client.getChannelById(Snowflake.of("1467903841876840590")).cast(TextChannel.class).flatMap(channel ->
                channel.createMessage(String.format("""
                        >>> # %s
                        Time : %s
                        Location: %s
                        Description: %s"""
                        , "Trade fair", "13.4. 12:00-14:00", "Agora", "Showing of cool projects that have been worked with many months")));
        return EventMessage.then();
    }
}
