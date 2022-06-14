package pl.wiktorekx.chatcopied.gui;

import net.labymod.ingamechat.GuiChatCustom;
import net.minecraft.util.IChatComponent;
import pl.wiktorekx.chatcopied.CustomChatStyle;

import java.awt.*;
import java.awt.datatransfer.StringSelection;

public class ChatCopiedChatGui extends GuiChatCustom {

    public ChatCopiedChatGui(String defaultText) {
        super(defaultText);
    }

    @Override
    protected boolean handleComponentClick(IChatComponent chatComponent) {
        if(chatComponent != null){
            if(chatComponent.getChatStyle() instanceof CustomChatStyle){
                CustomChatStyle customChatStyle = (CustomChatStyle) chatComponent.getChatStyle();
                Object obj = customChatStyle.getProperties().get("copyText");
                if(obj instanceof IChatComponent){
                    Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new StringSelection(((IChatComponent) obj).getUnformattedText()), null);
                }
            }
            return super.handleComponentClick(chatComponent);
        }
        return false;
    }
}