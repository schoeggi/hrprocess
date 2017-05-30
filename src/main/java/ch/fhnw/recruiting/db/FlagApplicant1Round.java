package ch.fhnw.recruiting.db;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Logger;
import javax.inject.Named;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;


/**
 *
 * @author Joel Lehner / 16.04.2017
 */

@Named
public class FlagApplicant1Round implements JavaDelegate {

    private final static Logger LOGGER = Logger.getLogger(FlagApplicant1Round.class.getName());
    
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        LOGGER.info("Start: FlagApplicant1Round called!!!");
        String mailSubjectAfterDMN = "";
        String mailBodyAfterDMN = "";

        int candidate_id = (int) execution.getVariable("candidate_id");
        boolean applicabilityResult = (boolean) execution.getVariable("applicabilityResult");
        
        if (applicabilityResult == true){
        	mailSubjectAfterDMN = "Your Application at Fiusable Ltd";
        	mailBodyAfterDMN = "Thanks for your application at Fiusable Ltd. Congratulation, you passed the first round (DMN) of candidate evaluation.";
        }
        else{
        	mailSubjectAfterDMN = "Your Application at Fiusable Ltd";
        	mailBodyAfterDMN = "Thanks for your application at Fiusable Ltd. Unfortunately you're application dropped out in our first round (DMN).";
        }
        execution.setVariable("mailSubjectAfterDMN", mailSubjectAfterDMN);
        execution.setVariable("mailBodyAfterDMN", mailBodyAfterDMN);

            
        String sql_recommendation = "UPDATE applicant SET PASSED1DMN =" +applicabilityResult +" WHERE id = " +candidate_id;
        jdbcTemplate.execute(sql_recommendation);     
        LOGGER.info("executed SQL Statement:" +sql_recommendation);
        LOGGER.info("End: FlagApplicant1Round");
    }
}








