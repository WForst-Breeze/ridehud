package net.treset.ridehud.modmenu;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import net.treset.ridehud.RideHudClient;
import net.treset.ridehud.config.gui.ConfigGui;

public class ModMenuCompat implements ModMenuApi {
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() { //set config screen as modmenu options
        return (screen) -> {
            RideHudClient.configScreen = new ConfigGui();
            RideHudClient.configScreen.setParent(screen);
            return RideHudClient.configScreen;
        };
    }
}
