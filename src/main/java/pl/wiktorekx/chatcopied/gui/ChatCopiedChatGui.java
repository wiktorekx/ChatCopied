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

    //    private int getChatValueOf(int height) throws NoSuchFieldException, IllegalAccessException {
//        GuiNewChat guiNewChat = this.mc.ingameGUI.getChatGUI();
//        if(guiNewChat.getChatOpen()){
//            if(guiNewChat.getChatComponent(0, height) != null) {
//                Field field = GuiNewChat.class.getDeclaredField("scrollPos");
//                field.setAccessible(true);
//                int scrollPos = field.getInt(guiNewChat);
//                ScaledResolution scaledresolution = new ScaledResolution(this.mc);
//                int lineChatHeight = MathHelper.floor_float((float) (height / scaledresolution.getScaleFactor() - 27) / guiNewChat.getChatScale());
//                int lineCount = guiNewChat.getLineCount();
//                if (lineChatHeight >= 0 && lineChatHeight < this.mc.fontRendererObj.FONT_HEIGHT * lineCount + lineCount) {
//                    return lineChatHeight / this.mc.fontRendererObj.FONT_HEIGHT + scrollPos;
//                }
//            }
//        }
//        return -1;
//    }
//
//    @Override
//    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
//        super.drawScreen(mouseX, mouseY, partialTicks);
////        IChatComponent ichatcomponent = this.mc.ingameGUI.getChatGUI().getChatComponent(Mouse.getX(), Mouse.getY());
////        this.mc.ingameGUI.getChatGUI().printChatMessageWithOptionalDeletion(ichatcomponent, 1);
////        System.out.println(this.mc.ingameGUI.getChatGUI().getSentMessages());
//    }
}