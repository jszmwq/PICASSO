
/*
 # 
 # 
 # PROGRAM INFORMATION
 # 
 # 
 # Copyright (C) 2006 Indian Institute of Science, Bangalore, India.
 # All rights reserved.
 # 
 # This program is part of the Picasso Database Query Optimizer Visualizer
 # software distribution invented at the Database Systems Lab, Indian
 # Institute of Science (PI: Prof. Jayant R. Haritsa). The software is
 # free and its use is governed by the licensing agreement set up between
 # the copyright owner, Indian Institute of Science, and the licensee.
 # The software is distributed without any warranty; without even the
 # implied warranty of merchantability or fitness for a particular purpose.
 # The software includes external code modules, whose use is governed by
 # their own licensing conditions, which can be found in the Licenses file
 # of the Docs directory of the distribution.
 # 
 # 
 # The official project web-site is
 #     http://dsl.serc.iisc.ernet.in/projects/PICASSO/picasso.html
 # and the email contact address is 
 #     picasso@dsl.serc.iisc.ernet.in
 # 
 #
*/

package iisc.dsl.picasso.common;

public class MessageIds {

	//public static int CLIENTID = 1;   // This is generated by the server first time around
	//public static int MESSAGEID = 2; // Type of command the user is issuing
	
	public static final String ERROR_MSG = "Error";
	public static String STATUS_MSG = "StatusMsg";
	
	// Message End
	public static String END_MESSAGE = "&MSG=END";
	public static String STATUS_END = "&MSG=S_END";
	
	// Command Strings
	public static String[] MessageString = {"", "Get Client Id", "", "", "", "Close Client",
		"Generate Diagram", "Read Diagram", "Estimate time to generate", "Get Plan Tree", "Get Compiled Plan Tree",
		"", "Delete Diagram", "Cleanup", "Delete Picasso Tables", "Get QTD Names", "Cancel Processing", "Get Server Status",
		"Delete Process", "Shutdown Picasso Server", "Get Abstract Plan", "Get Plan Trees", "Get Multiple Plan Trees",
		"Get Plan costs","Get Plan Strings","Get All Plan Costs","Generate Approximate Diagram", 
		"Stop Compilation", "Rename Query Template", "Pause Processing", "Resume Processing","Estimated Approximation Time",
		"Set Server Setting"};
	
	// Commands
	public static final int GET_CLIENT_ID = 1;
	public static final int ERROR_ID = 2;
	public static final int WARNING_ID = 3;
	public static final int STATUS_ID = 4;
	public static final int CLOSE_CLIENT = 5;
	public static final int GENERATE_PICASSO_DIAGRAM = 6;
	public static final int READ_PICASSO_DIAGRAM = 7;
	public static final int TIME_TO_GENERATE = 8;
	public static final int GET_PLAN_TREE = 9;
	public static final int GET_COMPILED_PLAN_TREE = 10;
	public static final int PROCESS_QUEUED = 11;
	public static final int DELETE_PICASSO_DIAGRAM = 12;
	public static final int CLEANUP_PICASSO_TABLES = 13;
	public static final int DELETE_PICASSO_TABLES = 14;
	public static final int GET_QUERYTEMPLATE_NAMES = 15;
	public static final int STOP_PROCESSING = 16;
	public static final int GET_SERVER_STATUS = 17;
	public static final int DELETE_PROCESS = 18;
	public static final int SHUTDOWN_SERVER = 19;
	public static final int GET_ABSTRACT_PLAN = 20;
	public static final int GET_PLAN_TREES = 21;
	public static final int GET_MULTI_PLAN_TREES = 22;
	public static final int GET_PLAN_COSTS = 23;
	public static final int GET_PLAN_STRINGS = 24;
	public static final int GET_ALL_PLAN_COSTS = 25;	
	public static final int RENAME_PICASSO_DIAGRAM = 28;
	public static final int PAUSE_PROCESSING = 29;
	public static final int RESUME_PROCESSING = 30;

	
	
	// Ids for packets returned by Server
	public static final String MAX_CONDITIONS = "MC";
	public static final String RELATION_NAMES = "RN";
	public static final String DATA_POINTS = "DP";
	
	/**
	 * Parameters required to generate Approx Diagram - ADG
	 */
	// Generation Specific
	public static final int GENERATE_APPROX_PICASSO_DIAGRAM = 26;	
	public static final int STOP_COMPILING = 27;	
	public static final int TIME_TO_GENERATE_APPROX = 31;
	// Type of approximation used	
	public static final int SRSWOR = 0;
	public static final int GSPQO = 1;
	//public static final int AGS = 2;
	
	//Added to set the collation and IS_SERVER_DEBUG
	public static final int SET_SERVER_SETTING = 32;
	
	//Optimizer Class
	public static final int Class1 = 1;
	public static final int Class2 = 2;
	/**
	 * Ends here
	 */
}
