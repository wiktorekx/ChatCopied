package pl.wiktorekx.chatcopied.listeners;

import net.labymod.api.events.MessageModifyChatEvent;
import net.labymod.core.LabyModCore;
import net.labymod.ingamechat.GuiChatCustom;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.IChatComponent;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.ReflectionHelper;
import pl.wiktorekx.chatcopied.ChatCopied;
import pl.wiktorekx.chatcopied.gui.ChatCopiedChatGui;

import java.lang.reflect.Field;

public class ChatCopiedListeners implements MessageModifyChatEvent {
    private ChatCopied chatCopied;

    public ChatCopiedListeners(ChatCopied chatCopied) {
        this.chatCopied = chatCopied;
    }

    @Override
    public Object onModifyChatMessage(Object o) {
        if (chatCopied.isEnable()) {
            IChatComponent iChatComponent = (IChatComponent) o;
            iChatComponent.appendSibling(chatCopied.getCopyTextComponent(iChatComponent.createCopy()));
            return iChatComponent;
        }
        return o;
    }

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public void onOpenGui(GuiOpenEvent event) {
        try {
            if (chatCopied.isEnable()) {
                GuiScreen guiScreen = LabyModCore.getForge().getGuiOpenEventGui(event);
                if (guiScreen != null) {
                    if (guiScreen.getClass().equals(GuiChatCustom.class)) {
                        LabyModCore.getForge().setGuiOpenEventGui(event, new ChatCopiedChatGui(getDefaultInputFieldText(guiScreen)));
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private String getDefaultInputFieldText(GuiScreen guiScreen) throws IllegalAccessException {
        Field field = ReflectionHelper.findField(GuiChat.class, "u", "field_146409_v", "defaultInputFieldText");
        field.setAccessible(true);
        return (String) field.get(guiScreen);
    }
}
