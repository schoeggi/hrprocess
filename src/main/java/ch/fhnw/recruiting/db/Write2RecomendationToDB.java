package ch.fhnw.recruiting.db;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Logger;
import javax.inject.Named;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.beans.factory.annotation.Autowired;


/**
 *
 * @author Joel Lehner / 16.04.2017
 */

@Named
public class Write2RecomendationToDB implements JavaDelegate {

    private final static Logger LOGGER = Logger.getLogger(Write2RecomendationToDB.class.getName());
    
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public void execute(DelegateExecution execution) throws Exception {
 
        LOGGER.info("Start: Write2RecomendationToDB called!!!");
        int candidate_id = (int) execution.getVariable("candidate_id");
        String recommendation2Interview = (String) execution.getVariable("recommendation2Interview");
        boolean outcome2Interview = (boolean) execution.getVariable("outcome2Interview");  
        String sql_recommendation = "UPDATE applicant SET LINERECOMMENDATION='"+recommendation2Interview+"' , PASSED2INTERVIEW="+outcome2Interview+" WHERE id = "+candidate_id;
        LOGGER.info("Executed SQL statement 2 recommendation:" +sql_recommendation);

        jdbcTemplate.execute(sql_recommendation);      	
        LOGGER.info("End: Write2RecomendationToDB called!!!");
   
    }
}








