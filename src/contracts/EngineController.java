/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package contracts;

import view.ViewContent;




/**
 *
 * @author daan-
 */
public interface EngineController {

    public void setPeriod(int period);






    public void pause();






    public void resume();






    public void terminate();






    public ViewContent getViewContent();

}

