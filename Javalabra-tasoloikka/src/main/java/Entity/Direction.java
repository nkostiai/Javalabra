/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Entity;

/**
*
* @author nkostiai
*
* Direction -enumit säilyttävät 8 suuntaa ja niille olennaiset vektorikertoimet
*
*/
public enum Direction {
    UP(0, -1), DOWN(0, 1), LEFT(-1, 0), RIGHT(1, 0), TOPLEFT(-0.71, -0.71), TOPRIGHT(0.71, -0.71), BOTTOMLEFT(-0.71, 0.71), BOTTOMRIGHT(0.71, 0.71), NULL(0, 0);
    private double dy;
    private double dx;
    
    private Direction(double dx, double dy){
        this.dy = dy;
        this.dx = dx;
    }
    
    public double getdx(){
        return this.dx;
    }
    public double getdy(){
        return this.dy;
    }
    
}
