/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BIN;

import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author HP
 */
public class ConstomAnchor {
    
    public static void fullAnchor(Node node  , double top , double right , double bottom , double left){
        AnchorPane.setTopAnchor(node, top);
        AnchorPane.setRightAnchor(node, right);
        AnchorPane.setBottomAnchor(node, bottom);
        AnchorPane.setLeftAnchor(node, left);
    }
    
}
