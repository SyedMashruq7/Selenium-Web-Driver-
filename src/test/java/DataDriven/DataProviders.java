package DataDriven;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {
	
	@DataProvider
	public String[][] getLoginData() throws IOException{
		
		ReadingDataFromExcel rd = new ReadingDataFromExcel();
		
		int totalrows = rd.getRows();
		int totalcols = rd.getCols();
		
		String loginData[][] = new String[totalrows][totalcols];
		
		for(int i=1;i<=totalrows;i++) {
			for(int j=0;j<totalcols;j++) {
				
				loginData[i-1][j] = rd.getCellData(i,j);
			}
		}
		
		return loginData;
		
	}

}
