package com.lbr;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.List;

import jxl.Cell;
import jxl.Sheet;
import jxl.SheetSettings;
import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.UnderlineStyle;
import jxl.write.WritableCell;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.apache.log4j.Logger;

/**
 * Class to create Excel files from java
 *
 * @created Jul 1, 2005
 * @author vranjan
 */
public class ExcelUtilities {

    protected static final Logger logger = Logger.getLogger(ExcelUtilities.class);
    static final String  SQL_DATE_FORMAT	= "yyyy-MM-dd";
    static final String  EXCEL_DATE_FORMAT	= "yyyy-mm-dd";  // EXCEL uses this format
    static final String  REVENUE_FORMAT	= "0";
    static final SimpleDateFormat formatDate_SQL = new SimpleDateFormat(SQL_DATE_FORMAT);
    //static final SimpleDateFormat formatDate_EXCEL = new SimpleDateFormat(EXCEL_DATE_FORMAT);
    public static final boolean createFileOnDisk = false;


    /**
     * Loads the PINCode XLS file and creates  SQL file to load in the DB.
     * @param inStr
     */
    public static void readXLSAndCreateMasterSQL(InputStream inStr, String outFileName){
        boolean previousRowBlank = false;
        StringBuffer masterSql = new StringBuffer("");
        try{
            Workbook  workbook = Workbook.getWorkbook(inStr);
            Sheet sheet = workbook.getSheet(0);

            int numRows = sheet.getRows();
            int numCols = sheet.getColumns();
            String propertyAccountIDFullString = sheet.getCell(0,0).getContents();
            // getIDForAccountID

            String col1Header = sheet.getCell(0,0).getContents();
            String col2Header = sheet.getCell(1,0).getContents();
            String col3Header = sheet.getCell(2,0).getContents();
            String col4Header = sheet.getCell(3,0).getContents();
            String col5Header = sheet.getCell(3,0).getContents();
            // insert into locations(locID, locName, cityID) values('Murugeshpalya', 1);

	            for (int i = 1; i < numRows; i++) {
	                Cell[] rowCells = sheet.getRow(i);
	                StringBuffer rowSql = new StringBuffer("");

	                //rowSql.append("insert into locations(locID, locName, cityID) values(");
	                rowSql.append(" insert into zipdb(name, pincode, dist, city, state) values(");
                	for (int j = 0; j < numCols; j++) {
                	    Cell aCell = rowCells[j];
                	    String cellContent = aCell.getContents();
                	    switch (j) {
                        case 0:   // Name
	                        	rowSql.append("\""+cellContent+"\"");
	                        	rowSql.append(",");
    	                	    break;
                        case 1:	// PINCODE
                        	//rowSql.append("'"+cellContent.trim()+"'");
                        	//String ss = null;
/*                        	while(cellContent.charAt(0)!='0' || cellContent.charAt(0)!='1' || cellContent.charAt(0)!='2'
                        		|| cellContent.charAt(0)!='3' || cellContent.charAt(0)!='4' || cellContent.charAt(0)!='5'
                        			|| cellContent.charAt(0)!='6' || cellContent.charAt(0)!='7' || cellContent.charAt(0)!='8'
                        				|| cellContent.charAt(0)!='9'){
                        		logger.debug("Some shit in ZIP ..."+cellContent);
                        		ss = cellContent.substring(1);
                        		logger.debug("Correction ..."+ss);
                        	}*/

                        	//rowSql.append(ss);
                        	rowSql.append(cellContent.trim());
                        	rowSql.append(", ");
                            break;
                        case 2:	//District
                        	rowSql.append("\""+cellContent+"\"");
                        	rowSql.append(", ");
                                break;
                        case 3:	// City
                        	rowSql.append("\""+cellContent+"\"");
                        	rowSql.append(", ");
                                break;
                        case 4:	// State
                        	rowSql.append("\""+cellContent+"\"");
                        	rowSql.append(");\n");
                            break;
                        default:
                                break;
                        }
                	}
                	//if(rowCells[0].getContents()!="" && rowCells[1].getContents()!="" && rowCells[3].getContents().equalsIgnoreCase("BANGALORE") && rowCells[4].getContents().equalsIgnoreCase("KARNATAKA")){
                	if(rowCells[0].getContents()!="" && rowCells[1].getContents()!="" && rowCells[3].getContents()!="" && rowCells[4].getContents()!=""){
                		masterSql.append(rowSql);
                	}
	            }
	            masterSql.append("/* ----- End of Locations SQL file --------- */");
	            //logger.debug(masterSql.toString());
	            printToFile(masterSql.toString(), outFileName);

        }
        catch (Exception e) {
			// TODO: handle exception
		}
    }

