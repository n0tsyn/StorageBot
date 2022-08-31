package storagebot;

import storagebot.listeners.CommandListener;
import storagebot.listeners.EventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import java.nio.file.Paths;

import java.io.File;
import java.io.FileNotFoundException;

import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.sharding.DefaultShardManagerBuilder; 

public class Bot 
{
    public static void main(String[] args) throws Exception
    {
        String token = null; 
        List<GatewayIntent> intents = new ArrayList<>(); // https://ci.dv8tion.net/job/JDA5/javadoc/net/dv8tion/jda/api/requests/GatewayIntent.html

        try
        {
            File tokenFile = Paths.get("./src/main/resources/token.txt").toFile();
            Scanner reader = new Scanner(tokenFile);

            if (!tokenFile.exists())
            {
                reader.close();
                throw new FileNotFoundException("token file does not exist.");
            }

            token = reader.nextLine();
            reader.close();
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();  
        }

        if (token == null) return;

        DefaultShardManagerBuilder builder = DefaultShardManagerBuilder.create(token, intents);
        builder.setStatus(OnlineStatus.ONLINE);
        builder.addEventListeners(new CommandListener(), new EventListener());
        builder.build();
    }
}
