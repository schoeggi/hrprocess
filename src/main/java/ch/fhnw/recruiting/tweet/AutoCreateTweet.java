package ch.fhnw.recruiting.tweet;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Logger;
import javax.inject.Named;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;


/**
 *
 * @author Joel Lehner / 16.04.2017
 */

@Named
public class AutoCreateTweet implements JavaDelegate {

    private final static Logger LOGGER = Logger.getLogger(AutoCreateTweet.class.getName());
    

    @Override
    public void execute(DelegateExecution execution) throws Exception {
    	
    	
    	String jobTitle = (String) execution.getVariable("jobTitle");
    	String maturity = (String) execution.getVariable("maturity");
    	Boolean knowledgeBananas = (boolean) execution.getVariable("knowledgeBananas");
    	Boolean knowledgeAgile = (boolean) execution.getVariable("knowledgeAgile");
    
        LOGGER.info("AutoCreateTweet.class.getName() called!!!");
        LOGGER.info("JobTitle:" + jobTitle);
        LOGGER.info("Maturity:" + maturity);
        LOGGER.info("KnowledgeBananas:" + knowledgeBananas);
        LOGGER.info("KnowledgeAgile:" + knowledgeAgile);
        
        char c[]      = maturity.toCharArray();
        c[0]          = Character.toLowerCase(c[0]);
        String maturityLowerCase = new String(c);
        String newline = System.getProperty("line.separator");
        String jobTitleWeb = jobTitle.replace(" ", "%20");

        int jobrefid = (int) execution.getVariable("jobrefid");
        
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        //System.out.println(dateFormat.format(date));
        
        String jobURL = "https://sheltered-citadel-37599.herokuapp.com/jobadd.php?job=" + jobTitleWeb + "&maturity=" + maturity + "&jobrefid=" + jobrefid;
    	String tweetBase = "FiusableLtd is hiring! We're looking for a genuine ";
        String tweetDraft = tweetBase + maturityLowerCase + " " + jobTitle + "... " + newline + "Apply here: " + jobURL +" " +dateFormat.format(date);

        /** Return tweetDraft back to camunda */
        execution.setVariable("tweetDraft", tweetDraft);

    }
}








