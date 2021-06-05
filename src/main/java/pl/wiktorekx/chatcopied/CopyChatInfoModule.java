package pl.wiktorekx.chatcopied;

import net.labymod.ingamegui.Module;
import net.labymod.settings.elements.ControlElement;
import net.labymod.utils.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;

public class CopyChatInfoModule extends Module {
    private int t;

    @Override
    public ControlElement.IconData getIconData() {
        return new ControlElement.IconData(Material.STONE);
    }

    @Override
    public double getHeight() {
        return 10;
    }

    @Override
    public double getWidth() {
        return 35;
    }

    @Override
    public void loadSettings() {

    }

    @Override
    public String getSettingName() {
        return "CopyChatInfo";
    }

    @Override
    public String getDescription() {
        return "Info from ChatCopper";
    }

    @Override
    public int getSortingId() {
        return 0;
    }

    @Override
    public void draw(double x, double y, double rightX) {
        Gui.drawRect((int) x, (int) y, (int) (x + getWidth()), (int) (y + getHeight()), 0xff38cc2b);
        Minecraft.getMinecraft().fontRendererObj.drawString("Copied", (int)x, (int)y, 0xffffffff);
    }
}