    public static void readXLSAndCreateLocations(InputStream inStr, String outFileName){
        boolean previousRowBlank = false;
        StringBuffer masterSql = new StringBuffer("");
        try{
            Workbook  workbook = Workbook.getWorkbook(inStr);
            Sheet sheet = workbook.getSheet(0);

            int numRows = sheet.getRows();
            int numCols = sheet.getColumns();
            String propertyAccountIDFullString = sheet.getCell(0,0).getContents();
            // getIDForAccountID

            String col1Header = sheet.getCell(0,0).getContents();
            String col2Header = sheet.getCell(1,0).getContents();
            String col3Header = sheet.getCell(2,0).getContents();
            String col4Header = sheet.getCell(3,0).getContents();
            String col5Header = sheet.getCell(3,0).getContents();
            logger.debug("NumRows = "+numRows+"\tNumCols="+numCols);
            // insert into locations(locID, locName, cityID) values('Murugeshpalya', 1);

	            for (int i = 1; i < numRows; i++) {
	            	try {
	                Cell[] rowCells = sheet.getRow(i);
	                StringBuffer rowSql = new StringBuffer("");

	                rowSql.append("insert into locations(locationID, locName, cityID) values(");
	             // PINCODE
	                rowSql.append(rowCells[1].getContents().trim());
	                rowSql.append(",");
                // name
                    rowSql.append("\""+rowCells[0].getContents()+"\"");
                	rowSql.append(", ");
/*               	//District
                	rowSql.append("\""+rowCells[0].getContents()+"\"");
                	rowSql.append(", ");*/
              	// City
                	rowSql.append("(select cityID from city where cityName=");
                	rowSql.append("'"+rowCells[3].getContents()+"'));\n");
                	//}
                	//if(rowCells[0].getContents()!="" && rowCells[1].getContents()!="" && rowCells[3].getContents().equalsIgnoreCase("BANGALORE") && rowCells[4].getContents().equalsIgnoreCase("KARNATAKA")){
                	if(rowCells[0].getContents()!="" && rowCells[1].getContents()!="" && rowCells[3].getContents()!=""){
                		masterSql.append(rowSql);
                	}
	            	} catch (Exception e) {
	            		logger.debug("ERROR @ i="+i);
						e.printStackTrace();
					}
	            }
	            masterSql.append("/* ----- End of Locations SQL file --------- */");
	            //logger.debug(masterSql.toString());
	            printToFile(masterSql.toString(), outFileName);

        }
        catch (Exception e) {
			// TODO: handle exception
		}

    }

    /**
     * Creates a new XLS file with a worksheet added to it
     * @param xlsFileName	the XLS file to be created
     * @param worksheetName		the worksheet name to be added
     * @param worksheetNumber	the position of the new worksheet
     * @param dataRow	the data for the worksheet(List of list)
     */
    public static WritableWorkbook createExcelFile(String dirName, String xlsFileName, OutputStream outStream, String worksheetName, int worksheetNumber, List dataRow , int headerLineNumber){
    	WritableWorkbook  workbook = null;
    	try{
    		if(ExcelUtilities.createFileOnDisk)
    			workbook = Workbook.createWorkbook(new File(dirName, xlsFileName));
    		else {
    			workbook = Workbook.createWorkbook(outStream);
    		}
            WritableSheet sheet = workbook.createSheet(worksheetName, worksheetNumber);
            if(sheet!=null)
                createXLSFile(workbook, sheet, dataRow, headerLineNumber);
            logger.info("createXLSFile() executed successfully");
        }
        catch (Exception e) {
            logger.error("Exception =", e);
        }
        return workbook;
    }

