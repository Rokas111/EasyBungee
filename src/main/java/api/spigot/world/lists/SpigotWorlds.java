package api.spigot.world.lists;

import api.spigot.APIInfo;
import api.spigot.world.SpigotWorld;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Used to store SpigotWorlds in a list
 *
 * @author Rokaz
 */
@RequiredArgsConstructor
public class SpigotWorlds {
    @NonNull @Getter private List<SpigotWorld> worlds;
    public SpigotWorlds(String serializedValue) {
        if (!serializedValue.contains(APIInfo.SEPARATOR_2)) {
            worlds = Stream.of(new SpigotWorld(serializedValue)).collect(Collectors.toList());
            return;
        }
        worlds = Stream.of(serializedValue.split(APIInfo.SEPARATOR_2)).map(value -> new SpigotWorld(value)).collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return String.join(APIInfo.SEPARATOR_2,worlds.stream().map(SpigotWorld::toString).collect(Collectors.toList()));
    }
}
