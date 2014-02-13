/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package GameState;

/**
*
* @author nkostiai
*
* State -enumit kuvaavat pelin eri GameStateja. Jokaisella statella on oma
* tunnistenumeronsa, jonka avulla GameStateManager hoitaa nykyisen pelitilanteen
* vaihtamisen.
*
*/
public enum State {
    MENUSTATE(0), LEVEL1STATE(1);
    
    private final int number;
    private final int totalNumberofStates;
    
    private State(int number){
        this.number = number;
        totalNumberofStates = 2;
    }
    
    public int getStateNumber(){
        return this.number;
    }
    
    public int getTotalNumberofStates(){
        return totalNumberofStates;
    }

    
}

