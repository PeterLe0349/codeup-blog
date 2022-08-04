package com.codeup.springblog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class DiceController {

    @GetMapping("/roll-dice")
    public String roll(){
        return "roll-dice";
    }

    @RequestMapping(path = "/roll-dice/{number1}", method = RequestMethod.GET)
    public String rollDice(@PathVariable String number1, Model model) {
        String message = rollRandom(number1) + rollRandom(number1) +rollRandom(number1);
        model.addAttribute("message", message);
        return "roll-dice";
    }

    public String rollRandom(String a){
        int ran = (int)Math.floor((Math.random()*6)+1);
        int aa = Integer.parseInt(a);
        if(aa==ran){
            return "You chose " + a + ". Dice roll was: " + ran +"."  + "Congratulations you win!\n";
        }else {
            return "You chose " + a + ". Dice roll was: " + ran +"."  + "Boohooo. You lose!\n";
        }

    }
}
