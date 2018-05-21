package me.s4wa;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.common.MinecraftForge;

import org.lwjgl.input.Keyboard;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

@Mod(modid = SensChanger.MODID, version = SensChanger.VERSION)
public class SensChanger
{
    public static final String MODID = "Sensitivity-changer";
    public static final String NAME = "Sensitivity Changer";
    public static final String VERSION = "1.0";
    private KeyBinding bindKeyUp;
    private KeyBinding bindKeyDown;
    private Minecraft mc = Minecraft.getMinecraft();
    private String text = null;

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
		bindKeyUp = new KeyBinding("Up Sensitivity", Keyboard.KEY_UP, NAME);
		bindKeyDown = new KeyBinding("Down Sensitivity", Keyboard.KEY_DOWN, NAME);
		text = null;
		MinecraftForge.EVENT_BUS.register(this);
	    ClientRegistry.registerKeyBinding(bindKeyUp);
	    ClientRegistry.registerKeyBinding(bindKeyDown);
    }

	@SubscribeEvent
	public void onRender(RenderGameOverlayEvent event) {
		if (mc.currentScreen != null) return;

		if (Keyboard.isKeyDown(bindKeyUp.getKeyCode())) {
			if (mc.gameSettings.mouseSensitivity >= 1) return;
			mc.gameSettings.mouseSensitivity = mc.gameSettings.mouseSensitivity +0.000055F;
		}
		if (Keyboard.isKeyDown(bindKeyDown.getKeyCode())) {
			if (mc.gameSettings.mouseSensitivity <= 0) return;
			mc.gameSettings.mouseSensitivity = mc.gameSettings.mouseSensitivity -0.000055F;
		}
		if (Keyboard.isKeyDown(bindKeyUp.getKeyCode()) || Keyboard.isKeyDown(bindKeyDown.getKeyCode())) {
			text = p();
			mc.fontRenderer.drawString(text, 10, 10, 0xFFFFFF);
		}
	}
	
	public String p() {
		float sens = mc.gameSettings.mouseSensitivity;
		return ((int)(sens * 200.0F)) + "%";
	}
}
