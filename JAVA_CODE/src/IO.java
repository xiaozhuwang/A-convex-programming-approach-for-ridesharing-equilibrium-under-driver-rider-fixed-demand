import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class IO {
	//input filename
	String filename_net;
	String filename_driver;
	String filename_rider;
	
	//output filename
	String filename_driver_rider_flow_distribution;
	String filename_riders_price;
	String filename_run_duration;
	String filename_epsilon2;
	String filename_epsilon1;
	String filename_theta_p;
	String filename_tolerance;
	
	int vertex_number;
	int link_number;
	int driver_number;
	int rider_number;
	
	public IO(int vertex_number, int link_number, int driver_number, int rider_number){
		this.vertex_number = vertex_number;
		this.driver_number = driver_number;
		this.rider_number = rider_number;
		this.link_number = link_number;
	}
	
	//----------------------------------------input--------------------------------------
	//input network data
	public double[][] get_net_data(){
		try{
			BufferedReader br = new BufferedReader(new FileReader(this.filename_net));
			double[][] links_temp = null;
			String line = "";
			int rowNum = 0;
			line = br.readLine();
			String[] line_array1 = line.trim().split(",");
			links_temp = new double[this.link_number][line_array1.length];
			
			while((line = br.readLine())!=null){
				String[] line_array = line.trim().split(",");
				for(int i = 0; i<line_array.length; i++){
					links_temp[rowNum][i] = Double.parseDouble(line_array[i]);
				}
				rowNum++;
			}
			br.close();
			return links_temp;
		}catch (Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	//input driver OD
	public double[][] get_driver_OD(){
		try{
			BufferedReader br = new BufferedReader(new FileReader(this.filename_driver));
			double[][] links_temp = null;
			String line = "";
			int rowNum = 0;
			line = br.readLine();
			String[] line_array1 = line.trim().split(",");
			links_temp = new double[this.driver_number][line_array1.length];
			
			while((line = br.readLine())!=null){
				String[] line_array = line.trim().split(",");
				 
				for(int i = 0; i<line_array.length; i++){
					links_temp[rowNum][i] = Double.parseDouble(line_array[i]);
				}
				rowNum++;
			}
			br.close();
			return links_temp;
		}catch (Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	//input rider OD
	public double[][] get_rider_OD(){
		try{
			BufferedReader br = new BufferedReader(new FileReader(this.filename_rider));
			double[][] links_temp = null;
			String line = "";
			int rowNum = 0;
			line = br.readLine();
			String[] line_array1 = line.trim().split(",");
			links_temp = new double[this.rider_number][line_array1.length];
			
			while((line = br.readLine())!=null){
				String[] line_array = line.trim().split(",");
				for(int i = 0; i<line_array.length; i++){
					links_temp[rowNum][i] = Double.parseDouble(line_array[i]);
				}
				rowNum++;
			}
			br.close();
			return links_temp;
		}catch (Exception e){
			e.printStackTrace();
			return null;
		}
	}

	//----------------------------------------------output-----------------------------------
	public void print_driver_rider_flow_distribution(Solution solution){
		try{
			BufferedWriter bw=new BufferedWriter(new FileWriter(new File(this.filename_driver_rider_flow_distribution),true));
			for(Driver driver : solution.drivers){
				double[] driver_flow_distribution = driver.flow_distribution;
				for(int i = 0; i < driver_flow_distribution.length - 1; i ++){
					bw.write(driver_flow_distribution[i] + ",");
				}
				bw.write(driver_flow_distribution[driver_flow_distribution.length - 1] + "\r\n");
			}
			bw.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void print_riders_price(Solution solution){
		try{
			BufferedWriter bw=new BufferedWriter(new FileWriter(new File(this.filename_riders_price),true));
			for(int rideri = 0; rideri < this.rider_number; rideri ++){
				Rider rider = solution.riders.get(rideri);
				bw.write(rider.price + "\r\n");
			}
			bw.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void print_run_duration(long run_duration){
		try{
			BufferedWriter bw=new BufferedWriter(new FileWriter(new File(this.filename_run_duration),true));
			bw.write(run_duration + " ms\r\n");
			bw.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void print_epsilon2(double[][] epsilon2_array, int print_length){
		try{
			BufferedWriter bw=new BufferedWriter(new FileWriter(new File(this.filename_epsilon2),true));
			for(int i = 0; i < print_length; i ++){
				for(int j = 0; j < epsilon2_array[0].length - 1; j ++){
					bw.write(epsilon2_array[i][j] + ",");
				}
				bw.write(epsilon2_array[i][epsilon2_array[0].length - 1] + "\r\n");
			}
			bw.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void print_epsilon1(double[][] epsilon1_array, int print_length){
		try{
			BufferedWriter bw=new BufferedWriter(new FileWriter(new File(this.filename_epsilon1),true));
			for(int i = 0; i < print_length; i ++){
				for(int j = 0; j < epsilon1_array[0].length - 1; j ++){
					bw.write(epsilon1_array[i][j] + ",");
				}
				bw.write(epsilon1_array[i][epsilon1_array[0].length - 1] + "\r\n");
			}
			bw.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void print_theta_p(double[][] theta_p_array, int print_length){
		try{
			BufferedWriter bw=new BufferedWriter(new FileWriter(new File(this.filename_theta_p),true));
			for(int i = 0; i < print_length; i ++){
				for(int j = 0; j < theta_p_array[0].length - 1; j ++){
					bw.write(theta_p_array[i][j] + ",");
				}
				bw.write(theta_p_array[i][theta_p_array[0].length - 1] + "\r\n");
			}
			bw.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void print_all_tolerance(double inner_tolerance, double epsilon1, double epsilon2){
		try{
			BufferedWriter bw=new BufferedWriter(new FileWriter(new File(this.filename_tolerance),true));
			bw.write("inner_tolerance:," + inner_tolerance + "\r\n");
			bw.write("epsilon1:," + epsilon1 + "\r\n");
			bw.write("epsilon2:," + epsilon2 + "\r\n");
			bw.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
