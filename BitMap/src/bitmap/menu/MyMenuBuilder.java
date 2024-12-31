/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bitmap.menu;

import bitmap.main.Window;
import java.awt.Dimension;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import raven.drawer.Drawer;
import raven.drawer.component.SimpleDrawerBuilder;
import raven.drawer.component.footer.SimpleFooterData;
import raven.drawer.component.header.SimpleHeaderData;
import raven.drawer.component.menu.MenuAction;
import raven.drawer.component.menu.MenuEvent;
import raven.drawer.component.menu.SimpleMenuOption;
import raven.swing.AvatarIcon;

/**
 *
 * @author n3v3rm1nd
 */
public class MyMenuBuilder extends SimpleDrawerBuilder{
    
    @Override
    public SimpleHeaderData getSimpleHeaderData() {
        return new SimpleHeaderData()
                .setIcon(new AvatarIcon(getClass().getResource("/bitmap/images/profile.PNG"), 60, 60, 999))
                .setTitle("@n3v3rm1nd8")
                .setDescription("n3v3rm1nd8@github.com");
    }

    @Override
    public SimpleMenuOption getSimpleMenuOption() {
        String menus[][] = {
            {"~Main~"},
            {"Banner"},
            {"Dashboard"},
            {"~Configuration~"},
            {"Scanner", "Threads", "Timeout", "Top Ports"},
        };
        
        
        
        
        
        
        return new SimpleMenuOption().setMenus(menus).addMenuEvent(new MenuEvent(){
            @Override
            public void selected(MenuAction action, int index, int subIndex){
//                if (index == 1) {
//                    JOptionPane.showMessageDialog(null, "The real villain is the one who uses something for evil, not that 'something'");
//                }
                switch (subIndex){
                    case 1:
                        SubWindowThreads swThread = new SubWindowThreads(new Window(), true);
                        swThread.setVisible(true);
                        break;
                        
                    case 2:
                        SubWindowTimeout swTimeout = new SubWindowTimeout(new Window(), true);
                        swTimeout.setVisible(true);
                        break;
                        
                    case 3:
                        SubWindowRangePorts swrp = new SubWindowRangePorts(new Window(), true);
                        swrp.setVisible(true);
                        break;
                }
                //System.out.println("Menu selected " + index + " " + subIndex);
            }
        });
    }

    @Override
    public SimpleFooterData getSimpleFooterData() {
        return new SimpleFooterData();
    }
}