    /**
     * Adds a new worksheet to the already existing XSL file
     * @param xlsFileName	the existing XLS file
     * @param worksheetName		the new worsheet to be added
     * @param worksheetNumber	the position of the new worksheet
     * @param dataRow	the data for the worksheet(List of list)
     */
    public static void addXLSWorksheet(String dirName, String xlsFileName, String worksheetName, int worksheetNumber, List dataRow, int headerLineNumber){
        try{
        Workbook  workbookx = Workbook.getWorkbook(new File(dirName, xlsFileName));
        WritableWorkbook  workbook = Workbook.createWorkbook(new File(dirName, xlsFileName), workbookx);
        WritableSheet sheet = workbook.getSheet(worksheetName);
        if(sheet==null)
            sheet = workbook.createSheet(worksheetName, worksheetNumber);
        createXLSFile(workbook, sheet, dataRow, headerLineNumber);
        logger.info("addXLSWorksheet() executed successfully");
        }
        catch (Exception e) {
            logger.error("Exception =", e);
        }
    }

    /**
     * Creates an XLS file with the supplied data. This is for internal use
     * @param workbook
     * @param sheet
     * @param dataRows
     */
    public static void createXLSFile(WritableWorkbook workbook, WritableSheet sheet, List dataRows, int headerLineNumber){
        try{
        	SheetSettings sheetSettings  = sheet.getSettings();
        	sheetSettings.setDefaultColumnWidth(33);
        	if(ExcelUtilities.createFileOnDisk)
{
//        		logger.debug("Creating Budget file on Disk....");
}
        	else
{
//        		logger.debug("Creating Budget file in Memory....");
}
            // cell format for header
            WritableFont headerFont = new WritableFont(WritableFont.ARIAL, 12, WritableFont.BOLD, false,UnderlineStyle.SINGLE);
            WritableCellFormat headerFormat = new WritableCellFormat (headerFont);
            headerFormat.setAlignment(Alignment.CENTRE);
            //headerFormat.setWrap(true);

            jxl.write.DateFormat dateFmt = new jxl.write.DateFormat (EXCEL_DATE_FORMAT);
            WritableCellFormat dateFormat = new WritableCellFormat(dateFmt);
            dateFormat.setAlignment(Alignment.CENTRE);

            jxl.write.DateFormat integerFmt = new jxl.write.DateFormat (REVENUE_FORMAT);
            WritableCellFormat integerFormat = new WritableCellFormat(integerFmt);
            integerFormat.setAlignment(Alignment.CENTRE);

            //headerFormat.setShrinkToFit(true);
            //headerFormat.setWrap(true);
            //headerFormat.setIndentation(1);
            //cell format for data
            WritableFont dataFont = new WritableFont(WritableFont.ARIAL, 10, WritableFont.NO_BOLD);
            WritableCellFormat dataFormat = new WritableCellFormat (dataFont);
            dataFormat.setAlignment(Alignment.CENTRE);
            WritableCellFormat dataFormat2 = new WritableCellFormat (dataFont);
            dataFormat2.setAlignment(Alignment.LEFT);
            int numRows = dataRows.size();
            int count = 0;
            for (Iterator iter = dataRows.iterator(); iter.hasNext();) {
                count++;
                int columnNumber = 1;
                List aRow = (List) iter.next();
                int numColumns = aRow.size();
                for (Iterator iterator = aRow.iterator(); iterator.hasNext();) {
                    WritableCell aCellData = (WritableCell) iterator.next();
                    if(count <= headerLineNumber)
                        aCellData.setCellFormat(headerFormat);
                    else if (columnNumber++==numColumns)
                        aCellData.setCellFormat(dataFormat);
                    else
                        aCellData.setCellFormat(dataFormat);

                    try{
	                    if (aCellData instanceof jxl.write.Label) {
	                        sheet.addCell((jxl.write.Label)aCellData);
	                    }
	                    else if (aCellData instanceof jxl.write.DateTime) {
	                    	aCellData.setCellFormat(dateFormat);
	                        sheet.addCell((jxl.write.DateTime)aCellData);
	                    }
	                    else if (aCellData instanceof jxl.write.Number) {
	                    	aCellData.setCellFormat(integerFormat);
	                        sheet.addCell((jxl.write.Number)aCellData);
	                    }
	                    else if (aCellData instanceof jxl.write.Blank) {
	                        sheet.addCell((jxl.write.Blank)aCellData);
	                    }
	                    else if (aCellData instanceof jxl.write.Boolean) {
	                        sheet.addCell((jxl.write.Boolean)aCellData);
	                    }
                    }
                    catch (Exception e) {
                        logger.error("Exception =", e);
                    }
                }
            }

            workbook.write();
           	workbook.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void createStateSQL(InputStream inStr, String outFileName){
        boolean previousRowBlank = false;
        StringBuffer masterSql = new StringBuffer("");
        try{
            Workbook  workbook = Workbook.getWorkbook(inStr);
            Sheet sheet = workbook.getSheet(1);    // STATES

            int numRows = sheet.getRows();
            int numCols = sheet.getColumns();
            logger.debug("NumRows = "+numRows+"\tNumCols="+numCols);

            String col1Header = sheet.getCell(0,0).getContents();
/*            String col2Header = sheet.getCell(1,0).getContents();
            String col3Header = sheet.getCell(2,0).getContents();
            String col4Header = sheet.getCell(3,0).getContents();
            String col5Header = sheet.getCell(3,0).getContents();
 */           // insert into locations(locID, locName, cityID) values('Murugeshpalya', 1);

	            for (int i = 1; i < numRows; i++) {
	                Cell[] rowCells = sheet.getRow(i);
	                StringBuffer rowSql = new StringBuffer("");

	                //insert into state(stateName, countryID) values('A & N ISLAND', 1);
	                rowSql.append("insert into state(stateName, countryID) values(");
                	for (int j = 0; j < numCols; j++) {
                	    Cell aCell = rowCells[j];
                	    String cellContent = aCell.getContents();
                	    switch (j) {
                        case 0:   // Name
	                        	rowSql.append("'"+cellContent+"'");
	                        	rowSql.append(", 1);\n");
    	                	    break;
                        default:
                                break;
                        }
                	}
                	//if(rowCells[0].getContents()!="" && rowCells[1].getContents()!="" && rowCells[3].getContents().equalsIgnoreCase("BANGALORE") && rowCells[4].getContents().equalsIgnoreCase("KARNATAKA")){
                	if(rowCells[0].getContents()!=""){
                		masterSql.append(rowSql);
                	}
	            }
	            masterSql.append("/* ----- End of State SQL file --------- */");
	            //logger.debug(masterSql.toString());

        }
        catch (Exception e) {
			// TODO: handle exception
		}

        printToFile(masterSql.toString(), outFileName);
    }

    public static void createDistrictSQL(InputStream inStr, String outFileName){
        boolean previousRowBlank = false;
        StringBuffer masterSql = new StringBuffer("");
        try{
            Workbook  workbook = Workbook.getWorkbook(inStr);
            Sheet sheet = workbook.getSheet(2);    // DISTRICT

            int numRows = sheet.getRows();
            int numCols = sheet.getColumns();
            logger.debug("NumRows = "+numRows+"\tNumCols="+numCols);
	            for (int i = 1; i < numRows; i++) {
	                Cell[] rowCells = sheet.getRow(i);
	                StringBuffer rowSql = new StringBuffer("");

	                //insert into state(stateName, countryID) values('A & N ISLAND', 1);
	                rowSql.append("insert into district(districtName, stateID) values(");
                	for (int j = 0; j < numCols; j++) {
                	    Cell aCell = rowCells[j];
                	    String cellContent = aCell.getContents();
                	    switch (j) {
                        case 0:   // Name
	                        	rowSql.append("'"+cellContent+"'");
    	                	    break;
                        case 1:   // districts
                        	rowSql.append(", (select stateID from state where stateName=");
                        	rowSql.append("'"+cellContent+"'));\n");
	                	    break;
                        default:
                                break;
                        }
                	}
                	//if(rowCells[0].getContents()!="" && rowCells[1].getContents()!="" && rowCells[3].getContents().equalsIgnoreCase("BANGALORE") && rowCells[4].getContents().equalsIgnoreCase("KARNATAKA")){
                	if(rowCells[0].getContents()!=""){
                		masterSql.append(rowSql);
                	}
	            }
	            masterSql.append("/* ----- End of District SQL file --------- */");
	            //logger.debug(masterSql.toString());
        }
        catch (Exception e) {
			// TODO: handle exception
		}

        printToFile(masterSql.toString(), outFileName);
    }

    public static void createCitySQL(InputStream inStr, String outFileName){
        StringBuffer masterSql = new StringBuffer("");
        try{
            Workbook  workbook = Workbook.getWorkbook(inStr);
            Sheet sheet = workbook.getSheet(3);    // STATES
            int numRows = sheet.getRows();
            int numCols = sheet.getColumns();
            logger.debug("NumRows = "+numRows+"\tNumCols="+numCols);

            String col1Header = sheet.getCell(0,0).getContents();
            String col2Header = sheet.getCell(1,0).getContents();
            String col3Header = sheet.getCell(2,0).getContents();
            // insert into city(cityName, stateID) values('Bangalore', 15);

	            for (int i = 1; i < numRows; i++) {
	                Cell[] rowCells = sheet.getRow(i);
	                StringBuffer rowSql = new StringBuffer("");

	                //insert into state(stateName, countryID) values('A & N ISLAND', 1);
	                rowSql.append("insert into city(cityName, districtID, stateID) values(");
                	for (int j = 0; j < numCols; j++) {
                	    Cell aCell = rowCells[j];
                	    String cellContent = aCell.getContents();
                	    switch (j) {
                        case 0:   // city
	                        	rowSql.append("'"+cellContent+"'");
    	                	    break;
                        case 1:   // District
                        	rowSql.append(", (select districtID from district where districtName=");
                        	rowSql.append("'"+cellContent+"' and stateID=(select stateID from state where stateName= ");
	                	    break;
                        case 2:   // state
                        	rowSql.append("'"+cellContent+"'))");
                        	rowSql.append(", (select stateID from state where stateName=");
                        	rowSql.append("'"+cellContent+"'));\n");
	                	    break;
                        default:
                                break;
                        }

                	}
                	//if(rowCells[0].getContents()!="" && rowCells[1].getContents()!="" && rowCells[3].getContents().equalsIgnoreCase("BANGALORE") && rowCells[4].getContents().equalsIgnoreCase("KARNATAKA")){
                	if(rowCells[0].getContents()!="" && rowCells[2].getContents()!=""){
                		masterSql.append(rowSql);
                	}
	            }
	            masterSql.append("/* ----- End of Cities SQL file --------- */");
	            //logger.debug(masterSql.toString());

        }
        catch (Exception e) {
			// TODO: handle exception
		}

        printToFile(masterSql.toString(), outFileName);
    }

    public static void createLocationsSQL(InputStream inStr, String outFileName){
        StringBuffer masterSql = new StringBuffer("");
        try{
            Workbook  workbook = Workbook.getWorkbook(inStr);
            Sheet sheet = workbook.getSheet(0);

            int numRows = sheet.getRows();
            int numCols = sheet.getColumns();
            String propertyAccountIDFullString = sheet.getCell(0,0).getContents();
            // insert into locations(locID, locName, cityID) values('Murugeshpalya', 1);

	            for (int i = 1; i < numRows; i++) {
	                Cell[] rowCells = sheet.getRow(i);
	                StringBuffer rowSql = new StringBuffer("");
	                rowSql.append("insert into locations(locName, pincode, cityID) values(");
            	    String locationName = rowCells[0].getContents();
            	    String pincode = rowCells[1].getContents();
            	    String districtName = rowCells[2].getContents();
            	    String cityName = rowCells[3].getContents();
            	    String stateName = rowCells[4].getContents();
               // Name
                	rowSql.append("\""+locationName+"\"");
                	rowSql.append(",");
                // PINCODE
                	rowSql.append(pincode.trim());
                	// CityID
                	rowSql.append(", (select cityID from city where cityName=");
                	rowSql.append("'"+cityName+"'  and districtID=(select districtID from district where districtName=");
            	    rowSql.append("'"+districtName+"' and stateID=(select stateID from state where stateName= "+"'"+stateName+"'"+") and stateID=(select stateID from state where stateName= ");
            	    rowSql.append("'"+stateName+"'))));\n");
                	//if(rowCells[0].getContents()!="" && rowCells[1].getContents()!="" && rowCells[3].getContents().equalsIgnoreCase("BANGALORE") && rowCells[4].getContents().equalsIgnoreCase("KARNATAKA")){
                	if(rowCells[0].getContents()!="" && rowCells[1].getContents()!="" && rowCells[3].getContents()!="" && rowCells[4].getContents()!=""){
                		masterSql.append(rowSql);
                	}
	            }
	            masterSql.append("/* ----- End of Locations SQL file --------- */");
	            //logger.debug(masterSql.toString());
	            printToFile(masterSql.toString(), outFileName);

        }
        catch (Exception e) {
			// TODO: handle exception
		}
    }

 public static void printToFile(String str, String fullFilePathName){
     File outputFile = new File(fullFilePathName);
     Writer fs;
		try {
			fs = new PrintWriter(outputFile);
	        fs.write(str.toString());
	        fs.flush();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 }
}

