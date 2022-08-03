package com.codeup.springblog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class DiceController {

    @GetMapping("/roll-dice")
    public String roll(){
        return "roll-dice";
    }

    @RequestMapping(path = "/roll-dice/{number1}", method = RequestMethod.GET)
    @ResponseBody
    public String rollDice(@PathVariable String number1) {
        return rollRandom(number1) + rollRandom(number1) +rollRandom(number1);
    }

    public String rollRandom(String a){
        int ran = (int)Math.floor((Math.random()*6)+1);
        int aa = Integer.parseInt(a);
        if(aa==ran){
            return "You chose " + a + ". Dice roll was: " + ran +"."  + "Congratulations you win!<br>";
        }else {
            return "You chose " + a + ". Dice roll was: " + ran +"."  + "Boohooo. You lose!<br>";
        }

    }
}
