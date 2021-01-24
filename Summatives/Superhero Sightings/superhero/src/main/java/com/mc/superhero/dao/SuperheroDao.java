/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mc.superhero.dao;

import com.mc.superhero.entities.Superhero;
import com.mc.superhero.entities.Superpower;
import java.util.List;

/**
 * created 1/24/21
 * @author Melanie Carroll
 */
public interface SuperheroDao {
    Superhero getSuperheroById(int id);
    List<Superhero> getAllSuperheros();
    Superhero addSuperhero(Superhero superhero);
    void updateSuperhero(Superhero superhero);
    void deleteSuperheroById(int id);
    
    List<Superpower> getSuperpowerForSuperhero(Superhero superhero);
    
}
