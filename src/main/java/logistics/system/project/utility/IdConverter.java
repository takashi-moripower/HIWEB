package logistics.system.project.utility;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IdConverter {
	static public String AnkenNo2Id( String ankenNo ){
		Pattern p = Pattern.compile("(\\w+)-([\\d]{9})(\\d)-(\\w+)");
	    Matcher m = p.matcher(ankenNo);

	    if(m.find()){
	    	String ankenId = m.group(1) + m.group(2);
			return ankenId;
	    }else{

	    	return null;
	    }

	}
}
