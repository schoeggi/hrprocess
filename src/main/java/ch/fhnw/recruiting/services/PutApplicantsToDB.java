package ch.fhnw.recruiting.services;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Logger;
import javax.inject.Named;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.beans.factory.annotation.Autowired;


/**
 *
 * @author Joel Lehner / 16.04.2017
 */

@Named
public class PutApplicantsToDB implements JavaDelegate {

    private final static Logger LOGGER = Logger.getLogger(PutApplicantsToDB.class.getName());
    
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public void execute(DelegateExecution execution) throws Exception {

        LOGGER.info("InitializeHRprocess called!!!");

       /** execution.setVariable("hrprocess_start_date", new Date()); */
        
        LOGGER.info("Start: Dropping table applicant");
        jdbcTemplate.execute("INSERT INTO APPLICANT (ID,FIRSTNAME,LASTNAME,AGE,STATUS,ADDRESS,TRAVEL,SKILLS,GENDER,SALARY) VALUES (5,'Georg','Buzzi',32,'open','Turmstrasse',true,'Java','m', 100000)");
        LOGGER.info("End: Dropping table applicant");

    }
}







