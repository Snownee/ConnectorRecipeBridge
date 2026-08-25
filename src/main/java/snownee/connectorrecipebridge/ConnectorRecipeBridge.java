package snownee.connectorrecipebridge;

import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.ModList;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.InterModProcessEvent;

@Mod(ConnectorRecipeBridge.ID)
public class ConnectorRecipeBridge {
	public static final String ID = "connectorrecipebridge";
	public static final Logger LOGGER = LogUtils.getLogger();

	public ConnectorRecipeBridge(IEventBus modEventBus, ModContainer modContainer) {
		modEventBus.addListener(this::postInit);
	}

	private void postInit(InterModProcessEvent event) {
		if (ModList.get().isLoaded("fabric_resource_conditions_api_v1")) {
			event.enqueueWork(NeoConditionWrapper::wrapAll);
		}
		if (ModList.get().isLoaded("fabric_recipe_api_v1")) {
			event.enqueueWork(NeoIngredientWrapper::wrapAll);
		}
	}
}
