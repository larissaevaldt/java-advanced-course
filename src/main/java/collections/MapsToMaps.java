package collections;

import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

public class MapsToMaps {
    public static void main(String[] args) {
        mapsToMaps();
    }
    public static void mapsToMaps(){
        // ThreeMap is sorted by its keys
        Map<String, Integer> channelToSubscribers    = new TreeMap<>(); // channel, numSubscribers
        Map<String, String> channelToPublisher       = new TreeMap<>(); // channel, publisher
        Map<String, Integer> publisherToSubscribers  = new TreeMap<>(); // publisher, numSubscribers

        // channel -> number of subscribers
        // K -> V1
        channelToSubscribers.put("JustForLaughs", 120_000);
        channelToSubscribers.put("JustForGags", 10_000);
        channelToSubscribers.put("ContemplationTechniques", 10_000);
        channelToSubscribers.put("A New Earth", 20_000);

        // channel -> publisher
        // K -> V2
        channelToPublisher.put("JustForLaughs", "Charlie Chaplin");
        channelToPublisher.put("JustForGags", "Charlie Chaplin");
        channelToPublisher.put("ContemplationTechniques", "Echhart Tolle");
        channelToPublisher.put("A New Earth", "Echhart Tolle");

        // 1. Using a forEach(BiConsumer), set up the publisherToSubscribers map
        // publisher -> number of subscribers (total)
        // V2 -> V1
        channelToSubscribers.forEach((channel, numSubscribers) -> {
            String publisher = channelToPublisher.get(channel);
            // does that publisher already exist in the publisherToSubscribers map?
            if (publisherToSubscribers.containsKey(publisher)) {
                int currentSubscribers = publisherToSubscribers.get(publisher);
                int newVal = currentSubscribers + numSubscribers;
                publisherToSubscribers.put(publisher, newVal); //replaces old value
            } else {
                publisherToSubscribers.put(publisher, numSubscribers);
            }
        });

        // 2. Output "publisherToSubscribers"
       publisherToSubscribers.forEach((k, v) -> System.out.println("publisher: " + k + "; numSubscribers: " + v));

        // 3. Who has the most/least subscribers?
        int minSubscribers = Collections.max(publisherToSubscribers.values());
        int maxSubscribers = Collections.min(publisherToSubscribers.values());

        publisherToSubscribers.forEach((publisher, numSubscribers) -> {
            if (numSubscribers == maxSubscribers) {
                System.out.println("Publisher with most subscribers: " + publisher + " " + maxSubscribers);
            } else if (numSubscribers == minSubscribers) {
                System.out.println("Publisher with fewest subscribers: " + publisher + " " + minSubscribers);
            }
        });

    }
}