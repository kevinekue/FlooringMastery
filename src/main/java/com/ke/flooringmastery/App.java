/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ke.flooringmastery;

import com.ke.flooringmastery.controller.FlooringMasteryController;
import com.ke.flooringmastery.dao.FlooringMasteryDaoException;
import com.ke.flooringmastery.dao.FlooringMasteryDaoFileImpl;
import com.ke.flooringmastery.service.FlooringMasteryDataValidationException;
import com.ke.flooringmastery.service.FlooringMasteryServiceFileImpl;
import com.ke.flooringmastery.ui.FlooringMasteryView;
import com.ke.flooringmastery.ui.UserIO;
import com.ke.flooringmastery.ui.UserIOConsoleImpl;
import java.io.FileNotFoundException;
import java.io.IOException;
import com.ke.flooringmastery.dao.FlooringMasteryDao;
import com.ke.flooringmastery.service.FlooringMasteryServiceLayer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author Owner
 */
public class App {
    public static void main(String[] args) throws FlooringMasteryDaoException, IOException, FileNotFoundException, FlooringMasteryDataValidationException {
//        UserIO myIo = new UserIOConsoleImpl();
//        FlooringMasteryView myView = new FlooringMasteryView(myIo);
//        FlooringMasteryDao myDao = new FlooringMasteryDaoFileImpl();
////        ClassRosterAuditDao myAuditDao = new ClassRosterAuditDaoFileImpl();
//        FlooringMasteryServiceLayer myService = new FlooringMasteryServiceFileImpl(myDao);
//        FlooringMasteryController controller = new FlooringMasteryController(myService, myView);
//        controller.run();

        ApplicationContext ctx = 
           new ClassPathXmlApplicationContext("applicationContext.xml");
        FlooringMasteryController controller = 
           ctx.getBean("controller", FlooringMasteryController.class);
        controller.run();
    }
}
