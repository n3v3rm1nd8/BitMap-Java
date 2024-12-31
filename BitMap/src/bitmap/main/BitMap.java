/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package bitmap.main;

import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.fonts.roboto.FlatRobotoFont;
import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import java.awt.Font;
import javax.swing.UIManager;

/**
 *
 * @author n3v3rm1nd
 */
public class BitMap {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
//        FlatRobotoFont.install();
//        FlatLaf.registerCustomDefaultsSource("bitmap.themes");
        FlatRobotoFont.install();
        FlatLaf.registerCustomDefaultsSource("bitmap.themes");
        UIManager.put("defaultFont", new Font(FlatRobotoFont.FAMILY, Font.PLAIN, 13));
        FlatMacDarkLaf.setup();
        
        Window w = new Window();
        w.setVisible(true);
    }
    
}
