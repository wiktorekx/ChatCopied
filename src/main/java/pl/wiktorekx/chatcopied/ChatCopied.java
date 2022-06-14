package pl.wiktorekx.chatcopied;

import com.google.gson.JsonPrimitive;
import net.labymod.api.LabyModAddon;
import net.labymod.settings.elements.BooleanElement;
import net.labymod.settings.elements.ControlElement;
import net.labymod.settings.elements.SettingsElement;
import net.labymod.settings.elements.StringElement;
import net.labymod.utils.Consumer;
import net.labymod.utils.Material;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import pl.wiktorekx.chatcopied.listeners.ChatCopiedListeners;

import java.util.List;

public class ChatCopied extends LabyModAddon {
    private boolean enable = true;
    private String prefix = "&f [";
    private String suffix = "&f]";
    private String copy = "&acopy";

    @Override
    public void onEnable() {
        ChatCopiedListeners chatCopiedListeners = new ChatCopiedListeners(this);
        getApi().getEventManager().register(chatCopiedListeners);
        getApi().registerForgeListener(chatCopiedListeners);
    }

    @Override
    public void loadConfig() {
        if(getConfig().has("enable")){
            enable = getConfig().get("enable").getAsBoolean();
        }
        if(getConfig().has("prefix")){
            prefix = getConfig().get("prefix").getAsString();
        }
        if(getConfig().has("suffix")){
            suffix = getConfig().get("suffix").getAsString();
        }
        if(getConfig().has("copy")){
            copy = getConfig().get("copy").getAsString();
        }
    }

    @Override
    protected void fillSettings(List<SettingsElement> list) {
        list.add(new BooleanElement("enable", new ControlElement.IconData(Material.LEVER), new Consumer<Boolean>() {
            @Override
            public void accept(Boolean b) {
                enable = b;
                getConfig().add("enable", new JsonPrimitive(b));
            }
        }, enable));
        list.add(new StringElement("prefix", new ControlElement.IconData(Material.PAPER), prefix, new Consumer<String>() {
            @Override
            public void accept(String s) {
                prefix = s;
                getConfig().add("prefix", new JsonPrimitive(s));
            }
        }));
        list.add(new StringElement("suffix", new ControlElement.IconData(Material.PAPER), suffix, new Consumer<String>() {
            @Override
            public void accept(String s) {
                suffix = s;
                getConfig().add("suffix", new JsonPrimitive(s));
            }
        }));
        list.add(new StringElement("copy", new ControlElement.IconData(Material.PAPER), copy, new Consumer<String>() {
            @Override
            public void accept(String s) {
                copy = s;
                getConfig().add("copy", new JsonPrimitive(s));
            }
        }));
    }

    public IChatComponent getCopyTextComponent(IChatComponent copyText){
        IChatComponent prefix = new ChatComponentText(color(this.prefix));
        IChatComponent suffix = new ChatComponentText(color(this.suffix));
        IChatComponent text = new ChatComponentText(color(this.copy));
        CustomChatStyle customChatStyle = new CustomChatStyle(text);
        customChatStyle.setProperty("copyText", copyText);
        text.setChatStyle(customChatStyle);
        return prefix.appendSibling(text).appendSibling(suffix);
    }

    public String color(String text){
        return text.replace("&", "\u00A7");
    }

    public boolean isEnable() {
        return enable;
    }
}
