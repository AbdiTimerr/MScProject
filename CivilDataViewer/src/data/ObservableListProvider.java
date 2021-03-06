package data;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import csvReader.CSVReader;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.PieChart.Data;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;


/**
 * A subclass for getting the required data as an observable List.
 * 
 * @author timer
 *
 */
public class ObservableListProvider{
	//to use for the graphs
	private String[] singleStringObservableList;
	private String[] doubleStringObservableList;
	//provided data
	private CSVReader reader;
	private List<String[]> columnData;
	private String[] headerData;
	
	//data for barcharts
	public ObservableListProvider(CSVReader reader, int colNumber) {
		this.reader = reader;
		this.columnData = reader.getData();
		this.headerData = reader.getHeader();
		singleStringObservableList = DataFormatFX.getColumnData(colNumber, columnData);
	}
	
	
	/**
	 * Selects and sets a single column
	 */
	public void setSingleColumnDataToUse(int column) {
		singleStringObservableList = DataFormatFX.getColumnData(column, columnData); 
	}
	
	/**
	 * Selects and sets a single column
	 */
	public void setDoubleColumnDataToUse(int col1, int col2) {
		//TODO: write method for observablelist of two datasets
	}
	/**
	 * Iterates over String array and returns observable list that can be used to draw a pie chart
	 * 
	 * @return Returns observable list data that is used to draw graphs
	 */
	public ObservableList<PieChart.Data> getPieChartObservableList() {
		
		ObservableList<PieChart.Data> observableList = FXCollections.observableArrayList(); 
		
		Map<String, Integer> map = DataFormatFX.countAndMapData(singleStringObservableList);
		
        for(Entry<String, Integer> e : map.entrySet()) {
        	observableList.add(new PieChart.Data(e.getKey(), e.getValue()));
        }
        
        return observableList;
	}

	
	public XYChart.Series getBarChartObservableList(int column) {
		setSingleColumnDataToUse(column);
		
		Map<String, Integer> map = DataFormatFX.countAndMapData(singleStringObservableList);
		
		XYChart.Series dataSeries = new XYChart.Series();
		
		for(Entry<String, Integer> e : map.entrySet()) {
			dataSeries.getData().add(new XYChart.Data(e.getKey(), e.getValue()));
		}
		
		return dataSeries;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public ObservableList<XYChart.Series<String, Integer>> getBarChartData(int column) {
		setSingleColumnDataToUse(column);
		
		ObservableList<XYChart.Series<String, Integer>> observableList = FXCollections.observableArrayList(); 
		Series<String, Integer> series = new Series<>();
		Map<String, Integer> map = DataFormatFX.countAndMapData(singleStringObservableList);
		
		for(Entry<String, Integer> e : map.entrySet()) {
			
			series.getData().add(new XYChart.Data(e.getKey(), e.getValue()));
		}
		observableList.addAll(series);
		return observableList;
		
	}
	
	/**
	 * Method is used to provide the data needed by the barchart.
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static ObservableList<XYChart.Series<String, Integer>> generateBarChartListFromString(String[] stringColumn) {

		ObservableList<XYChart.Series<String, Integer>> observableList = FXCollections.observableArrayList();
		Series<String, Integer> series = new Series<>();
		//Create a map from Column provided
		Map<String, Integer> map = DataFormatFX.countAndMapData(stringColumn);

		for (Entry<String, Integer> e : map.entrySet()) {

			series.getData().add(new XYChart.Data(e.getKey(), e.getValue()));
		}
		observableList.addAll(series);
		return observableList;
	}
	
	public static void main(String[] args) {
		
	}
}
